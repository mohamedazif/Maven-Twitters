package org.twitter.service.interfaces;

import org.twitter.model.User;

public interface LoginService {
    User loginUser(String userId, String password);
}
