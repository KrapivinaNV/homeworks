package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import users.dto.UserDTO;
import users.error.Error;
import users.error.ErrorCode;
import users.exception.UserNotFoundException;
import users.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class OnlineController {

    private final UserService userService;

    @Autowired
    public OnlineController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/users")
    public Response addUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        try {
            UUID uuid = userService.addUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getBirthday(), userDTO.getEmail(), userDTO.getPassword());
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
            UserDTO byEmail = userService.findByEmail(email);
            return new Response<>(byEmail);
        } catch (Exception e) {
            Error error = new Error(ErrorCode.OTHER, "an error has occurred during user getting");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Response<>(error);
        }
    }
}