package com.github.joel003.util;

import com.github.joel003.service.UserService;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidation {

    /*-------------------------------------------
    *           EMAIL VALIDATION
    * -------------------------------------------*/
    public static boolean emailValidation(String email) {

        UserService userService = new UserService();

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if(Pattern.matches(regex, email)) {

            String dbEmail = userService.searchEmail(email);

            if(dbEmail != null && dbEmail.equalsIgnoreCase(email)) {
                System.err.println("Email already present--try login/forgetpassword");
                return false;
            }
            return true;
        }
        System.err.println("The valid email(eg:abc@gmail.com)");
        return false;

    }

    /*-------------------------------------------
     *           PASSWORD VALIDATION
     * -------------------------------------------*/
    public static boolean passwordValidation(String pass) {
        String regex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
        if (Pattern.matches(regex, pass)) {
            return true;
        }
        System.err.println("\nPassword must contain at least one - lowercase letter & UpperCase letter &  special character & number");
        return false;
    }

    /*-------------------------------------------
     *           PhoneNumber VALIDATION
     * -------------------------------------------*/
    public static boolean phoneNumberValidation(String phno) {
        String regex = "^[6-9]\\d{9}$";

        if (Pattern.matches(regex, phno)) {
            return true;
        }
        System.err.println("\nPhone number satrts with 6-9 nad must contain 10 numbers");
        return false;
    }

    /*---------------------------------------------------------
                        CHOICE VALIDATION
    ----------------------------------------------------------*/
    public static int choiceValidate(int min, int max, Scanner sc) {
        while (true) {
            System.out.print("Please enter your choice: ");
            String input = sc.nextLine();

            try {
                int num = Integer.parseInt(input);

                if (num >= min && num <= max) {
                    return num;
                } else {
                    System.err.println("Choice out of range. Enter between " + min + " and " + max + ".");
                }

            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Enter a valid number.");
            }
        }
    }
}
