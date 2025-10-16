package org.twitter.view;

import org.twitter.controller.*;
import org.twitter.model.Tweet;
import org.twitter.model.User;
import org.twitter.service.UserProfile;
import org.twitter.service.Utility;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public final class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static User loggedUser = null;

    public static void main(String[] args) {
        System.out.println("Hello and welcome to Twitter model!");
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
        if (loggedUser == null) {
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
                    5. Logout""");
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
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 0 -> { }
                default -> System.out.println("Invalid Option!");
            }
        } else {
            switch (choice) {
                case 1 -> showTimeline();
                case 2 -> showProfile();
                case 3 -> postTweet();
                case 4 -> suggestFollowers();
                case 5 -> editProfile();
                case 6 -> {
                    loggedUser = null;
                    System.out.println("User logged out!");
                }
                case 0 -> { }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    /**
     * View for user login.
     */
    private static void loginUser() {
        System.out.println("Enter your User-Id:");
        final String userId = SCANNER.next();
        System.out.println("Enter your Password:");
        final String password = SCANNER.next();

        UserController userController = new UserController();
        if (userController.loginUser(userId, password) != null) {
            System.out.println("User logged in!");
        } else {
            System.err.println("Invalid UserId or Password!");
        }
    }

    /**
     * View for register user.
     */
    private static void registerUser() {
        System.out.println("Enter the following details:" + "\nEnter Name:");
        String userName = SCANNER.nextLine();
        System.out.println("Enter Email-Id:");
        String email = SCANNER.next();

        if (!Utility.isValidEmail(email)) {
            System.err.println("Invalid Email\nUser not registered");
            return;
        }

        System.out.println("Create User-ID:");
        String userId = SCANNER.next();
        System.out.println("Create Password:");
        String password = SCANNER.next();

        if (!Utility.isValidPassword(password)) {
            System.err.println("Password must contain an Uppercase, a "
                    + "lowercase and a digits and with minimum 8 characters");
            return;
        }

        System.out.println("Enter your age:");
        int age = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Tell about yourself:");
        String bio = SCANNER.nextLine();

        UserController userController = new UserController();
        if (userController.registerUser(userName, email,
                userId, password, age, bio)) {
            System.out.println("User Registered successfully!");
        } else {
            System.err.println("User not registered!");
        }
    }

    /**
     * View for Timeline display.
     */
    private static void showTimeline() {
        TimelineController timelineController = new TimelineController();
        Collection<Tweet> timelineTweets = timelineController.getTimelineTweets(loggedUser);

        System.out.println("Timeline of " + loggedUser.getUserId());

        if (timelineTweets.isEmpty()) {
            System.out.println("Follow someone to build your timeline...");
        }

        for (final Tweet tweet : timelineTweets) {
            System.out.print("User-Id: " + tweet.getUserId() + "\n"
                    + tweet.getTweetContent()
                    + "\nLikes: " + tweet.getLikedBy().size() + "\tRetweets: "
                    + tweet.getRetweetedBy().size()
                    + "\nEnter 1 to Like\t\t2 to Retweet"
                    + "\t\t3 to Like & Retweet");
            int choice = SCANNER.nextInt();

            switch (choice) {
                case 1 -> {
                    if (timelineController.likeTweet(loggedUser, tweet)) {
                        System.out.println("Tweet liked successfully!");
                    }
                }
                case 2 -> {
                    if (timelineController.retweet(loggedUser, tweet)) {
                        System.out.println("Retweeted successfully!");
                    }
                }
                case 3 -> {
                    boolean liked = timelineController.likeTweet(loggedUser, tweet);
                    boolean retweeted = timelineController.retweet(loggedUser, tweet);
                    if (liked || retweeted) {
                        System.out.println("Liked & Retweeted successfully!");
                    }
                }
                default -> System.out.println("Skipped this tweet.");
            }
        }
    }

    /**
     * View for Profile display.
     */
    private static void showProfile() {
        ProfileController profileController = new ProfileController();
        System.out.println(
                "\nUserId: " + loggedUser.getUserId()
                        + "\nWelcome " + loggedUser.getUserName()
                        + " to the Twitter World"
                        + "\nBio: " + loggedUser.getBio()
                        + "\nFollowing: " + loggedUser.getFollowing().size()
                        + "\nFollowers: " + loggedUser.getFollowers().size()
                        + "\n\nMy Tweets:");

        List<Object> tweets  = profileController.getUserProfileTweets(loggedUser);

        if (tweets.isEmpty()) {
            System.out.println("Share your Tweets,..");
            return;
        }

        for (final Object tweet : tweets) {
            if (tweet instanceof UserProfile.CombinedTweets ct) {
                if (ct.isRetweet()) {
                    System.out.println("\nYou retweeted:");
                }
                System.out.println("User-Id: " + ct.tweet().getUserId() + "\n"
                        + ct.tweet().getTweetContent()
                        + "\nLikes: " + ct.tweet().getLikedBy().size()
                        + "\tRetweets: " + ct.tweet().getRetweetedBy().size()
                        + "-----------------------------------------------");
            }
        }
    }

    /**
     * View for posting a tweet.
     */
    private static void postTweet() {
        System.out.println("\nWrite your tweet thoughts:");

        final String tweetContent = SCANNER.nextLine();

        if (tweetContent == null || tweetContent.isBlank()) {
            System.err.println("Tweet content cannot be empty");
            return;
        }

        if (tweetContent.length() > 280) {
            System.err.println("Tweet cannot exceed 280 characters.");
            return;
        }

        TweetController tweetController = new TweetController();
        if (tweetController.postTweet(loggedUser.getUserId(), tweetContent)){
            System.out.println("Tweet posted successfully by " + loggedUser.getUserId());
        }
    }

    /**
     * View for suggesting followers.
     */
    private static void suggestFollowers() {
        FollowSuggestionController followSuggestionController = new FollowSuggestionController();

        List<User> suggestions = followSuggestionController.getSuggestedUser(loggedUser);

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available right now.");
            return;
        }

        System.out.println("Follow Suggestions for " + loggedUser.getUserName() + ":");
        for (final User suggUser : suggestions) {
            System.out.println("User-ID: " + suggUser.getUserId());
            System.out.println("Followers: " + suggUser.getFollowers().size()
                    + " | Following: " + suggUser.getFollowing().size()
                    + "\nEnter 1 to Follow or 0 to Skip: ");

            int choice = SCANNER.nextInt();
            if (choice == 1) {
                followSuggestionController.followUser(loggedUser, suggUser);
                System.out.println("You are now following " + suggUser.getUserId());
            }
        }
    }

    /**
     * View for editing a profile.
     */
    private static void editProfile() {
        ProfileEditView profileEditView = new ProfileEditView();
        profileEditView.showEditMenu(loggedUser);
    }
}