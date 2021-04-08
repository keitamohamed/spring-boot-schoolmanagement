package com.keita.schoolmanagement.controller;

import com.keita.schoolmanagement.model.Address;
import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.model.User;
import com.keita.schoolmanagement.service.AddressService;
import com.keita.schoolmanagement.service.AuthenticateService;
import com.keita.schoolmanagement.service.UserService;
import com.keita.schoolmanagement.util.SetLoginItem;
import com.keita.schoolmanagement.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class AppController {

    Authenticate loginUser = null;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticateService authService;
    @Autowired
    private UtilService utilService;
    @Autowired
    private AddressService addressService;

    private Long userID = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute Authenticate authenticate, Model model) {
        model.addAttribute("authenticate", authenticate);
        SetLoginItem.showHeaderNav(model, loginUser);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(@ModelAttribute Authenticate authenticate, Model model) {
        model.addAttribute("authenticate", authenticate);
        SetLoginItem.showHeaderNav(model, loginUser);
        return "Login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogoutForm(Model model) {
        loginUser = SetLoginItem.signOut(model, new User(), loginUser, null, new Address());
        return "redirect:/";
    }

    @RequestMapping(value = "/user_registration", method = RequestMethod.GET)
    public String getUserRegistration(@ModelAttribute User user, Model model) {
        user.setUserID((long) utilService.generateRandomNumber());
        model.addAttribute("user", user);
        model.addAttribute("action_url", "process_user_registration_form");
        SetLoginItem.showHeaderNav(model, loginUser);
        return "RegisterForm";
    }

    @RequestMapping(value = "/address_form", method = RequestMethod.GET)
    public String getUserAForm(@ModelAttribute Address address, Model model) {
        model.addAttribute("address", address);
        model.addAttribute("action_url", "process_address_form");
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(userID, "/RegisterForm");
    }

    @RequestMapping(value = "/authenticate_form", method = RequestMethod.GET)
    public String getAuthenticateForm(@ModelAttribute Authenticate authenticate, Model model) {
        model.addAttribute("authenticate", authenticate);
        model.addAttribute("action_url", "process_authentication_form");
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(userID, "/RegisterForm");
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(@ModelAttribute Optional<User> user, @ModelAttribute Optional<Address> address,
                          @ModelAttribute Authenticate authenticate, Model model) {
        if (loginUser == null) {
            return SetLoginItem.index(loginUser, "view/Account");
        }
        userID = userService.getID(authService.findIdByID(loginUser.getAuthID()));
        model.addAttribute("user", userService.getUser(userID));
        model.addAttribute("address", addressService.getUserAddress(userID));
        model.addAttribute("authenticate", authService.getUserAuth(userID));
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(loginUser, "view/Account");
    }

    @RequestMapping(value = "/view_list", method = RequestMethod.GET)
    public String getViewList(@ModelAttribute User user, Model model) {
        if (loginUser == null) {
            return SetLoginItem.index(loginUser, "view/Account");
        }
        UtilService.setTitle(model, "Student List");
        model.addAttribute("users", userService.getUsers());
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(loginUser, "view/View");
    }

    @RequestMapping(value = "/user_update_form", method = RequestMethod.GET)
    public String getUserUpdateForm(@ModelAttribute Optional<User> user, Model model) {
        if (loginUser == null || userService.getUser(userID).isEmpty()) {
            return SetLoginItem.index(loginUser, "view/Account");
        }
        model.addAttribute("user", userService.getUser(userID));
        model.addAttribute("action_url", "process_user_update_form");
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(loginUser, "view/Edit");
    }

    @RequestMapping(value = "/address_update_form", method = RequestMethod.GET)
    public String getEditAddressForm(@ModelAttribute Optional<Address> address, Model model) {
        if (loginUser == null || addressService.getUserAddress(userID).isEmpty()) {
            return SetLoginItem.index(loginUser, "view/Account");
        }
        model.addAttribute("address", addressService.getUserAddress(userID));
        model.addAttribute("action_url", "process_address_update_form");
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(loginUser, "view/Edit");
    }

    @RequestMapping(value = "/authenticate_update_form", method = RequestMethod.GET)
    public String getAuthenticateUpdateForm(@ModelAttribute Optional<Authenticate> authenticate, Model model) {
        if (loginUser == null || authService.getUserAuth(userID).isEmpty()) {
            return SetLoginItem.index(loginUser, "view/Account");
        }
        model.addAttribute("authenticate", authService.getUserAuth(userID));
        model.addAttribute("prevPassword", true);
        model.addAttribute("authorize", SetLoginItem.authorize(loginUser));
        model.addAttribute("action_url", "process_authenticate_update_form");
        SetLoginItem.showHeaderNav(model, loginUser);
        return SetLoginItem.isUserLogin(loginUser, "view/Edit");
    }


    /**
     * ALL REQUEST POST MAPPING METHOD
     */

    @RequestMapping(value = "/process_login", method = RequestMethod.POST)
    public String login(@Valid Authenticate authenticate, BindingResult bindingResult, Model model) throws SQLException {
        Authenticate auth = authService.auth(authenticate.getUsername(), authenticate.getPassword());

        if (bindingResult.hasErrors() || auth == null) {
            model.addAttribute("error", "The username and password " +
                    "you entered did not match our record. Please double check and try again");
            return "Login";
        }
        loginUser = auth;
        return "redirect:/";
    }

    @RequestMapping(value = "/process_user_registration_form", method = RequestMethod.POST)
    public String postUserRegistration(@Valid User user, BindingResult bindingResult, Model model) throws SQLException {
        if ((userService.checkEmailValidation(user) != null) || bindingResult.hasErrors()) {
            if (!bindingResult.hasErrors()) {
                bindingResult.rejectValue("email", "error.email", "There is already an account registered with this email");
            }
            return "RegisterForm";
        }
        user.setAge(utilService.calculateUserAge(user.getDateOfBirth()));
        userID = (userService.save(user).getUserID());
        return SetLoginItem.redirectPath(loginUser, "redirect:/address_form", "redirect:/user_registration");
    }

    @RequestMapping(value = "/process_address_form", method = RequestMethod.POST)
    public String postUserAddressForm(@Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "RegisterForm";
        }
        Address inserted = addressService.save(userID, address);
        if (inserted == null) {
            return "RegisterForm";
        }
        return SetLoginItem.redirectPath(loginUser, "redirect:/authenticate_form", "redirect:/address");
    }

    @RequestMapping(value = "/process_authentication_form", method = RequestMethod.POST)
    public String postAuthenticateForm(@ModelAttribute Authenticate authenticate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "RegisterForm";
        }
        authService.save(authenticate, userID);
        return SetLoginItem.redirectPath(loginUser, "login", "redirect:/authenticate_form");
    }

    @RequestMapping(value = "/process_user_update_form", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user, Model model,
                             BindingResult result) {
        return EditController.updateUser(loginUser, user, userService, result, model);
    }

    @RequestMapping(value = "/process_address_update_form", method = RequestMethod.POST)
    public String updateAddress(@ModelAttribute Address address, Model model,
                                BindingResult result) {
        return EditController.updateAddress(loginUser, address, addressService, result, model);
    }

    @RequestMapping(value = "/process_authenticate_update_form", method = RequestMethod.POST)
    public String updateAuthenticate(@ModelAttribute Authenticate authenticate, Model model,
                                     BindingResult result) {
        return EditController.updateAuthenticate(loginUser, authenticate, authService, result, model);
    }

}
