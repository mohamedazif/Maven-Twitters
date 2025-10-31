package org.twitter.user.service;

import org.twitter.user.model.User;

public interface LoginService {
    User loginUser(String userId, String password);
}
