package main.java.com.mf.spring.springmvsboot.service;

import com.mf.spring.springmvsboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void addUser(User u);
    void deleteById(Long id);
    User getUserById(Long id);
    void updateUser(User u);
    User getByName(String name);
}
