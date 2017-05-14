package com.dummy.universalshop.controller;

import com.dummy.universalshop.dto.UserDTO;
import com.dummy.universalshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<Boolean> checkUsernameExists(@RequestParam(value = "username") String username) {
        Boolean exists = userService.usernameExists(username);
        return new ResponseEntity<Boolean>(exists, HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<Boolean> registerUser(@RequestBody UserDTO userDTO) {
        Boolean result = userService.registerUser(userDTO);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
