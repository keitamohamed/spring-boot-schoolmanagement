package com.keita.schoolmanagement.controller;

import com.keita.schoolmanagement.model.Address;
import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.model.User;
import com.keita.schoolmanagement.service.AddressService;
import com.keita.schoolmanagement.service.AuthenticateService;
import com.keita.schoolmanagement.service.UserService;
import com.keita.schoolmanagement.util.LoginItem;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class EditController {

    public static String updateUser(Authenticate auth, User user, UserService uService, BindingResult result, Model model) {
        if (auth.getUser().getUserID() == null || result.hasErrors()) {
            return LoginItem.index(auth, "view/Account");
        }
        uService.updateUser(auth, user);
        model.addAttribute("address", uService.getUser(auth.getUser().getUserID()));
        LoginItem.isUserLogin(auth, "view/Edit");
        LoginItem.showHeaderNav(model, auth);
        return "redirect:/account";
    }

    public static String updateAddress(Authenticate auth, Address address, AddressService aService, BindingResult result, Model model) {
        if (auth.getUser().getUserID() == null || result.hasErrors()) {
            return LoginItem.index(auth, "view/Account");
        }
        aService.updateAddress(auth.getUser().getUserID(), address);
        model.addAttribute("address", aService.getUserAddress(auth.getUser().getUserID()));
        LoginItem.isUserLogin(auth, "view/Edit");
        LoginItem.showHeaderNav(model, auth);
        return "redirect:/account";

    }

    public static String updateAuthenticate(Authenticate auth, Authenticate authenticate, AuthenticateService authService, BindingResult result, Model model) {
        if (auth.getUser().getUserID() == null || result.hasErrors()) {
            return LoginItem.index(auth, "view/Account");
        }
//        aService.updateAddress(auth.getUser().getUserID(), address);
        authService.updateAuthenticate(auth.getUser().getUserID(), authenticate);
        model.addAttribute("authenticate", authService.getUserAuth(auth.getUser().getUserID()));
        LoginItem.isUserLogin(auth, "view/Edit");
        LoginItem.showHeaderNav(model, auth);
        return "redirect:/account";

    }
}
