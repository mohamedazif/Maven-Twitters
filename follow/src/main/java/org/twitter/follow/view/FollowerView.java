package org.twitter.follow.view;

import org.twitter.follow.controller.FollowerController;
import org.twitter.user.model.User;

import java.util.Collection;
import java.util.Scanner;

/**
 * View for the Follow Module
 *
 * @version         1.0
 * @author          Mohamed Azif
 */
public final class FollowerView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final FollowerController controller;

    public FollowerView(final FollowerController followerController) {
        this.controller = followerController;
    }

    /**
     * Menu for the Follow view.
     *
     * @param userId        Logged-in User
     */
    public void showFollowMenu(final long userId) {
        System.out.println("""
                1. Follow New Person
                2. Follow By UserID
                3. View Following
                4. View Followers
                5. Unfollow By UserID
                0. Exit""");

        int choice =  SCANNER.nextInt();
        SCANNER.nextLine();

        try {
            switch (choice) {
                case 1 -> followNewUser(userId);
                case 2 -> followById(userId);
                case 3 -> viewFollowing(userId);
                case 4 -> viewFollowers(userId);
                case 5 -> unfollowById(userId);
                case 0 -> System.out.println("Exiting Follow People...");
                default -> {
                    System.out.println("Invalid option!");
                    SCANNER.nextLine();
                    showFollowMenu(userId);
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * To follow a new user from the list.
     *
     * @param userId        Logged-in User
     */
    private void followNewUser(final long userId) {
        final Collection<User> suggestions = controller.suggestFollowers(userId);

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available right now.");
            return;
        }

        System.out.println("Follow Suggestions for " + userId + ":");

        for (final User suggestUser : suggestions) {
            System.out.println("User-ID: " + suggestUser.getHandle()
                    + "\nFollowers: "
                    + controller.getFollowersCount(suggestUser.getId())
                    + " | Following: "
                    + controller.getFollowingCount(suggestUser.getId())
                    + "\nEnter 1 to Follow or 0 to Skip: ");

            final int choice = SCANNER.nextInt();
            SCANNER.nextLine();

            if (choice == 1) {
                controller.followById(userId, suggestUser.getId());
                System.out.println("You are now following: " + suggestUser.getHandle());
            }
        }

    }

    /**
     * To view the users who are following you.
     *
     * @param userId        Logged-in User
     */
    private void viewFollowers(final long userId) {
        final Collection<User> followersList = controller.getFollowers(userId);

        if (followersList.isEmpty()) {
            System.out.println("No one have yet followed you!");
        } else {
            System.out.println("Users who are following you:");
            showUserProfile(followersList);
        }
    }

    /**
     * To view the users who are followed by you.
     *
     * @param userId        Logged-in User
     */
    private void viewFollowing(final long userId) {
        final Collection<User> followingList = controller.getFollowing(userId);

        if (followingList.isEmpty()) {
            System.out.println("You haven't followed anyone!");
        }
        else {
            System.out.println("Users you are following:");
            showUserProfile(followingList);
        }
    }

    /**
     * To show the UserProfile for follow purpose.
     *
     * @param usersList     List of Users
     */
    private void showUserProfile(final Collection<User> usersList) {
        for (final User user : usersList) {
            System.out.println("User-ID: " + user.getHandle()
                    + "\nFollowers: "
                    + controller.getFollowersCount(user.getId())
                    + " | Following: "
                    + controller.getFollowingCount(user.getId())
                    + "\n");
        }
    }

    /**
     * To follow a user by ID.
     *
     * @param userId        Logged-In UserID
     */
    private void followById(final long userId) {
        System.out.println("Enter the User-ID of the person:");
        final long toFollowId = SCANNER.nextLong();
        SCANNER.nextLine();

        if (toFollowId == userId) {
            System.err.println("You can't follow yourself!");
        }

        if (controller.followById(userId, toFollowId)) {
            System.out.println("You are now following " + toFollowId);
        } else {
            System.err.println("User doesn't exist!");
        }
    }

    /**
     * To unfollow a user by ID.
     *
     * @param userId        Logged-In UserID
     */
    private void unfollowById(final long userId) {
        System.out.println("Enter the User-ID of the person to unfollow:");
        final long toUnfollowId = SCANNER.nextLong();
        SCANNER.nextLine();

        if (controller.unfollowById(userId, toUnfollowId)) {
            System.out.println("You have unfollowed " + toUnfollowId);
        } else {
            System.err.println("User doesn't exist!");
        }
    }
}
