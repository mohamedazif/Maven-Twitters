package user.service;

import org.twitter.user.model.User;

/**
 * Interface to provide register a user service.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface UserRegisterService {

    boolean register(final User user);
}
