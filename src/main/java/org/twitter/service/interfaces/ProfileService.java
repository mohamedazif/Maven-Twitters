package org.twitter.service.interfaces;

import org.twitter.model.User;

import java.util.List;

public interface ProfileService {
    List<Object> getUserProfileTweets(User user);
}
