package main.java.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import security.model.Role;
import security.model.User;
import security.service.RoleService;
import security.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String allUsers(Model model){
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("userAuthentication", (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "allUsers";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("addUser") User user,
                            @RequestParam(value = "select_role", required = false) String[] role) {
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rol.add(roleService.getAllRoles().get(0));
            } else if (s.equals("ROLE_USER")) {
                rol.add(roleService.getAllRoles().get(1));
            }
        }
        user.setRoles(rol);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.show(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "select_roles", required = false) String [] role){
        userService.update(user,role);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "select_roles", required = false) String[] role) {
        userService.update(user, role);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id ){
        userService.delete(id);
        return "redirect:/admin";
    }
}
