package org.twitter.follow.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.follow.exceptions.CountRetrievalException;
import org.twitter.follow.exceptions.FollowerNotAddedException;
import org.twitter.follow.exceptions.UnfollowException;
import org.twitter.follow.exceptions.UserRetrievalException;
import org.twitter.follow.model.Follower;
import org.twitter.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Acts as a repository to retrieve/update information between Database
 * and follow module.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class FollowRepositoryImpl implements FollowRepository {

    private static final String FOLLOW_QUERY = """
                                   INSERT INTO
                                   followers (followerId, followingId)
                                   values (?,?)""";
    private static final String UNFOLLOW_QUERY = """
                                    DELETE FROM followers
                                    WHERE followerId = ?
                                    AND followingId = ?""";
    private static final String FOLLOWING_QUERY = """
                                      SELECT u.*
                                      FROM users u
                                      JOIN followers f
                                      ON u.id = f.followingId
                                      WHERE f.followerId = ?""";
    private static final String FOLLOWING_COUNT_QUERY =
            "SELECT COUNT(*) FROM followers WHERE followerId = ?";
    private static final String FOLLOWERS_QUERY = """
                                      SELECT u.*
                                      FROM users u
                                      JOIN followers f
                                      ON u.id = f.followerId
                                      WHERE f.followingId = ?""";
    private static final String FOLLOWERS_COUNT_QUERY =
            "SELECT COUNT(*) FROM followers WHERE followingId = ?";
    private static final String NOT_FOLLOWED_BY_QUERY = """
                                    SELECT *
                                    FROM users
                                    WHERE id NOT IN
                                    (SELECT followingid
                                    FROM followers
                                    WHERE followerid = ?)
                                    AND id <> ?""";

    /**
     * To add a follower to the follower table in the database.
     *
     * @param follower              Follower Object with follower and following IDs
     */
    @Override
    public void addFollower(final Follower follower) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.
                     prepareStatement(FOLLOW_QUERY)) {

            prepareStatement.setLong(1, follower.getFollowerId());
            prepareStatement.setLong(2, follower.getFollowingId());
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new FollowerNotAddedException(exception.getMessage());
        }
    }

    /**
     * To unfollow a user.
     *
     * @param follower              Follower Object with follower and following IDs
     */
    @Override
    public void removeFollower(final Follower follower) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(UNFOLLOW_QUERY)) {

            prepareStatement.setLong(1, follower.getFollowerId());
            prepareStatement.setLong(2, follower.getFollowingId());
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new UnfollowException(exception.getMessage());
        }
    }

    /**
     * To retrieve the list of ID the user is following.
     *
     * @param followerId        Follower-ID
     * @return                  Collection of Users followed by the user
     */
    @Override
    public Collection<User> getFollowing(final long followerId) {
        return queryUser(FOLLOWING_QUERY, followerId);
    }

    /**
     * To get the following count.
     *
     * @param followerId        Follower-ID
     * @return                  Count of Following
     */
    @Override
    public int geFollowingCount(final long followerId) {
        return queryCount(FOLLOWING_COUNT_QUERY, followerId);
    }

    /**
     * To retrieve the list of users who are following the ID.
     *
     * @param followingId       Following-ID
     * @return                  Collection of users following the ID
     */
    @Override
    public Collection<User> getFollowers(final long followingId) {
        return queryUser(FOLLOWERS_QUERY, followingId);
    }

    /**
     * To get the follower count.
     *
     * @param followingId       Following-ID
     * @return                  Count of followers
     */
    @Override
    public int getFollowersCount(final long followingId) {
        return queryCount(FOLLOWERS_COUNT_QUERY, followingId);
    }

    @Override
    public Collection<User> getNotFollowedBy(final long followerId) {
        return queryUser(NOT_FOLLOWED_BY_QUERY, followerId);
    }

    /**
     * Private method which is just used to get the query and build user object collection.
     *
     * @param query                 Query to be executed
     * @param userId                ID of the User
     * @return                      Collection of users
     */
    private Collection<User> queryUser(final String query, final long userId) {
        final Collection<User> usersList = new ArrayList<>();

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            if (NOT_FOLLOWED_BY_QUERY.equals(query)) {
                prepareStatement.setLong(2, userId);
            }

            prepareStatement.setLong(1, userId);
            final ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                usersList.add(new User.UserBuilder()
                        .id(resultSet.getLong("id"))
                        .handle(resultSet.getString("handle"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .age(resultSet.getInt("age"))
                        .bio(resultSet.getString("bio"))
                        .build()
                );
            }

        } catch (SQLException exception) {
            throw new UserRetrievalException(exception.getMessage());
        }

        return usersList;
    }

    /**
     * Private method which is just used to process query and return count.
     *
     * @param query             Query to be executed
     * @param userId            ID of a user
     * @return                  Result of the query
     */
    private int queryCount(final String query, final long userId) {
        final int count;

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            prepareStatement.setLong(1, userId);
            final ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
                return count;
            }

        } catch (SQLException exception) {
            throw new CountRetrievalException(exception.getMessage());
        }

        return 0;
    }
}