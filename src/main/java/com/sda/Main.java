package com.sda;


import com.sda.dao.UsersDAO;
import com.sda.model.User;

public class Main {
    public static void main(String[] args) {
        UsersDAO usersDAO = new UsersDAO();
        //usersDAO.deleteUser("adamKow9");
        int i = 0;
        while(i<10000){
            User user2 = new User();
            user2.setUsername("user"+i);
            user2.setPassword("qw");
            user2.setName("user");
            user2.setSurname("Kowalski");
            user2.setAge(35);
            user2.setEmail("adam@test.pl");

            usersDAO.create(user2);
            i++;
        }
//        User user2 = new User();
//        user2.setUsername("adamKow9");
//            user2.setPassword("qwerty1234");
//            user2.setName("Adam");
//            user2.setSurname("Kowalski");
//            user2.setAge(35);
//            user2.setEmail("adam@test.pl");
//
//            usersDAO.createUser(user2);
    }
}
