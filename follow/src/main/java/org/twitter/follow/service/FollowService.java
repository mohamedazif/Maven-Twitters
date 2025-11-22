package org.twitter.follow.service;

import org.twitter.follow.model.Follower;
import org.twitter.user.model.User;

import java.util.Collection;

/**
 * Interface for providing follow user related services.
 *
 * @version                 1.0
 * @author                  Mohamed Azif
 */
public interface FollowService {

    /**
     * To follow a user by userId.
     *
     * @param follower      Follower-ID
     * @return              True if FollowId exists also followed else false
     */
    boolean followById(final Follower follower);

    /**
     * To unfollow a user by ID.
     *
     * @param follower          Follower object containing follower-id and following-id
     * @return                  True if the user already followed the user and now unfollows else false
     */
    boolean unfollowById(final Follower follower);

    /**
     * To get the collection of users followed by the given ID.
     *
     * @param userId            Follower ID
     * @return                  Collection of users given ID is following
     */
    Collection<User> getFollowing(final long userId);

    /**
     * To get the collection of users following the given ID.
     *
     * @param userId            Following ID
     * @return                  Collection of users following the given ID
     */
    Collection<User> getFollowers(final long userId);

    /**
     * To suggest some other followers to follow.
     *
     * @param userId            Logged-in UserID
     * @return                  Collection of users to follow
     */
    Collection<User> suggestFollowers(final long userId);

    /**
     * To get the count of followers count of the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of followers
     */
    int getFollowersCount(final long userId);

    /**
     * To get the count of users followed by the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of following users
     */
    int getFollowingCount(final long userId);
}
