package main.java.security.service;

import org.springframework.stereotype.Service;
import security.model.Role;
import security.model.User;

import java.util.List;

@Service

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    User show(Long id);

    void update(User user, String[] role);

    Role showRole(Long id);

    void delete(Long id);

    User findByUserName(String userName);
}
