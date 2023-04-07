package com.example.security.service;

import com.example.security.entities.Role;
import com.example.security.entities.User;
import com.example.security.repositories.RoleRepo;
import com.example.security.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class UserServiceImp implements UserService , UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepo.findByUsername(username).get();
        if(user == null){
            log.error("user not found ");
            throw new UsernameNotFoundException("user not found");
        }else {
            log.info("user found:{}",username);
        }
        Collection<SimpleGrantedAuthority> authorities =  new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("saving new user {} to the database",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the database",role.getName());

        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);

        User user=userRepo.findByUsername(username).get();
        Role role=roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("fetching user {}",username);

        return userRepo.findByUsername(username).get();
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users ");
        return userRepo.findAll();
    }




}
