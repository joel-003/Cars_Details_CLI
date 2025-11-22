package com.github.joel003.controller;

import com.github.joel003.entity.UserDetail;
import com.github.joel003.service.UserService;
import com.github.joel003.util.ConsoleHelper;
import com.github.joel003.util.InputValidation;

import java.util.Scanner;

public class UserController {

    private final UserService userService;
    private final CarController carController;

    public UserController() {
        this.userService = new UserService();
        this.carController = new CarController();
    }

    /*-----------------------------------------------------
     *                      WELCOME PAGE
     * -----------------------------------------------------*/
    public void welcomePage(Scanner sc) {
        boolean flag ;
        do {
            ConsoleHelper.clearScreen(5);
            System.out.println("-------------> WELCOME TO CARS <-------------\n");
            System.out.println("1.Register User \n2.Login User \n3.Forget Password \n4.Exit");

            int choice = InputValidation.choiceValidate(1,4,sc);
            flag = true;

            switch(choice) {
                case 1 -> registerUser(sc);
                case 2 -> loginUser(sc);
                case 3 -> forgetPassword(sc);
                case 4 ->{flag = false;System.out.println("-------------> THANK YOU FOR VISITING CARS <-------------");}
            }
        }while(flag);
    }

    /*-----------------------------------------------------
    *                       REGISTER USER
    * -----------------------------------------------------*/

    private void registerUser(Scanner sc) {
        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> REGISTER USER <-------------");
        System.out.print("Enter User ID :");
        int id = sc.nextInt();sc.nextLine();
        System.out.print("Enter the User Name : ");
        String name = sc.nextLine();

        //email get and validation
        String email = null;
        do {
            System.out.print("Enter the User E-mail : ");
            email = sc.nextLine();
        }while(!InputValidation.emailValidation(email));

        //Phone Number validation
        String phno = null;
        do {
            System.out.print("Enter the User Phone Number : ");
            phno = sc.nextLine();
        }while(!(InputValidation.phoneNumberValidation(phno)));

        System.out.print("Enter the User Address : ");
        String address = sc.nextLine();

        //password validation
        String pwd = null;
        do {
            System.out.print("Enter the User Password : ");
            pwd = sc.nextLine();
        }while(!(InputValidation.passwordValidation(pwd)));

        UserDetail userDetail = new UserDetail();

        userDetail.setU_id(id);
        userDetail.setU_name(name);
        userDetail.setU_email(email);
        userDetail.setU_phoneNumber(phno);
        userDetail.setU_address(address);
        userDetail.setPwd(pwd);

         boolean success = userService.insertRegisterUser(userDetail);
        System.out.println("-------------------------------------------------");
        System.out.println(success ? "Registered User successfully" : "Failed to Register User.");
        System.out.println("-------------------------------------------------");

    }

    /*-----------------------------------------------------
     *                       LOGIN USER
     * -----------------------------------------------------*/
    private void loginUser(Scanner sc) {
        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> LOGIN CARS <-------------\n");
        System.out.print("Enter the E-mail : ");
        String email = sc.nextLine().trim();
        System.out.print("Enter the password : ");
        String pwd = sc.nextLine().trim();

        String res = userService.verifyUser(email,pwd);
        if(res == null) {
            System.err.println("User Not available");
        }
        else {
            carController.userPage(res,sc);
        }
    }

    private void forgetPassword(Scanner sc)  {
        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> Forget Password CARS <-------------\n");
        System.out.print("Enter the E-mail : ");
        String email = sc.nextLine();

        String res = userService.searchEmail(email);
        if (res == null) {
            System.err.println("Email Not available/Incorrect");
            return;
        }
        boolean success = userService.setNewPassword(email,sc);
        System.out.println("-------------------------------------------------");
        System.out.println(success ? "New Password Updated successfully" : "Failed to Update Password.");
        System.out.println("-------------------------------------------------");

    }


}
