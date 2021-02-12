package com.keita.schoolmanagement.controller;

import com.keita.schoolmanagement.model.Address;
import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.model.User;
import com.keita.schoolmanagement.service.AuthService;
import com.keita.schoolmanagement.service.UserService;
import com.keita.schoolmanagement.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class AppController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utilService;

    private Long userID = null;

    @RequestMapping("/")
    public String index() {
        return "auth";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getUserRegistration(@ModelAttribute User user, Model model) {
        user.setUserID((long) utilService.generateRandomNumber());
        model.addAttribute("user", user);
        return "/UserRForm";
    }

    @RequestMapping(value = "/process_u_register", method = RequestMethod.POST)
    public String postUserRegistration(@Valid User user, BindingResult bindingResult, Model model) {
        System.out.println("user id is " + user.getUserID());
        if (bindingResult.hasErrors()) {
            return "/UserRForm";
        }
        User inserted = userService.save(user);
        if (inserted == null) {
            return "/UserAForm";
        }
        userID = user.getUserID();
        return "redirect:/address";
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String getUserAForm(@ModelAttribute Address address, Model model) {
        model.addAttribute("address", address);
        return "/UserAForm";
    }

    @RequestMapping(value = "/auth_r_form", method = RequestMethod.GET)
    public String getRegisterForm(@ModelAttribute Authenticate authenticate, Model model) {
        model.addAttribute("authenticate", new Authenticate());
        return "/Register";
    }

    @PostMapping( path = "/login_auth")
    public String login(@ModelAttribute Authenticate authenticate) throws SQLException {
        Authenticate auth = authService.auth(authenticate.getUsername(), authenticate.getPassword());
        System.out.println("Print this page " + auth.getUsername());
        return "index";
    }

    @RequestMapping(path = "/process_register", method = RequestMethod.POST)
    public String    registerStudent(@Valid Authenticate authenticate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Register";
        }
//        if (user.getFirstName() != null) {
//            System.out.println("Name " + user.getFirstName() + " username " + authenticate.getUsername());
//        }

        if (authenticate.getUser() != null) {
            System.out.println("name " + authenticate.getUser().getFirstName() + " username " + authenticate.getUsername());
        }

        System.out.println(" username " + authenticate.getUsername());
        return "/Register";
    }

}
