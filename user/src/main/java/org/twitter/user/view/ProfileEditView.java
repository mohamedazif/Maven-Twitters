package org.twitter.user.view;

import org.twitter.user.controller.ProfileEditController;
import org.twitter.user.model.User;

import java.util.Scanner;

/**
 * View class for providing a edit profile screen.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class ProfileEditView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final ProfileEditController controller = new ProfileEditController();

    /**
     * Menu for edit the profile of the user.
     *
     * @param user Logged-in user
     */
    public void showEditMenu(final User user) {
        System.out.println("""
                Edit Profile:
                1. Change Username
                2. Change Password
                3. Change Age
                4. Change Bio
                5. Delete Account
                0. Exit""");

        int choice = SCANNER.nextInt();
        SCANNER.nextLine(); // consume newline

        try {
            switch (choice) {
                case 1 -> changeUsername(user);
                case 2 -> changePassword(user);
                case 3 -> changeAge(user);
                case 4 -> changeBio(user);
                case 5 -> removeAccount(user);
                case 0 -> System.out.println("Exiting edit menu...");
                default -> {
                    System.out.println("Invalid option!");
                    SCANNER.nextLine();
                    showEditMenu(user);
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * To change the username.
     *
     * @param user Logged-in User
     */
    private void changeUsername(final User user) {
        System.out.print("Enter new username: ");
        String username = SCANNER.nextLine();
        controller.changeUsername(user, username);
        System.out.println("Username changed successfully!");
    }

    /**
     * To change the password.
     *
     * @param user Logged-in User
     */
    private void changePassword(final User user) {
        System.out.print("Enter new password: ");
        String password = SCANNER.nextLine();
        controller.changePassword(user, password);
        System.out.println("Password changed successfully!");
    }

    /**
     * To change the age.
     *
     * @param user Logged-in User
     */
    private void changeAge(final User user) {
        System.out.print("Your current age: " + user.getAge() + "\nEnter new age: ");
        int age = SCANNER.nextInt();
        controller.changeAge(user, age);
        System.out.println("Age changed successfully!");
    }

    /**
     * To change the bio.
     *
     * @param user Logged-in User
     */
    private void changeBio(final User user) {
        System.out.print("Enter new bio: ");
        String bio = SCANNER.nextLine();
        if (bio.isBlank()) {
            System.err.println("Bio cannot be blank");
            return;
        }
        controller.changeBio(user, bio);
        System.out.println("Bio updated successfully!");
    }

    /**
     * To delete a user account.
     *
     * @param user Logged-in User
     */
    private void removeAccount(final User user) {
        controller.removeAccount(user);
    }
}
