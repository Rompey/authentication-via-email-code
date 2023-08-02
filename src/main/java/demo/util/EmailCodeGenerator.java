package demo.util;

import demo.dto.ConfirmationCode;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Component
public class EmailCodeGenerator {
    private static final String DIGITS = "0123456789";

    EmailCodeGenerator(){}
    public static ConfirmationCode validateCode() {
        String code = generateCode();
        Date expirationTime = new Date(System.currentTimeMillis() * 600000);
        return new ConfirmationCode(code, expirationTime);
    }

    private static String generateCode() {
        StringBuilder code = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            code.append(getRandomChar(random));
        }

        return code.toString();
    }

    private static char getRandomChar(Random random) {
        int randomIndex = random.nextInt(EmailCodeGenerator.DIGITS.length());
        return EmailCodeGenerator.DIGITS.charAt(randomIndex);
    }
}



