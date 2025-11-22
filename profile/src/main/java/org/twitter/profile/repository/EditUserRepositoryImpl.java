package org.twitter.profile.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.profile.exceptions.UserAccountDeleteException;
import org.twitter.profile.exceptions.UserProfileEditException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Editing/Deleting the user and their details.
 *
 * @version         1.0
 * @author          Mohamed Abdul Azif
 */
public class EditUserRepositoryImpl implements EditUserRepository {

    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_AGE_QUERY = "UPDATE users SET age = ? WHERE id = ?";
    private static final String UPDATE_BIO_QUERY = "UPDATE users SET bio = ? WHERE id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE id = ?";
    private static final String UPDATE_NAME_QUERY = "UPDATE users SET name = ? WHERE id = ?";

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(final long id) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY)) {

            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new UserAccountDeleteException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAge(final long id, final int updatedAge) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_AGE_QUERY)) {

            prepareStatement.setInt(1, updatedAge);
            prepareStatement.setLong(2, id);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new UserProfileEditException("Cannot update age!", exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBio(final long id, final String updatedBio) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_BIO_QUERY)) {

            prepareStatement.setString(1, updatedBio);
            prepareStatement.setLong(2, id);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new UserProfileEditException("Cannot update bio of the user!", exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePassword(final long id, final String updatedPassword) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_PASSWORD_QUERY)) {

            prepareStatement.setString(1, updatedPassword);
            prepareStatement.setLong(2, id);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new UserProfileEditException("Cannot change Password!", exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUsername(final long id, final String updatedName) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_NAME_QUERY)) {

            prepareStatement.setString(1, updatedName);
            prepareStatement.setLong(2, id);
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new UserProfileEditException("Cannot update username!", exception);
        }
    }
}
