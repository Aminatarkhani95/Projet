package com.example.security.repositories;

import com.example.security.entities.Maps;
import com.example.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MapsRepo extends JpaRepository<Maps,Long> {


    Maps findByTitle(String title);
}
