package main.java.com.mf.spring.springmvsboot.service;


import com.mf.spring.springmvsboot.model.Role;


import java.util.Set;


public interface RoleService {
    Set<Role> getAllRoles();
    Role getRoleByID(Long id);
    Role getRoleByName(String name);
    void addRole(Role role);
}
