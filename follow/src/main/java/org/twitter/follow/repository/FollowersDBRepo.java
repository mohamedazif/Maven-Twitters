package org.twitter.follow.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Acts as a repository to retrieve/update information between Database and follow module.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class FollowersDBRepo {

    private FollowersDBRepo() { }

    /**
     * To add a follower to the follower table in the database.
     *
     * @param userId            Follower-ID
     * @param followingId       Following-ID
     */
    public static void addFollower(final String userId, final String followingId) {
        final String followQuery = """
                                   INSERT INTO
                                   followers (followerId, followingId)
                                   values (?,?)""";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(followQuery)) {

            ps.setString(1, userId);
            ps.setString(2, followingId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * To unfollow a user.
     *
     * @param userId            Follower-ID
     * @param toUnfollowId      Following-ID
     */
    public static void removeFollower(final String userId, final String toUnfollowId) {
        final String removeQuery = "DELETE FROM followers WHERE user followerId = ? AND followingId = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(removeQuery)) {

            ps.setString(1, userId);
            ps.setString(2, toUnfollowId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To retrieve the list of ID the user is following.
     *
     * @param userId            Follower-ID
     * @return                  Collection of Users followed by the user
     */
    public static Collection<User> getFollowing(final String userId) {
        final String followingQuery = """
                                      SELECT u.*
                                      FROM users u
                                      JOIN followers f
                                      ON u.id = f.followingId
                                      WHERE f.followerId = ?""";

        return queryUser(followingQuery, userId);
    }

    /**
     * To retrieve the list of users who are following the ID.
     *
     * @param userId            Following-ID
     * @return                  Collection of users following the ID
     */
    public static Collection<User> getFollowers(final String userId) {
        final String followingQuery = """
                                      SELECT u.*
                                      FROM users u
                                      JOIN followers f
                                      ON u.id = f.followerId
                                      WHERE f.followingId = ?""";

        return queryUser(followingQuery, userId);
    }

    /**
     * Private method which is just used to get the query and build user object collection.
     *
     * @param query                 Query to be executed
     * @param userId                ID of the User
     * @return                      Collection of users
     */
    private static Collection<User> queryUser(final String query, final String userId) {
        final Collection<User> followList = new ArrayList<>();

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                followList.add(new User(
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("bio")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return followList;
    }

    /**
     * To get the follower count.
     *
     * @param userId            Following-ID
     * @return                  Count of followers
     */
    public static int getFollowersCount(final String userId) {
        final String followersQuery = "SELECT COUNT(*) FROM followers WHERE followingId = ?";
        return queryCount(followersQuery, userId);
    }

    /**
     * To get the following count.
     *
     * @param userId            Follower-ID
     * @return                  Count of Following
     */
    public static int getFollowingCount(final String userId) {
        final String followingQuery = "SELECT COUNT(*) FROM followers WHERE followerId = ?";
        return queryCount(followingQuery, userId);
    }

    /**
     * Private method which is just used to process query and return count.
     *
     * @param query             Query to be executed
     * @param userId            ID of a user
     * @return                  Result of the query
     */
    private static int queryCount(final String query, final String userId) {
        int count = 0;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            count = rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}
