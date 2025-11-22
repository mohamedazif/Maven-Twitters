package org.twitter.console;

import org.twitter.factory.AppFactory;
import org.twitter.follow.view.FollowerView;
import org.twitter.profile.view.ProfileEditView;
import org.twitter.profile.view.ProfileView;
import org.twitter.timeline.view.TimelineView;
import org.twitter.tweet.view.TweetView;
import org.twitter.user.model.User;
import org.twitter.user.view.UserView;

import java.util.Objects;
import java.util.Scanner;

/**
 * org.twitter.console.Main view of the class which acts as an entry point of the project.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public class Main {

    private static final AppFactory FACTORY = AppFactory.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);

    private static User loggedUser = null;

    public static void main(String[] args) {
        System.out.println("Hello and Welcome to Twitter Model!");
        int choice;

        do {
            showMenu();
            choice = getChoice();
            handleChoice(choice);
        } while (choice != 0);

        System.out.println("Goodbye for now!");
    }

    /**
     * Displays the menu options depending on user login state.
     */
    private static void showMenu() {
        SCANNER.nextLine();

        if (Objects.isNull(loggedUser)) {
            System.out.println("""
                    Menu:
                    1. Register
                    2. Login""");
        } else {
            System.out.println("""
                    Menu:
                    1. Timeline
                    2. Profile
                    3. Post Tweet
                    4. Follow Suggestions
                    5. Delete/Edit Profile
                    6. Logout""");
        }

        System.out.println("0. Exit");
    }


    /**
     * Reads the user's choice and ensures it is a valid integer.
     *
     * @return the user's numeric choice
     */
    private static int getChoice() {
        System.out.print("Enter your choice: ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Please enter a number:");
            SCANNER.next();
        }

        int choice = SCANNER.nextInt();
        SCANNER.nextLine();
        return choice;
    }

    /**
     * Handles the menu choice depending on whether the user is logged in.
     *
     * @param choice the menu choice entered by the user
     */
    private static void handleChoice(final int choice) {

        if (loggedUser == null) {
            UserView userView = FACTORY.getUserView();

            switch (choice) {
                case 1 -> userView.registerUser();
                case 2 -> loggedUser = userView.loginUser();
                case 0 -> { }
                default -> System.out.println("Invalid Option!");
            }

        } else {

            switch (choice) {
                case 1 -> {
                    final TimelineView timelineView = FACTORY.getTimelineView();

                    timelineView.viewTimeLine(loggedUser.getId());
                }
                case 2 -> {
                    final ProfileView profileView = FACTORY.getProfileView();
                    profileView.showUserProfile(loggedUser);
                }
                case 3 -> {
                    final TweetView tweetView = FACTORY.getTweetView();
                    tweetView.postTweet(loggedUser.getId(), loggedUser.getHandle());
                }
                case 4 -> {
                    final FollowerView followerView = FACTORY.getFollowerView();
                    followerView.showFollowMenu(loggedUser.getId());
                }
                case 5 -> {
                    final ProfileEditView profileEditView = FACTORY.getProfileEditView();
                    profileEditView.showEditMenu(loggedUser);
                }
                case 6 -> {
                    loggedUser = null;
                    System.out.println("User logged out!");
                }
                case 0 -> { }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}