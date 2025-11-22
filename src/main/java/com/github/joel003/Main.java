package com.github.joel003;

import com.github.joel003.controller.UserController;

import java.util.Scanner;

public class Main {
    public static void main() {

        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        userController.welcomePage(sc);
    }
}

