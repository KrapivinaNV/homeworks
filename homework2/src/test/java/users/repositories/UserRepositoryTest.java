package users.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import users.model.User;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getByEmail() {
        UUID uuid = UUID.randomUUID();

        entityManager.persist(
                new User(
                        uuid,
                        "TestUser",
                        "TestUserLastName",
                        new Date(1990, 06, 15),
                        "TestUser1@gmail.com",
                        "")
        );

        User user = userRepository.getByEmail("TestUser@gmail.com");

        assertEquals(user.getId(), uuid);
    }


}