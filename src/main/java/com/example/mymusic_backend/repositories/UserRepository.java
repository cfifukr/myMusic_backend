package com.example.mymusic_backend.repositories;


import com.example.mymusic_backend.models.collections.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {


    User getUserByEmail(String email);
}
