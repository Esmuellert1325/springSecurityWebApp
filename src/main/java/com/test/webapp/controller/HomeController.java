package com.test.webapp.controller;

import com.test.webapp.entity.Book;
import com.test.webapp.entity.Role;
import com.test.webapp.entity.User;
import com.test.webapp.repo.BookRepository;
import com.test.webapp.repo.RoleRepository;
import com.test.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    RoleRepository roleRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BookRepository bookRepo;

    @RequestMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getId(auth.getName());
        model.addAttribute("roles", getRoles(userId));
        model.addAttribute("last_login", userRepo.findLastLogin(userId));
//        Set<String> roles = auth.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
//        System.out.println(roles);
//        if (roles.contains("Adminisztrator")) System.out.println("Van admin jogod");
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
        System.out.println(auth.getName());
        List<Role> roles = getRoles(userId);
        for (Role role : roles) {
            if (role.getRole().equals("Adminisztrator")) return "admin";
        }
        return "error";
    }

    @RequestMapping("/books")
    public String books(Model model) {
        List<Book> books = getBooks();
        model.addAttribute("allBooks", books);
        return "books";
    }

    @RequestMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
        userRepo.registerNewUser(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                String.format("{noop}%s", user.getPassword()),
                new Date(),
                user.getBirthDate()
        );
        return "login";
    }

    private List<Book> getBooks() {
        List<Book> books = bookRepo.findAllBooks();
        return books;
    }

    private List<Role> getRoles(Long userId) {
        List<Role> roles = roleRepo.findAllRoles(userId);
        return roles;
    }

    private Long getId(String email) {
        Long id = roleRepo.getUserId(email);
        return id;
    }
}
