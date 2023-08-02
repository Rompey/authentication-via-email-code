package demo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserRegistrationDTO{
    private String name;
    private String emailAddress;
    private String password;
    private ConfirmationCode confirmationCode;

    public UserRegistrationDTO(String name, String emailAddress, String password){
        this.name=name;
        this.emailAddress=emailAddress;
        this.password=password;
    }
}
