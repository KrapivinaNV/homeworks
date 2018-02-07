package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import users.dto.UserDTO;
import users.service.UserService;

@RestController
public class OnlineController {

    private final UserService userService;

    @Autowired
    public OnlineController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/user")
    public void addClient(@RequestBody UserDTO userDTO) {
        //userService.addUser(userDTO);
    }
}
