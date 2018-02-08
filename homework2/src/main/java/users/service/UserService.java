package users.service;

import users.dto.UserDTO;
import users.exceptions.UserNotFoundException;

import java.util.Date;
import java.util.UUID;

public interface UserService {

    UUID addUser(String firstName, String lastName, Date birthday, String email, String password);

    void deleteUser(UUID userId);

    UserDTO findByEmail(String email) throws UserNotFoundException;

}
