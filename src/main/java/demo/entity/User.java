package demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(schema = "public", name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "email_address")
    private String emailAddress;
    private String password;
    private Date createdDate = new Date(System.currentTimeMillis() * 1000);
}
