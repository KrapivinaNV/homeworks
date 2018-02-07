package users.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;
}
