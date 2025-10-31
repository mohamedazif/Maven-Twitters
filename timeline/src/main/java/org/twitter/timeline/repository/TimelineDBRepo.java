package org.twitter.timeline.repository;

import org.twitter.database.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TimelineDBRepo {

    public static Collection<Map<String, Object>> getTweetsAndRetweets(final String userId) {

        final Collection<Map<String, Object>> userTweets = new ArrayList<>();
        final String userTweetsQuery = """
                        (
                         SELECT
                            t.id as id,
                            t.userId as posted_by,
                            t.content as content,
                            t.createdAt as posted_at,
                            FALSE as is_retweet,
                            NULL as retweeted_userId,
                            COUNT(DISTINCT l.id) AS like_count,
                            COUNT(DISTINCT r2.id) AS retweet_count
                         FROM tweets t
                         LEFT JOIN likes l ON l.tweetId = t.id
                         LEFT JOIN retweets r2 ON r2.originalTweetId = t.id
                         WHERE t.userid = ?
                         GROUP BY t.id, t.userId, t.content, t.createdAt
                        )
                        UNION ALL
                        (
                         SELECT
                            t.id AS id,
                            t.userid AS posted_by,
                            t.content AS content,
                            r.retweetedAt AS posted_at,
                            TRUE AS is_retweet,
                            r.userid AS retweeted_userid,
                            COUNT(DISTINCT l.id) AS like_count,
                            COUNT(DISTINCT r2.id) AS retweet_count
                         FROM retweets r
                         JOIN tweets t ON t.id = r.originalTweetId
                         LEFT JOIN likes l ON l.id = t.id
                         LEFT JOIN retweets r2 ON r2.originalTweetId = t.id
                         WHERE r.userid = ?
                         GROUP BY t.id, t.userid, t.content, r.retweetedAt, r.userid
                        )
                        ORDER BY posted_at DESC""";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(userTweetsQuery)) {

            ps.setString(1, userId);
            ps.setString(2, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("tweetId", rs.getInt("id"));
                row.put("postedBy", rs.getString("posted_by"));
                row.put("content", rs.getString("content"));
                row.put("postedAt", rs.getTimestamp("posted_at").toLocalDateTime());
                row.put("isRetweet", rs.getBoolean("is_retweet"));
                row.put("retweetBy", rs.getString("retweeted_userid"));
                row.put("likesCount", rs.getInt("like_count"));
                row.put("retweetsCount", rs.getInt("retweet_count"));
                userTweets.add(row);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return userTweets;
    }
}
