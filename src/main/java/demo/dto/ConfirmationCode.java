package demo.dto;

import java.util.Date;

public record ConfirmationCode(String code, Date expirationTime){

}
