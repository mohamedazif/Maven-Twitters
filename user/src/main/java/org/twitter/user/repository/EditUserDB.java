package org.twitter.user.repository;

import org.twitter.database.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Editing/Deleting the user and their details.
 *
 * @version         1.0
 * @author          Mohamed Abdul Azif
 */
public final class EditUserDB {

    private EditUserDB() { }

    /**
     * To update the name of the user.
     *
     * @param userId        ID of the user
     * @param userName      Name to change
     */
    public static void updateUsername(final String userId, final String userName) {
        final String updateNameQuery = "UPDATE users SET name = ? WHERE id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(updateNameQuery)) {

            ps.setString(1, userName);
            ps.setString(2, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * To update the password of the user.
     *
     * @param userId        ID of the user
     * @param password      Password to change
     */
    public static void updatePassword(final String userId, final String password) {
        final String updateNameQuery = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(updateNameQuery)) {

            ps.setString(1, password);
            ps.setString(2, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * To update the age of the user.
     *
     * @param userId        ID of the user
     * @param age           Age to change
     */
    public static void updateAge(final String userId, final int age) {
        final String updateNameQuery = "UPDATE users SET age = ? WHERE id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(updateNameQuery)) {

            ps.setInt(1, age);
            ps.setString(2, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * To update the biography of the user.
     *
     * @param userId        ID of the user
     * @param bio           Bio to change
     */
    public static void updateBio(final String userId, final String bio) {
        final String updateNameQuery = "UPDATE users SET bio = ? WHERE id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(updateNameQuery)) {

            ps.setString(1, bio);
            ps.setString(2, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * To delete a user.
     *
     * @param userId        ID of the user
     */
    public static void deleteUser(final String userId) {
        final String deleteQuery = "DELETE FROM users WHERE id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {

            ps.setString(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
