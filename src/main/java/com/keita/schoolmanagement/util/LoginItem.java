package com.keita.schoolmanagement.util;

import com.keita.schoolmanagement.model.Address;
import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.model.User;
import com.keita.schoolmanagement.service.AddressService;
import com.keita.schoolmanagement.service.AuthenticateService;
import com.keita.schoolmanagement.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class LoginItem {

    public static <T> String isUserLogin(T t, String path) {
        if (t != null) {
            return path;
        }
        if (firstFourChars(path).equals("view")) {
            return "//index";
        }
        return "index";
    }

    public static <T> String redirectPath(T user, String successPath, String errorPath) {
        if ((user != null) && firstFourChars(successPath).equals("index")) {
            return "redirect://index";
        } else if (user == null && firstFourChars(successPath).equals("login")) {
            return "redirect:/login";
        } else if (user == null) {
            return successPath;
        }
        return errorPath;
    }

    public static boolean authorize(Authenticate authenticate) {
        return !authenticate.getUserRole().equals("Student");
    }

    public static String index(Authenticate auth, String path) {
        if (auth == null && firstFourChars(path).equals("view")) {
            return "//index";
        }
        return "index";
    }

    public static <T> void setUserAccount(T userId, UserService userService, AddressService addressService,
                                          AuthenticateService authService, Model model) {
        System.out.println("user id is " + userId);
        model.addAttribute("user", userService.getUser(convertToLong(userId)));
        model.addAttribute("address", addressService.getUserAddress(convertToLong(userId)));
        model.addAttribute("authenticate", authService.getUserAuth(convertToLong(userId)));
    }

    private static <T> Long convertToLong(T t) {
        String userID = String.valueOf(t);
        return Long.parseLong(userID);
    }

    private static String firstFourChars(String path) {
        return path.substring(0, 4);
    }

    private static String firstFiveChars(String path) {
        return path.substring(0, 5);
    }

    public static void showHeaderNav(Model model, Authenticate loginUser) {
        if (loginUser != null && (loginUser.getUserRole().equals("Admin") ||
                loginUser.getUserRole().equals("Staff"))) {
            model.addAttribute("isUserLogin", "User_Is_Login");
            model.addAttribute("class_name", "un_order_show");
            model.addAttribute("admin_or_staff", true);
        } else if (loginUser != null) {
            model.addAttribute("isUserLogin", "User_Is_Login");
            model.addAttribute("class_name", "un_order_show");
        } else {
            model.addAttribute("isUserLogin", "User_Is_Not_Login");
        }
    }

    public static Authenticate signOut(Model model, User user, Authenticate authenticate, List<User> userList, Address userAddress) {
        if (user != null || authenticate != null || userList.size() > 0 || userAddress != null) {
            user = null;
            authenticate = null;
            userList = null;
            userAddress = null;
        }
        return authenticate;
    }
}
