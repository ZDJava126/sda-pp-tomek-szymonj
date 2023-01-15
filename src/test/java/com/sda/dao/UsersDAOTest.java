package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UsersDAOTest {

    private final UsersDAO userDAO = new UsersDAO();


    @Test
    void testCreateUserHappyPath() {
        // given
        String expectedUsername = UUID.randomUUID().toString();

        User expectedUser = User.builder()
                .username(expectedUsername)
                .password("password")
                .name("name")
                .surname("surname")
                .email("example@email.com")
                .age(30).build();

        // when
        userDAO.createUser(expectedUser);

        // then
        User actualUser;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            actualUser = session.get(User.class, expectedUsername);
        }

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(expectedUser.getAge(), actualUser.getAge());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());

    }

}
