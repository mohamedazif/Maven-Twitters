package org.twitter.follow.controller;

import org.twitter.follow.model.Follower;
import org.twitter.follow.service.FollowService;
import org.twitter.user.model.User;

import java.util.Collection;

/**
 * To coordinate the control between the follow view and service.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class FollowerController {

    private final FollowService followService;

    public FollowerController(final FollowService followService) {
        this.followService = followService;
    }

    /**
     * To follow a user by ID
     *
     * @param followerId        Follower User-ID
     * @param followingId       Following User-ID
     * @return                  True if toFollowId exists and followed else false
     */
    public boolean followById(final long followerId, final long followingId) {
        return followService.followById(new Follower(followerId, followingId));
    }

    /**
     * To unfollow a user by ID.
     *
     * @param followerId        Follower User-ID
     * @param followingId       Following User-ID
     * @return                  True if the user already followed the user and now unfollows else false
     */
    public boolean unfollowById(final long followerId, final long followingId) {
        return followService.unfollowById(new Follower(followerId, followingId));
    }

    /**
     * To get the collection of users followed by the given ID.
     *
     * @param userId            Follower ID
     * @return                  Collection of users given ID is following
     */
    public Collection<User> getFollowing(final long userId) {
        return followService.getFollowing(userId);
    }

    /**
     * To get the collection of users following the given ID.
     *
     * @param userId            Following ID
     * @return                  Collection of users following the given ID
     */
    public Collection<User> getFollowers(final long userId) {
        return followService.getFollowers(userId);
    }

    /**
     * To get the count of followers count of the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of followers
     */
    public int getFollowersCount(final long userId) {
        return followService.getFollowersCount(userId);
    }

    /**
     * To get the count of users followed by the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of following users
     */
    public int getFollowingCount(final long userId) {
        return followService.getFollowingCount(userId);
    }

    /**
     * To suggest some other followers to follow.
     *
     * @param userId            Logged-in UserID
     * @return                  Collection of users to follow
     */
    public Collection<User> suggestFollowers(final long userId) {
        return followService.suggestFollowers(userId);
    }
}
