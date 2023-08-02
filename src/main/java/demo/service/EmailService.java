package demo.service;

import demo.dto.ConfirmationCode;
import demo.dto.UserRegistrationDTO;
import demo.entity.User;
import demo.repository.UserRepository;
import demo.util.ArgonUtil;
import demo.util.EmailCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;
    private final UserRepository userRepository;
    private final Map<String, UserRegistrationDTO> userMap = new HashMap<>();

    public void sendMail(String to, String subject) {
        ConfirmationCode generateCode = EmailCodeGenerator.validateCode();
        UserRegistrationDTO registrationDTO = userMap.get(to);
        registrationDTO.setConfirmationCode(generateCode);

        CompletableFuture.runAsync(sendEmailCode(to, subject, generateCode.code()));
    }

    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {
        userMap.put(userRegistrationDTO.getEmailAddress(), new UserRegistrationDTO(userRegistrationDTO.getName(),
                userRegistrationDTO.getEmailAddress(),
                userRegistrationDTO.getPassword()));
    }

    public void saveUser(String emailAddress) {
        UserRegistrationDTO userRegistrationDTO = userMap.get(emailAddress);
            userRepository.save(buildUser(userRegistrationDTO));
    }

    private Runnable sendEmailCode(String to, String subject, String message) {
        return () -> {
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            javaMailSender.send(simpleMailMessage);
        };
    }

    private User buildUser(UserRegistrationDTO userRegistrationDTO) {
        return User.builder()
                .name(userRegistrationDTO.getName())
                .emailAddress(userRegistrationDTO.getEmailAddress())
                .password(ArgonUtil.hashPassword(userRegistrationDTO.getPassword()))
                .build();
    }
}
