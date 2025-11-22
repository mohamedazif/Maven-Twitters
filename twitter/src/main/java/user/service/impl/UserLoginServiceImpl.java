package user.service.impl;

import org.twitter.user.model.User;
import org.twitter.user.repository.UserRepository;
import org.twitter.user.service.UserLoginService;

import java.util.Optional;

/**
 * Checks credentials and logs in the user.
 *
 * @version                     1.0
 * @author                      Mohamed Abdul Azif
 */
public final class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository repository;

    public UserLoginServiceImpl(final UserRepository userRepository) {
        this.repository = userRepository;
    }
    /**
     * To check login of the users.
     *
     * @return          User object if produced UserId and Password matches the
     *                  registered User's UserId and Password else null
     */
    @Override
    public Optional<User> login(final String handle, final String password) {
        return repository.getUserByHandle(handle)
                .filter(user -> user.getPassword().equals(password));
    }
}
