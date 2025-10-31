package org.twitter.tweet.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.tweet.model.Retweet;
import org.twitter.tweet.model.Tweet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TweetDBRepo {

    private TweetDBRepo() { }

    public static boolean addTweet(final Tweet tweet) {
        final String newTweet = """
                                INSERT INTO
                                tweets (userId, content)
                                values (?, ?)""";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(newTweet)) {

            ps.setString(1, tweet.getUserId());
            ps.setString(2, tweet.getTweetContent());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean addRetweet(final Retweet retweet) {
        final String newRetweet = """
                                  INSERT INTO
                                  retweets (userId, originalTweetID)
                                  values (?, ?)""";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(newRetweet)) {

            ps.setString(1, retweet.getRetweetedUserId());
            ps.setInt(2, retweet.getOgTweet().getTweetId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean likeTweet(final int tweetId, final String likedBy) {
        final String likeQuery = """
                                 INSERT INTO
                                 likes (tweetId, likedBy)
                                 values (?, ?)""";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(likeQuery)) {

            ps.setInt(1, tweetId);
            ps.setString(2, likedBy);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static Collection<Tweet> getSpecificUserTweets(final String userId) {
        final Collection<Tweet> userTweets = new ArrayList<>();
        final String getTweetsQuery = "SELECT * FROM tweets WHERE userId = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(getTweetsQuery)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userTweets.add(new Tweet(
                        rs.getInt("id"),
                        rs.getString("userId"),
                        rs.getString("content"),
                        rs.getTimestamp("createdAt").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userTweets;
    }

    public static boolean deleteTweet(final int tweetId) {
        final String deleteQuery = "DELETE FROM tweets WHERE id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {

            ps.setInt(1, tweetId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }
}
