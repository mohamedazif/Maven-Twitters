package org.twitter.profile.view;

import org.twitter.profile.controller.ProfileController;
import org.twitter.timeline.model.TweetsAndRetweets;
import org.twitter.user.model.User;

import java.util.Collection;
import java.util.Scanner;

/**
 * View class to display the user profile.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class ProfileView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final ProfileController controller;

    public ProfileView(final ProfileController profileController) {
        this.controller = profileController;
    }

    /**
     * To display the user profile with their tweets and retweets.
     *
     * @param loggedUser            User who logged-in
     */
    public void showUserProfile(final User loggedUser) {
        System.out.println("\nUserId: " + loggedUser.getHandle()
                        + "\nWelcome " + loggedUser.getName()
                        + " to the Twitter World!"
                        + "\nBio: " + loggedUser.getBio()
                        + "\nFollowing: " + controller.
                        getFollowingCount(loggedUser.getId())
                        + "\nFollowers: " + controller.
                        getFollowersCount(loggedUser.getId())
                        + "\n\nMy Tweets:");

        final Collection<TweetsAndRetweets> tweetsAndRetweets = controller.
                getTweetsAndRetweets(loggedUser.getId());

        if (tweetsAndRetweets.isEmpty()) {
            System.out.println("Share your Tweets,..");
            return;
        }

        for (final TweetsAndRetweets tweets : tweetsAndRetweets) {
            if (tweets.isRetweet()) {
                System.out.println("\nYou Retweeted");
            }

            System.out.println("User-ID: " + tweets.postedBy() + "\n"
                    + tweets.content() + "\nLikes: " + tweets.likesCount()
                    + "\t\tRetweets: " + tweets.retweetsCount()
                    + "\n---------------------------------------------------");

            if (!(tweets.isRetweet())) {
                System.out.println("Press 0 to delete this tweet");
                final int choice = SCANNER.nextInt();

                if (choice == 0 && controller.deleteTweet(tweets.tweetId())) {
                        System.out.println("Tweet deleted successfully!");
                }
            }
        }
    }
}
