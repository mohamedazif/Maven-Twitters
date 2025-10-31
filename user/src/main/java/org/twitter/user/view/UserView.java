package org.twitter.user.view;

import org.twitter.user.controller.UserController;
import org.twitter.user.model.User;
import org.twitter.user.service.Utility;

import java.util.Objects;
import java.util.Scanner;

/**
 * View class for providing a Login/Register screen.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * View for user register.
     */
    public void registerUser() {
        System.out.println("Enter the following details:" + "\nEnter Name:");
        final String userName = SCANNER.nextLine();
        System.out.println("Enter Email-Id:");
        final String email = SCANNER.next();

        if (!Utility.isValidEmail(email)) {
            System.err.println("Invalid Email\nUser not registered");
            return;
        }

        System.out.println("Create User-ID:");
        final String userId = SCANNER.next();
        System.out.println("Create Password:");
        final String password = SCANNER.next();

        if (!Utility.isValidPassword(password)) {
            System.err.println("Password must contain an Uppercase, a "
                    + "lowercase and a digit and with minimum 8 characters");
            return;
        }

        System.out.println("Enter your age:");
        final int age = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Tell about yourself:");
        String bio = SCANNER.nextLine();

        UserController userController = new UserController();
        if (userController.registerUser(userId, email,
                userName, password, age, bio)) {
            System.out.println("User Registered successfully!");
        } else {
            System.err.println("User not registered!");
        }
    }

    /**
     * View for user login.
     */
    public User loginUser() {
        System.out.println("Enter your User-Id:");
        final String userId = SCANNER.next();
        System.out.println("Enter your Password:");
        final String password = SCANNER.next();

        UserController userController = new UserController();
        User loggedUser = userController.loginUser(userId, Utility.hashPassword(password));

        if (Objects.isNull(loggedUser)) {
            System.err.println("Invalid UserId or Password!");
        } else {
            System.out.println("User logged in!");
        }
        return loggedUser;
    }

    public void showProfile(final User toViewUser) {

    }
}
