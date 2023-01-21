package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.sda.db.HibernateUtils.getSessionFactory;

public class UsersDAO {

    public void create(User user) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

    public boolean deleteByUsername(String username) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, username); //lub get -> find
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
            return user != null;
        }
    }

    public List<User> findAll() {
        try (Session session = HibernateUtils.openSession()) {
            return session.createQuery("SELECT u FROM User u", User.class).getResultList();
        }

    }

    public User findByUsername(String username) {
        try (Session session = HibernateUtils.openSession()) {
            //  Transaction transaction = session.beginTransaction();
            User user = session.find(User.class, username);
            if (user == null) {
                System.out.println("Nie znaleziono takiego uzytkownika!!");
            }
            return user;
        }
    }

    public User update(User user) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            User userUpdate = session.merge(user);
            transaction.commit();
            if (userUpdate == null) {
                System.out.println("Nie znaleziono takiego uzytkownika!!");
            }
            return userUpdate;
        }
    }

    public boolean exist(String username) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.find(User.class, username);
            if (user == null) {
                return false;
            } else {
                return true;
            }
        }
    }
}
