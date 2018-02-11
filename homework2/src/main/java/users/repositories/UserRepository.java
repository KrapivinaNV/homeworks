package users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import users.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    List<User> getAllUsersByEmail(String email);
}