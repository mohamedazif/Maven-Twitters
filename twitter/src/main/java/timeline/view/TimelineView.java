package timeline.view;

import org.twitter.timeline.controller.TimelineController;
import org.twitter.timeline.model.TweetsAndRetweets;

import java.util.Collection;
import java.util.Scanner;

/**
 * View class for the Timeline module which provides the user's timeline.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class TimelineView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final TimelineController controller;

    public TimelineView(final TimelineController timelineController) {
        this.controller = timelineController;
    }

    /**
     * To show a timeline of a user.
     *
     * @param userId            ID of the logged-in user
     */
    public void viewTimeLine(final long userId) {

        final Collection<TweetsAndRetweets> timelineTweets = controller.getTimelineTweets(userId);

        System.out.println("\nTimeline of " + userId);

        if (timelineTweets.isEmpty()) {
            System.out.println("\nFollow Someone to build your Timeline...");
            return;
        }

        for (final TweetsAndRetweets tweetsAndRetweetsTweet : timelineTweets) {

            if (tweetsAndRetweetsTweet.isRetweet()) {
                System.out.println(tweetsAndRetweetsTweet.retweetedBy() + " retweeted this");
            }

            System.out.println("User-ID: " + tweetsAndRetweetsTweet.postedBy() + "\n"
                    + tweetsAndRetweetsTweet.content()
                    + "\n\nLikes: " + tweetsAndRetweetsTweet.likesCount()
                    + "\t\tRetweets: " + tweetsAndRetweetsTweet.retweetsCount()
                    + "\n1 to Like\t\t2 to Retweet\t\t3 to Like & Retweet"
                    + "\t\t0 for next tweet");
            int choice = SCANNER.nextInt();

            switch (choice) {
                case 0 -> { }
                case 1 -> likeTweetIfPossible(tweetsAndRetweetsTweet, userId);
                case 2 -> retweetIfPossible(tweetsAndRetweetsTweet, userId);
                case 3 -> {
                    likeTweetIfPossible(tweetsAndRetweetsTweet, userId);
                    retweetIfPossible(tweetsAndRetweetsTweet, userId);
                }
                default -> System.err.println("Invalid choice!");
            }
        }

        System.out.println("End of Timeline!");
    }

    /**
     * To like a tweet if a tweet was not already liked by a user.
     *
     * @param tweet             Tweet which is to be checked and liked
     * @param userId            ID of the user
     */
    private void likeTweetIfPossible(final TweetsAndRetweets tweet, final long userId) {

        if (controller.isTweetLikedBy(tweet.tweetId(), userId)) {
            System.err.println("Tweet already liked!");
            return;
        }

        if (controller.likeTweet(tweet.tweetId(), userId)) {
            System.out.println("Tweet liked successfully");
        }
    }

    /**
     * To retweet a tweet if a tweet was not already retweeted by a user.
     *
     * @param tweet             Tweet which is to be checked and retweeted
     * @param userId            ID of the user
     */
    private void retweetIfPossible(final TweetsAndRetweets tweet, final long userId) {

        if (controller.isRetweetedBy(tweet.tweetId(), userId)) {
            System.err.println("Tweet already retweeted!");
            return;
        }

        if (controller.retweet(tweet.tweetId(), userId)) {
            System.out.println("Retweeted successfully");
        }
    }

}
