package users.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import users.dto.UserResponse;
import users.exceptions.UserNotFoundException;
import users.model.User;
import users.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            throw new UserNotFoundException();
        }
    }

    public List<UserResponse> findByEmail(String email) throws UserNotFoundException {

        List<User> usersFound = userRepository.getAllUsersByEmail(email);
        if (usersFound.isEmpty()) {
            throw new UserNotFoundException();
        }
        ArrayList<UserResponse> userResponses = new ArrayList<>();
        for (User user:usersFound) {
            userResponses.add(UserResponse.fromUser(user));
        }

        return userResponses;
    }
}
