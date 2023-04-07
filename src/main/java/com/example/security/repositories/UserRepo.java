package com.example.security.repositories;


import com.example.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long>{


   Optional <User> findByUsername(String username);
}
