package users.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import users.Starter;
import users.controller.Response;
import users.dto.UserRequest;
import users.dto.UserResponse;
import users.repositories.UserRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Starter.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void mainUserFlowTest() {
        UserRequest userRequest = new UserRequest("testFirstName", "testLastName",
                new Date(1990, 03, 9),
                "user@mail",
                "pwd"
        );
        ResponseEntity<Response> exchange = restTemplate.exchange(
                "http://localhost:8080/api/users",
                HttpMethod.POST,
                new HttpEntity<>(userRequest),
                Response.class
        );
        UUID id = UUID.fromString((String) exchange.getBody().getValue());
        assertNotNull(id);

        Map<String, String> paramsForGet = new HashMap<>();
        paramsForGet.put("email", "user@mail");
        Response response = restTemplate.getForObject("http://localhost:8080/api/users/{email}", Response.class, paramsForGet);

        UserResponse[] userResponses = objectMapper.convertValue(response.getValue(), UserResponse[].class);

        assertArrayEquals(userResponses, new UserResponse[]{
                        new UserResponse(
                                id,
                                "testFirstName",
                                "testLastName",
                                new Date(1990, 03, 9),
                                "user@mail")
                }
        );

        Map<String, String> paramsForDelete = new HashMap<>();
        paramsForDelete.put("user-id", id.toString());
        restTemplate.delete("http://localhost:8080/api/users/{user-id}", paramsForDelete);

        assertEquals(userRepository.findAll(), new ArrayList<>());
    }
}
