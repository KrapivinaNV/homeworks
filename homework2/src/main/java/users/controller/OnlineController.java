package users.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import users.dto.UserRequest;
import users.dto.UserResponse;
import users.error.Error;
import users.error.ErrorCode;
import users.exceptions.UserNotFoundException;
import users.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
public class OnlineController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public OnlineController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users")
    public Response addUser(@RequestBody UserRequest userRequest, HttpServletResponse response) {
        try {
            String passwordEncoded = passwordEncoder.encode(userRequest.getPassword());
            UUID uuid = userService.addUser(
                    userRequest.getFirstName(),
                    userRequest.getLastName(),
                    userRequest.getBirthday(),
                    userRequest.getEmail(),
                    passwordEncoded
            );
            return new Response<>(uuid);
        } catch (Exception e) {
            Error error = new Error(ErrorCode.OTHER, "an error has occurred during user creation");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response<>(error);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/users/{user-id}")
    public Response deleteUser(@PathVariable(name = "user-id") UUID userId, HttpServletResponse response) {
        try {
            userService.deleteUser(userId);
            return new Response();
        } catch (UserNotFoundException e) {
            Error error = new Error(ErrorCode.USER_NOT_FOUND, "an error has occurred during user deleting");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new Response<>(error);
        } catch (Exception e) {
            Error error = new Error(ErrorCode.OTHER, "an error has occurred during user deleting");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response<>(error);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{email}")
    public Response findUserByEmail(@PathVariable(name = "email") String email, HttpServletResponse response) {
        try {
            List<UserResponse> byEmail = userService.findByEmail(email);
            return new Response<>(byEmail);
        } catch (UserNotFoundException e) {
            Error error = new Error(ErrorCode.USER_NOT_FOUND, "an error has occurred during user getting");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new Response<>(error);
        } catch (Exception e) {
            Error error = new Error(ErrorCode.OTHER, "an error has occurred during user getting");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response<>(error);
        }
    }
}