package users.dto;

import lombok.Data;
import users.model.User;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDTO {

   // private UUID uuid;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;

    public UserDTO(){}

    public UserDTO(UUID uuid, String firstName, String lastName, Date birthday, String email, String password) {
      //  this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
