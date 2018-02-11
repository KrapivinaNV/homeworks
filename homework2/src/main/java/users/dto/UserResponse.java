package users.dto;

import lombok.Data;
import users.model.User;

import java.util.Date;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;

    public UserResponse() {
    }

    public UserResponse(UUID id, String firstName, String lastName, Date birthday, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getEmail()
        );
    }
}
