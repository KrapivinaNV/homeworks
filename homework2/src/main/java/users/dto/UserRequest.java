package users.dto;

import lombok.Data;
import users.model.User;

import java.util.Date;
import java.util.UUID;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;

    public UserRequest(){}

    public UserRequest(String firstName, String lastName, Date birthday, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public static UserRequest fromUser(User user) {
        return new UserRequest(
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
