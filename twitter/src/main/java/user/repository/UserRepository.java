package user.repository;

import org.twitter.user.model.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for connecting to Database to do user related operations.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface UserRepository {
    boolean registerUser(final User user);

    Optional<User> getUserByHandle(final String handle);

    Optional<User> getUserById(final long id);

    Collection<User> getAllUsers();
}
