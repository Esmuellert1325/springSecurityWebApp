package com.test.webapp.controller;

import com.test.webapp.entity.Role;
import com.test.webapp.repo.RoleRepository;
import com.test.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getId(auth.getName());
        model.addAttribute("roles", getRoles(userId));
        model.addAttribute("last_login", userRepo.findLastLogin(userId));
        return "index";
    }

    @RequestMapping("/content")
    public String content() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getId(auth.getName());
        List<Role> roles = getRoles(userId);
        for (Role role : roles) {
            if (role.getRole().equals("Tartalomszerkeszto") || role.getRole().equals("Adminisztrator")) return "content";
        }
        return "error";
    }

    @RequestMapping("/users")
    public String users() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getId(auth.getName());
        List<Role> roles = getRoles(userId);
        for (Role role : roles) {
            if (role.getRole().equals("Bejelentkezett Felhasznalok") || role.getRole().equals("Adminisztrator")) return "users";
        }
        return "error";
    }

    @RequestMapping("/admin")
    public String admin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getId(auth.getName());
        List<Role> roles = getRoles(userId);
        for (Role role : roles) {
            if (role.getRole().equals("Adminisztrator")) return "admin";
        }
        return "error";
    }

    private List<Role> getRoles(Long userId) {
        List<Role> roles = roleRepo.findAllRoles(userId);
        return roles;
    }

    private Long getId(String name) {
        Long id = roleRepo.getUserId(name);
        return id;
    }
}