package com.example.mymusic_backend.services;

import com.example.mymusic_backend.dto.request.UserRequestDto;
import com.example.mymusic_backend.models.collections.User;
import com.example.mymusic_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long musicId){
        return userRepository.findById(musicId).orElse(null);
    }


    public User createUser(UserRequestDto user){
        return userRepository.save(user.getUser());
    }

    //тимчасова логіка
    public User login(String email, String password){
        User user = userRepository.getUserByEmail(email);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
