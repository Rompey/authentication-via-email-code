package demo.controller;

import demo.dto.EmailDTO;
import demo.dto.UserRegistrationDTO;
import demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-code")
    public void sendMail(@RequestBody EmailDTO emailDTO){
        emailService.sendMail(emailDTO.to(), "Your verification code is:");
    }

    @PostMapping("/save-user")
    public void saveUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        emailService.saveUser(userRegistrationDTO.getEmailAddress());
    }

    @PostMapping("/save-temporary-user")
    public void temporaryUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        emailService.registrationUser(userRegistrationDTO);
    }
}
