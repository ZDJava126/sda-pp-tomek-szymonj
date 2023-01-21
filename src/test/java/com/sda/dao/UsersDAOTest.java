package com.sda.dao;

import com.sda.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sda.db.HibernateUtils.getSessionFactory;

class UsersDAOTest {
    private final UsersDAO userDAO = new UsersDAO();

    @Test
    void testCreateHappyPath(){
        //given
        String expectedUserName = "user1";

        User expectedUser = User.builder()
                .username(expectedUserName)
                .password("password")
                .name("name")
                .surname("surname")
                .email("example@email.com")
                .age(30)
                .build();

        //when

        userDAO.create(expectedUser);


        //then

        try(Session session = getSessionFactory().openSession()){

            User actualUser = session.get(User.class, expectedUserName);
            Assertions.assertNotNull(actualUser);
            Assertions.assertEquals(expectedUser.getName(),actualUser.getName());
            Assertions.assertEquals(expectedUser.getSurname(),actualUser.getSurname());
            Assertions.assertEquals(expectedUser.getPassword(),actualUser.getPassword());
            Assertions.assertEquals(expectedUser.getAge(),actualUser.getAge());
            Assertions.assertEquals(expectedUser.getEmail(),actualUser.getEmail());
        }


    }
}
