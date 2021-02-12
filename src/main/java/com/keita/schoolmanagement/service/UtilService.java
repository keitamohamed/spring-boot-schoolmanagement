package com.keita.schoolmanagement.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UtilService {

    public int generateRandomNumber() {
        Random rnd = new Random();
        return (1000000 + rnd.nextInt(900000000));
    }
}
