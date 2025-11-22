package user.repository;

import org.twitter.database.config.DBConnect;
import org.twitter.user.exceptions.RegistrationFailedException;
import org.twitter.user.exceptions.UserNotFoundException;
import org.twitter.user.model.User;
import org.twitter.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Repository for storing and retrieving user information
 *
 * @version         1.0
 * @author          Mohamed Abdul Azif
 */
public class UserRepositoryImpl implements UserRepository {
    private static final Collection<String> handles = new ArrayList<>();
    private static final Collection<String> email = new ArrayList<>();
    private static final String REGISTER_QUERY = """
                                INSERT INTO
                                users (handle, email, name, password, age, bio)
                                values (?, ?, ?, ?, ?, ?)""";
    private static final String GET_USER_BY_HANDLE_QUERY =
            "SELECT id, handle, email, name, password, age, bio FROM users WHERE handle = ?";
    private static final String GET_USER_BY_ID_QUERY =
            "SELECT id, handle, email, name, password, age, bio FROM users WHERE id = ?";
    private static final String ALL_USERS_QUERY =
            "SELECT id, handle, email, name, password, age, bio FROM users";

    /**
     * To add a user to the database.
     *
     * @param user      Information about the user
     * @return          True if registered otherwise false
     */
    @Override
    public boolean registerUser(final User user) {
        // Check if userId is already in use
        if (handles.contains(user.getHandle())) {
            System.err.println("User ID already exists!");
            return false;
        }

        // Check if email is already in use
        if (email.contains(user.getEmail())) {
            System.err.println("Email is already registered!");
            return false;
        }

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(REGISTER_QUERY)) {

            prepareStatement.setString(1, user.getHandle());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setString(3, user.getName());
            prepareStatement.setString(4, user.getPassword());
            prepareStatement.setInt(5, user.getAge());
            prepareStatement.setString(6, user.getBio());
            prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RegistrationFailedException(exception.getMessage());
        }

        handles.add(user.getHandle());
        email.add(user.getEmail());

        return true;
    }

    /**
     * To retrieve the details of the specific user.
     *
     * @param handle        User's ID to retrieve
     * @return          User Object if found else null
     */
    @Override
    public Optional<User> getUserByHandle(final String handle) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_HANDLE_QUERY)) {

            prepareStatement.setString(1, handle);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new User.UserBuilder()
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
            throw new UserNotFoundException(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(final long id) {

        try (final Connection connection = DBConnect.getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY)) {

            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new User.UserBuilder()
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
            throw new UserNotFoundException(exception.getMessage());
        }
        return Optional.empty();
    }

    /**
     * To retrieve list of every user.
     *
     * @return          Collection of all users.
     */
    @Override
    public Collection<User> getAllUsers() {
        final Collection<User> usersList = new ArrayList<>();

        try (final Connection connection = DBConnect.getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(ALL_USERS_QUERY)){

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
            throw new UserNotFoundException(exception.getMessage());
        }

        return usersList;
    }
}
