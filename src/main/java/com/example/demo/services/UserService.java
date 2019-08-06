package com.example.demo.services;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public UserService() {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserById(int id){
        return userDao.findById(id);
    }

    public User findUserByName(String name){
        return userDao.findByName(name);
    }

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public boolean registerNewUser(String name, String password, boolean registered){
        if (checkNamesForDublicates(name)) {
            userDao.save(new User(name, password, registered));
            return true;
        }
        return false;
    }

    private static boolean checkNamesForDublicates(String name){
        UserService userService = new UserService();
        List<String> allUsers = new ArrayList<>();
        for (User user : userService.findAllUsers()) allUsers.add(user.getName());
        return (!allUsers.contains(name));
    }
}
