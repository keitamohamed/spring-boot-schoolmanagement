package com.keita.schoolmanagement.util;

import com.keita.schoolmanagement.model.Authenticate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
public class UtilService {

    public static void setTitle(Model model, String title) {
        model.addAttribute("title", title);
    }

    public static void headerShow(Model model, Authenticate loginUser) {
        if (loginUser == null) {
            model.addAttribute("class_name", "un_order_show_none");
            return;
        }
        model.addAttribute("isUserLogin", "userIsLogin");
        model.addAttribute("class_name", "un_order_show");
    }

    public int generateRandomNumber() {
        Random rnd = new Random();
        return (1000000 + rnd.nextInt(900000000));
    }

    public int calculateUserAge(Date date) {
        LocalDate birthDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDay, now);
        return period.getYears();
    }
}
