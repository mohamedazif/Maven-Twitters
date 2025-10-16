package org.twitter.service.interfaces;

import org.twitter.model.User;

public interface EditService {
    void changeUsername(User user, String newUsername);
    void changePassword(User user, String newPassword);
    void changeAge(User user, int newAge);
    void changeBio(User user, String newBio);
}
