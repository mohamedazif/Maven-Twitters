package user.view;

import org.twitter.user.controller.UserController;
import org.twitter.user.model.User;
import org.twitter.user.service.UserValidationUtils;

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

    private final UserController controller;

    public UserView(final UserController userController) {
        this.controller = userController;
    }

    /**
     * View for user register.
     */
    public void registerUser() {
        System.out.println("Enter the following details:" + "\nEnter Name:");
        final String userName = SCANNER.nextLine();
        System.out.println("Enter Email-Id:");
        final String email = SCANNER.next();

        if (!UserValidationUtils.isValidEmail(email)) {
            System.err.println("Invalid Email\nUser not registered");
            return;
        }

        System.out.println("Create User-ID:");
        final String handle = SCANNER.next();
        System.out.println("Create Password:");
        final String password = SCANNER.next();

        if (!UserValidationUtils.isValidPassword(password)) {
            System.err.println("Password must contain an Uppercase, a "
                    + "lowercase and a digit and with minimum 8 characters");
            return;
        }

        System.out.println("Enter your age:");
        final int age = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Tell about yourself:");
        final String bio = SCANNER.nextLine();

        if (controller.registerUser(handle, email,
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
        final String handle = SCANNER.next();
        System.out.println("Enter your Password:");
        final String password = SCANNER.next();
        SCANNER.nextLine();

        final User loggedUser = controller.loginUser(handle, password);

        if (Objects.isNull(loggedUser)) {
            System.err.println("Invalid UserId or Password!");
        } else {
            System.out.println("User logged in!");
        }
        return loggedUser;
    }
}
