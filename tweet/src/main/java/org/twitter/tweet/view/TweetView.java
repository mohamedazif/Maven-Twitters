package org.twitter.tweet.view;

import org.twitter.tweet.controller.TweetController;
import org.twitter.tweet.service.TweetPost;

import java.util.Scanner;

public class TweetView {

    public void postTweet(final String userId) {
        System.out.println("\nWrite your tweet thoughts:");

        final Scanner SCANNER = new Scanner(System.in);
        final String tweetContent = SCANNER.nextLine();

        if (tweetContent.isBlank()) {
            System.err.println("Tweet content cannot be empty");
            return;
        }

        if (280 < tweetContent.length()) {
            System.err.println("Tweet cannot exceed 280 characters.");
            return;
        }

        final TweetController controller = new TweetController(new TweetPost());

        if (controller.postTweet(userId, tweetContent)) {
            System.out.println("Tweet posted successfully by " + userId);
        }
    }
}
