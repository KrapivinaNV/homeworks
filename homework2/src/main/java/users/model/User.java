package users.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class User {
    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;

    public User(UUID id, String firstName, String lastName, Date birthday, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password; ////TODO
    }

    public User(String firstName, String lastName, Date birthday, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public User(){}
}
