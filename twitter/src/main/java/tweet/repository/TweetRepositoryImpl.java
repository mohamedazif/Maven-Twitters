package tweet.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.tweet.exceptions.TweetNotDeletedException;
import org.twitter.tweet.exceptions.TweetNotPostedException;
import org.twitter.tweet.model.Tweet;
import org.twitter.tweet.repository.TweetRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Repository for store, retrieve and remove tweets.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class TweetRepositoryImpl implements TweetRepository {


    private static final String INSERT_TWEET = """
                                INSERT INTO
                                tweets (userId, content)
                                values (?, ?)""";
    private static final String DELETE_TWEET = "DELETE FROM tweets WHERE id = ?";

    /**
     * Add a tweet to the Twitter database.
     *
     * @param tweet         Created tweeted object
     * @return              True if added else false
     */
    @Override
    public boolean post(final Tweet tweet) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(INSERT_TWEET)) {

            prepareStatement.setLong(1, tweet.getUserId());
            prepareStatement.setString(2, tweet.getTweetContent());
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new TweetNotPostedException(exception.getMessage());
        }

        return true;
    }

    /**
     * To delete a tweet.
     *
     * @param tweetId       ID of the tweet to be deleted
     * @return              True if deleted else false
     */
    @Override
    public boolean delete(final int tweetId) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(DELETE_TWEET)) {

            prepareStatement.setInt(1, tweetId);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new TweetNotDeletedException(exception.getMessage());
        }

        return true;
    }
}
