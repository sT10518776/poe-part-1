/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login.login;


import java.util.Scanner;

public class Login {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String cellPhone;

    // check firstname
    private boolean checkFirstname(String firstname) {
        return firstname.matches("^[A-Za-z]+$");
    }

    // check lastname
    private boolean checkLastname(String lastname) {
        return lastname.matches("^[A-Za-z]+$");
    }

    // Verify username
    private boolean checkUsername(String username) {
        return username.matches("^[A-Za-z0-9_]{1,5}$") && username.contains("_");
    }

    // confirm password
    private boolean checkPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
    }

    //  cellphone
    public static boolean checkCellPhone(String cellPhone) {
        return cellPhone.matches("^\\+27\\d{9}$");
    }

    // REGISTER
    public String registerUser(String firstname, String lastname,
                               String username, String password,
                               String cellPhone) {

        String message = "";

        if (!checkFirstname(firstname)) {
            return "Firstname is incorrectly formatted (letters only)";
        }

        if (!checkLastname(lastname)) {
            return "Lastname is incorrectly formatted (letters only)";
        }

        if (!checkUsername(username)) {
            return "Username must contain '_' and be max 5 characters";
        } else {
            this.username = username;
            message += "USERNAME HAS BEEN SUCCESSFULLY CAPTURED\n";
        }

        if (!checkPassword(password)) {
            return "Password must be 8+ chars, include uppercase, number, special character";
        } else {
            this.password = password;
            message += "PASSWORD HAS BEEN SUCCESSFULLY CAPTURED\n";
        }

        if (!checkCellPhone(cellPhone)) {
            return "Cellphone number is incorrectly formatted or does not start with +27";
        } else {
            this.cellPhone = cellPhone;
            message += "CELLPHONE NUMBER HAS BEEN SUCCESSFULLY CAPTURED\n";
        }

        this.firstname = firstname;
        this.lastname = lastname;

        message += "User registered successfully!";
        return message;
    }

    // LOGIN (FAIL = END PROGRAM
    public String loginUser(String username, String password) {

        if (this.username == null || this.password == null) {
            return "No user registered yet.";
        }

        if (this.username.equals(username) && this.password.equals(password)) {
            return "SUCCESS|" + firstname + " " + lastname + "|LOGIN_OK";
        } else {
            return "LOGIN_FAILED";
        }
    }

    // MAIN METHOD 
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login user = new Login();

        // REGISTER
        System.out.println(" REGISTER ");

        System.out.print("Enter Firstname: ");
        String fname = input.nextLine();

        System.out.print("Enter Lastname: ");
        String lname = input.nextLine();

        System.out.print("Enter Username: ");
        String uname = input.nextLine();

        System.out.print("Enter Password: ");
        String pass = input.nextLine();

        System.out.print("Enter Cellphone (+27...): ");
        String phone = input.nextLine();

        System.out.println("\n" + user.registerUser(fname, lname, uname, pass, phone));

        // LOGIN
        System.out.println("\nLOGIN");

        System.out.print("Enter Username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = input.nextLine();

        String result = user.loginUser(loginUser, loginPass);

       
        if (result.equals("LOGIN_FAILED")) {
            System.out.println("Invalid password or username.");
            input.close();
            return;
        }

        
        if (result.startsWith("SUCCESS")) {
            String[] parts = result.split("\\|");
            System.out.println("\nWelcome " + parts[1] + ", it is great to see you again!");
        }

        input.close();
    }
}
