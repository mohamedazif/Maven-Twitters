package org.twitter.user.service;

import org.twitter.user.model.User;

public interface EditProfileService {
    void changeUsername(User user, String newUsername);
    void changePassword(User user, String newPassword);
    void changeAge(User user, int newAge);
    void changeBio(User user, String newBio);
    void removeAccount(User user);
}
