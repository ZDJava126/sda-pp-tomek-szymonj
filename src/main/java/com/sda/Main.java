package com.sda;


import com.sda.dao.UsersDAO;

public class Main {
    public static void main(String[] args) {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.deleteUser("adamKow");
    }
}
