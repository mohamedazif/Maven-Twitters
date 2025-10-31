import org.twitter.follow.view.FollowView;
import org.twitter.tweet.view.TweetView;
import org.twitter.user.model.User;
import org.twitter.user.view.ProfileEditView;
import org.twitter.user.view.UserView;

import java.util.Objects;
import java.util.Scanner;

public class Main {

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
            UserView userView = new UserView();

            switch (choice) {
                case 1 -> userView.registerUser();
                case 2 -> loggedUser = userView.loginUser();
                case 0 -> { }
                default -> System.out.println("Invalid Option!");
            }
        } else {
            FollowView followView = new FollowView();
            ProfileEditView profileEditView = new ProfileEditView();
            switch (choice) {
//                case 1 -> showTimeline();
//                case 2 -> showProfile();
                case 3 -> {
                    TweetView tweetView = new TweetView();
                    tweetView.postTweet(loggedUser.getId());
                }
                case 4 -> followView.showFollowMenu(loggedUser.getId());
                case 5 -> profileEditView.showEditMenu(loggedUser);
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
