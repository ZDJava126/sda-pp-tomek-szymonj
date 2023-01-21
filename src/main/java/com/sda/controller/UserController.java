package com.sda.controller;

import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.model.User;
import com.sda.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public void findAll() {
        List<UserDTO> users = userService.findAll();

        if(users.isEmpty()){
            System.out.println("Users list empty!");
        } else {
            System.out.println("User list: ");
            users.forEach(System.out::println);
        }
    }

    public void findByUsername(String username){
        try{
            UserDTO user = userService.findByUsername(username);
            System.out.println("User found: " + username);
        } catch (NotFoundException ex){
            log.error("NotFoundException: {}",ex.getMessage());
        }
    }

    public void deleteByUsername(String username){
        try{
            userService.deleteByUsername(username);
            System.out.println(String.format("User with username %s deleted!.",username));
        } catch (NotFoundException ex){
            log.error("NotFoundException: {}",ex.getMessage());
        }
    }
    public void create(User user){
        try{
            userService.create(user);
            System.out.println(String.format("User with username %s created!.",user.getUsername()));
        } catch (UsernameConflictException ex){
            log.error("UsernameConflictException: {}",ex.getMessage());
        }
    }

    public void update(User user, String username){
        try{
            UserDTO updateUser = userService.update(user, username);
            System.out.println(String.format("User with username %s updated!.",user.getUsername()));
            System.out.println("User after update: " + updateUser);
        } catch(UsernameConflictException ex){
            log.error("UsernameConflictException: {}",ex.getMessage());
        } catch (NotFoundException ex){
            log.error("NotFoundException: {}",ex.getMessage());
        }
    }
}
