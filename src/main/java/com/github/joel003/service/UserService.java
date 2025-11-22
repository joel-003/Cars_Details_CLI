package com.github.joel003.service;

import com.github.joel003.dao.UserDAO;
import com.github.joel003.entity.UserDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UserService {

    private final UserDAO udao;

    public UserService() {
        this.udao = new UserDAO();
    }

    public String searchEmail(String email) {
        try {
            return udao.searchEmail(email);
        } catch (SQLException e) {
            System.out.println("SQLException (SearchEmail): " + e.getMessage());
            return null;
        }
    }

    public boolean insertRegisterUser(UserDetail userDetail) {
        try {
            return udao.registerUser(userDetail);
        } catch (SQLException e) {
            System.out.println("SQLException (insertRegisterUser): " + e.getMessage());
            return false;
        }
    }

    public String verifyUser(String email, String pwd) {
        try {
            return udao.verifyUser(email, pwd);

        } catch (SQLException e) {
            System.out.println("SQLException (verifyUser): " + e.getMessage());
            return null;
        }
    }

    public boolean setNewPassword(String email, Scanner sc) {
        try {
            String otp = generateOTP();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter OTP (valid for 10 seconds): ");

            long start = System.currentTimeMillis();
            String input = null;

            while (System.currentTimeMillis() - start < 10000) {
                if (br.ready()) {
                    input = br.readLine();
                    break;
                }
                Thread.sleep(200);
            }

            if (input == null) {
                System.err.println("OTP Expired!");
                return false;
            }

            if (!otp.equals(input)) {
                System.err.println("Incorrect OTP");
                return false;
            }

            System.out.println("OTP Verified!");

            // FLUSH leftover newline so Scanner won't skip
            if (System.in.available() > 0) {
                System.in.skip(System.in.available());
            }

            System.out.print("Enter new password: ");
            String newPwd = sc.nextLine();

            return udao.changePassword(email, newPwd);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    private String generateOTP() {
        String otp;
        long rand = new Random().nextLong(100000, 1000000);
        otp = Long.toString(rand);
        System.out.println("Your Opt is " + otp + " is valid for 10 seconds");

        return otp;
    }
}
