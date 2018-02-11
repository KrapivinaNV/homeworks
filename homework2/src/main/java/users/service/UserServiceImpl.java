package users.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import users.dto.UserResponse;
import users.exceptions.UserNotFoundException;
import users.model.User;
import users.repositories.UserRepository;
import users.service.exceptions.NoUserIdFoundException;

import java.util.Date;
import java.util.UUID;

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UUID addUser(String firstName, String lastName, Date birthday, String email, String password) {
        User user = new User(firstName, lastName, birthday, email, password);
        user.setId(UUID.randomUUID());
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    public void deleteUser(UUID userId) {
        try {
            userRepository.delete(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new NoUserIdFoundException();
        }
    }

    public UserResponse findByEmail(String email) throws UserNotFoundException {

        User userFound = userRepository.getByEmail(email);
        if (null == userFound) {
            throw new UserNotFoundException();
        }

        return UserResponse.fromUser(userFound);
    }

}
