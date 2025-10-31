package org.twitter.repository;

import org.twitter.model.Tweet;
import org.twitter.repository.config.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TweetDBRepo {

    private TweetDBRepo() { }

    public static boolean addTweet(final Tweet tweet) {
        final String newTweet = """
                                INSERT INTO
                                tweets (id, userId, content, createdAt)
                                values (?, ?, ?, ?)""";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(newTweet)) {

            ps.setInt(1, tweet.getTweetId());
            ps.setString(2, tweet.getUserId());
            ps.setString(3, tweet.getTweetContent());
            ps.setTimestamp(4, Timestamp.valueOf(tweet.getCreatedAt()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Collection<Tweet> getSpecificUserTweets(final String userId) {
        final Collection<Tweet> userTweets = new ArrayList<>();
        final String getTweetsQuery = "SELECT * FROM tweets";

        try (Connection con = DBConnect.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(getTweetsQuery)) {

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
            return null;
        }
        return userTweets;
    }

    public static Collection<Tweet> getTimelineTweets(final String userId) {
        final Collection<Tweet> timelineTweets = new ArrayList<>();
        final String getTimelineQuery = "SELECT";
        return null;
    }
}
