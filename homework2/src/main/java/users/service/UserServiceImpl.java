package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.dto.UserDTO;
import users.model.User;
import users.repositories.UserRepository;

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

    }

    public UserDTO findByEmail(String email) {
        return null;
    }
}
