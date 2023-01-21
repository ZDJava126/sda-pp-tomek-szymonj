package com.sda.service;

import com.sda.dao.UsersDAO;
import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.mapper.UserMapper;
import com.sda.model.User;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

public class UserService {
    private final UsersDAO usersDAO;
    private final UserMapper userMapper;

    public List<UserDTO> findAll() {
        return usersDAO.findAll().stream()
                .map(user -> userMapper.map(user))
                .collect(Collectors.toList());
    }

    public UserDTO findByUsername(String username){

        UserDTO userDTO = userMapper.map(usersDAO.findByUsername(username));

        if (userDTO == null){
                throw new NotFoundException("User not found in database: " + username);
            }
        return userDTO;
        }

    public void deleteByUsername(String username){
        boolean deleted = usersDAO.deleteByUsername(username);
        if(!deleted){
            throw new NotFoundException("Cannot delete by user name, User not found in database: " + username);
        }
    }
    public void create(User user){

        boolean checkedIfExist = usersDAO.exist(user.getUsername());
        if(checkedIfExist){
            throw new UsernameConflictException("User is already in database: " + user.getUsername());
        } else{
            usersDAO.create(user);
        }
    }

    public UserDTO update(User user, String username){
        if (!user.getUsername().equals(username)) {
            throw new UsernameConflictException("Usernames aren't equals");
        } else if (!usersDAO.exist(username)) {
            throw new NotFoundException("User not exist");
        } else {
            User result = usersDAO.update(user);
            return userMapper.map(result);
        }
    }

}




