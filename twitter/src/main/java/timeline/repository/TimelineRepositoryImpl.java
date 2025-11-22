package timeline.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.timeline.exceptions.TweetNotLikedException;
import org.twitter.timeline.exceptions.TweetNotRetweetedException;
import org.twitter.timeline.exceptions.TweetRetweetRetrievalException;
import org.twitter.timeline.model.TweetsAndRetweets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TimelineRepositoryImpl implements TimelineRepository {

    private static final String USER_TWEETS_QUERY = """
                    (
                        SELECT
                           t.id as id,
                           u.handle as posted_by,
                           t.content as content,
                           t.createdAt as posted_at,
                           FALSE as is_retweet,
                           NULL as retweeted_userId,
                           COUNT(DISTINCT l.id) AS like_count,
                           COUNT(DISTINCT r2.id) AS retweet_count
                        FROM tweets t
                        JOIN users u ON u.id = t.userid
                        LEFT JOIN likes l ON l.tweetId = t.id
                        LEFT JOIN retweets r2 ON r2.originalTweetId = t.id
                        WHERE t.userid = ?
                        GROUP BY t.id, u.handle, t.content, t.createdAt
                       )
                       UNION ALL
                       (
                        SELECT
                           t.id AS id,
                           u.handle AS posted_by,
                           t.content AS content,
                           r.retweetedAt AS posted_at,
                           TRUE AS is_retweet,
                           r.retweetedby AS retweeted_userid,
                           COUNT(DISTINCT l.id) AS like_count,
                           COUNT(DISTINCT r2.id) AS retweet_count
                        FROM retweets r
                        JOIN tweets t ON t.id = r.originalTweetId
                        LEFT JOIN users u ON u.id = t.userid
                        LEFT JOIN likes l ON l.id = t.id
                        LEFT JOIN retweets r2 ON r2.originalTweetId = t.id
                        WHERE r.retweetedby = ?
                        GROUP BY t.id, u.handle, t.content, r.retweetedAt, r.retweetedby
                    )
                    ORDER BY posted_at DESC""";
    private static final String CHECK_RETWEET_QUERY = """
                                    SELECT retweetedBy, originalTweetId
                                    FROM retweets
                                    WHERE originalTweetId = ? AND retweetedBy = ?""";
    private static final String LIKED_BY_QUERY = """
                                    SELECT tweetId, likedBy
                                    FROM likes
                                    WHERE tweetId = ? AND likedBy = ?""";
    private static final String RETWEET_QUERY = """
                                  INSERT INTO
                                  retweets (retweetedBy, originalTweetID)
                                  values (?, ?)""";
    private static final String LIKE_QUERY = """
                                 INSERT INTO
                                 likes (tweetId, likedBy)
                                 values (?, ?)""";
    private static final String TIMELINE_TWEETS_QUERY = """
                        (
                         SELECT
                            t.id as id,
                            u.handle as posted_by,
                            t.content as content,
                            t.createdAt as posted_at,
                            FALSE as is_retweet,
                            NULL as retweeted_userId,
                            COUNT(DISTINCT l.id) AS like_count,
                            COUNT(DISTINCT r2.id) AS retweet_count
                         FROM tweets t
                         JOIN users u ON u.id = t.userid
                         LEFT JOIN likes l ON l.tweetId = t.id
                         LEFT JOIN retweets r2 ON r2.originalTweetId = t.id
                         WHERE t.userid IN (
                                            SELECT followingId
                                            FROM followers
                                            WHERE followerId = ?)
                         GROUP BY t.id, u.handle, t.content, t.createdAt
                        )
                        UNION ALL
                        (
                         SELECT
                            t.id AS id,
                            u.handle AS posted_by,
                            t.content AS content,
                            r.retweetedAt AS posted_at,
                            TRUE AS is_retweet,
                            r.retweetedby AS retweeted_userid,
                            COUNT(DISTINCT l.id) AS like_count,
                            COUNT(DISTINCT r2.id) AS retweet_count
                         FROM retweets r
                         JOIN tweets t ON t.id = r.originalTweetId
                         LEFT JOIN users u ON u.id = t.userid
                         LEFT JOIN likes l ON l.id = t.id
                         LEFT JOIN retweets r2 ON r2.originalTweetId = t.id
                         WHERE r.retweetedby IN (
                                            SELECT followingId
                                            FROM followers
                                            WHERE followerId = ?)
                         GROUP BY t.id, u.handle, t.content, r.retweetedAt, r.retweetedby
                        )
                        ORDER BY posted_at DESC""";

    /**
     * To retrieve a particular user's tweets and retweets.
     *
     * @param userId        ID of a User
     * @return              Collection of tweets in map format
     */
    @Override
    public Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId) {
        final Collection<TweetsAndRetweets> userTweets = new ArrayList<>();

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(USER_TWEETS_QUERY)) {

            prepareStatement.setLong(1, userId);
            prepareStatement.setLong(2, userId);
            final ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {

                userTweets.add(new TweetsAndRetweets(
                        resultSet.getInt("id"),
                        resultSet.getString("posted_by"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_at").toLocalDateTime(),
                        resultSet.getBoolean("is_retweet"),
                        resultSet.getString("retweeted_userid"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("retweet_count")
                ));
            }

        } catch (SQLException exception) {
            throw new TweetRetweetRetrievalException(exception.getMessage());
        }

        return userTweets;
    }

    /**
     * To check whether a tweet is retweeted by a user or not.
     *
     * @param tweetId       ID of the tweet to check
     * @param userId        User-ID who may have retweeted
     * @return              True if retweeted else false
     */
    @Override
    public boolean hasRetweeted(final int tweetId, final long userId) {

        try (final Connection con = DBConnect.getConnection();
             final PreparedStatement ps = con.prepareStatement(CHECK_RETWEET_QUERY)) {

            ps.setInt(1, tweetId);
            ps.setLong(2, userId);
            final ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException exception) {
            throw new TweetNotRetweetedException(exception.getMessage());
        }
    }

    /**
     * To check whether a tweet is liked by a user or not
     * @param tweetId       ID of the tweet to check
     * @param userId        User-ID who may have liked
     * @return              True if liked else false
     */
    @Override
    public boolean hasTweetLiked(final int tweetId, final long userId) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(LIKED_BY_QUERY)) {

            prepareStatement.setInt(1, tweetId);
            prepareStatement.setLong(2, userId);
            final ResultSet rs = prepareStatement.executeQuery();

            return rs.next();

        } catch (SQLException exception) {
            throw new TweetNotLikedException(exception.getMessage());
        }
    }

    /**
     * Add details of the tweet which is retweeted.
     *
     * @param retweetBy        User who retweeted
     * @param tweetId       ID of a tweet which is retweeted
     * @return              True if added else false
     */
    @Override
    public boolean retweet(final long retweetBy, final int tweetId) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(RETWEET_QUERY)) {

            prepareStatement.setLong(1, retweetBy);
            prepareStatement.setInt(2, tweetId);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new TweetNotRetweetedException(exception.getMessage());
        }

        return true;
    }

    /**
     * Add details of the tweet which is liked.
     *
     * @param tweetId       ID of a tweet which is liked
     * @param likedBy       User who liked the tweet
     * @return              True if added else false
     */
    @Override
    public boolean like(final int tweetId, final long likedBy) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(LIKE_QUERY)) {

            prepareStatement.setInt(1, tweetId);
            prepareStatement.setLong(2, likedBy);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new TweetNotLikedException(exception.getMessage());
        }

        return true;
    }

    /**
     * To get the timeline tweet for a particular user.
     *
     * @param userId            ID of the user.
     * @return                  Collection of Tweets and retweets
     */
    @Override
    public Collection<TweetsAndRetweets> getTimelineTweets(final long userId) {
        final Collection<TweetsAndRetweets> timelineTweets = new ArrayList<>();

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(TIMELINE_TWEETS_QUERY)) {

            prepareStatement.setLong(1, userId);
            prepareStatement.setLong(2, userId);
            final ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                timelineTweets.add(new TweetsAndRetweets(
                        resultSet.getInt("id"),
                        resultSet.getString("posted_by"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_at").toLocalDateTime(),
                        resultSet.getBoolean("is_retweet"),
                        resultSet.getString("retweeted_userid"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("retweet_count")
                ));
            }

        } catch (SQLException exception) {
            throw new TweetRetweetRetrievalException(exception.getMessage());
        }

        return timelineTweets;
    }
}
