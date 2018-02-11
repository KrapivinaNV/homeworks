package users.service;

import users.dto.UserRequest;
import users.dto.UserResponse;
import users.exceptions.UserNotFoundException;

import java.util.Date;
import java.util.UUID;

public interface UserService {

    UUID addUser(String firstName, String lastName, Date birthday, String email, String password);

    void deleteUser(UUID userId);

    UserResponse findByEmail(String email) throws UserNotFoundException;

}
