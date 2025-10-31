package org.twitter.follow.service;

import org.twitter.user.model.User;

import java.util.Collection;

public interface FollowService {
    boolean followById(String userId, String toFollowId);
    boolean unfollowById(String userId, String toUnfollowId);
    Collection<User> getFollowing(String userId);
    Collection<User> getFollowers(String userId);
    Collection<User> suggestFollowers(String userId);
    int getFollowersCount(String userId);
    int getFollowingCount(String userId);
}
