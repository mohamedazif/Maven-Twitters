package org.twitter.tweet.view;

import org.twitter.tweet.controller.TweetController;

import java.util.Scanner;

/**
 * View class for the Tweet module.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class TweetView {
    private final TweetController controller;

    public TweetView(final TweetController tweetController) {
        this.controller = tweetController;
    }

    /**
     * Get input from the user for posting the tweet.
     *
     * @param userId        ID of the logged-in user.
     */
    public void postTweet(final long userId, final String userHandle) {
        System.out.println("\nWrite your tweet thoughts:");

        final Scanner scanner = new Scanner(System.in);
        final String tweetContent = scanner.nextLine();

        if (tweetContent.isBlank()) {
            System.err.println("Tweet content cannot be empty");
            return;
        }

        if (280 < tweetContent.length()) {
            System.err.println("Tweet cannot exceed 280 characters.");
            return;
        }

        if (controller.postTweet(userId, tweetContent)) {
            System.out.println("Tweet posted successfully by " + userHandle);
        }
    }
}
