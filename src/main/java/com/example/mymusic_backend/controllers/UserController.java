package com.example.mymusic_backend.controllers;


import com.example.mymusic_backend.dto.request.UserRequestDto;
import com.example.mymusic_backend.dto.response.UserDto;
import com.example.mymusic_backend.models.collections.User;
import com.example.mymusic_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRequestDto userRequestDto){
        User user = userService.createUser(userRequestDto);

        return ResponseEntity.ok(UserDto.getDto(user));
    }


    //тимчасовий логін
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Map<String, String> requestMap){

        User user = userService.login(requestMap.get("email"), requestMap.get("password"));


        if(user == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(UserDto.getDto(user));

    }

}
