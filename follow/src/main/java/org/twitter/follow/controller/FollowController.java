package org.twitter.follow.controller;

import org.twitter.follow.model.Follow;
import org.twitter.follow.service.FollowService;
import org.twitter.user.model.User;

import java.util.Collection;

/**
 * To coordinate the control between the follow view and service.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class FollowController {

    private final FollowService followService;

    public FollowController(final FollowService followService) {
        this.followService = followService;
    }

    /**
     * To follow a user by ID
     *
     * @param follow            Object of Follow with Follower and Following IDs
     * @return                  True if toFollowId exists and followed else false
     */
    public boolean followById(final Follow follow) {
        return followService.followById(follow.getUserId(), follow.getFollowingId());
    }

    /**
     * To unfollow a user by ID.
     *
     * @param unfollow          Object of Follow with Follower and Following IDs
     * @return                  True if the user already followed the user and now unfollows else false
     */
    public boolean unfollowById(final Follow unfollow) {
        return followService.unfollowById(unfollow.getUserId(), unfollow.getFollowingId());
    }

    /**
     * To get the collection of users followed by the given ID.
     *
     * @param userId            Follower ID
     * @return                  Collection of users given ID is following
     */
    public Collection<User> getFollowing(final String userId) {
        return followService.getFollowing(userId);
    }

    /**
     * To get the collection of users following the given ID.
     *
     * @param userId            Following ID
     * @return                  Collection of users following the given ID
     */
    public Collection<User> getFollowers(final String userId) {
        return followService.getFollowers(userId);
    }

    /**
     * To get the count of followers count of the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of followers
     */
    public int getFollowersCount(final String userId) {
        return followService.getFollowersCount(userId);
    }

    /**
     * To get the count of users followed by the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of following users
     */
    public int getFollowingCount(final String userId) {
        return followService.getFollowingCount(userId);
    }

    /**
     * To suggest some other followers to follow.
     *
     * @param userId            Logged-in UserID
     * @return                  Collection of users to follow
     */
    public Collection<User> suggestFollowers(final String userId) {
        return followService.suggestFollowers(userId);
    }
}
