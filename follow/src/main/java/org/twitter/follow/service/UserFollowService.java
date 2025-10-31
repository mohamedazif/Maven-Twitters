package org.twitter.follow.service;

import org.twitter.follow.repository.FollowersDBRepo;
import org.twitter.user.model.User;
import org.twitter.user.repository.UserDBRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Provides services for the follow module.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class UserFollowService implements FollowService {

    /**
     * To check if the user-id registered or not.
     *
     * @param userId        User-Id to check
     * @return              True if registered else false
     */
    private boolean checkUser(final String userId) {
        return Objects.nonNull(UserDBRepo.getSpecificUser(userId));
    }

    /**
     * To follow a user by ID
     *
     * @param userId            Follower-ID
     * @param toFollowId        To Follow-ID
     * @return                  True if toFollowId exists and followed else false
     */
    @Override
    public boolean followById(final String userId, final String toFollowId) {

        if (checkUser(toFollowId)) {
            FollowersDBRepo.addFollower(userId, toFollowId);
        } else {
            return false;
        }

        return true;
    }

    /**
     * To unfollow a user by ID.
     *
     * @param userId            Follower-ID
     * @param toUnfollowId      To UnFollow-ID
     * @return                  True if the user already followed the user and now unfollows else false
     */
    @Override
    public boolean unfollowById(final String userId, final String toUnfollowId) {

        boolean isFollowed = FollowersDBRepo.getFollowing(userId).
                contains(UserDBRepo.getSpecificUser(toUnfollowId));

        if (checkUser(toUnfollowId)) {

            if (isFollowed){
                FollowersDBRepo.removeFollower(userId, toUnfollowId);
            } else {
                System.err.println("You are not a follower!");
            }

        } else {
            return false;
        }

        return true;
    }

    /**
     * To get the collection of users followed by the given ID.
     *
     * @param userId            Follower ID
     * @return                  Collection of users given ID is following
     */
    @Override
    public Collection<User> getFollowing(final String userId) {
        return FollowersDBRepo.getFollowing(userId);
    }

    /**
     * To get the collection of users following the given ID.
     *
     * @param userId            Following ID
     * @return                  Collection of users following the given ID
     */
    @Override
    public Collection<User> getFollowers(final String userId) {
        return FollowersDBRepo.getFollowers(userId);
    }

    /**
     * To get the count of followers count of the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of followers
     */
    @Override
    public int getFollowersCount(final String userId) {
        return FollowersDBRepo.getFollowersCount(userId);
    }

    /**
     * To get the count of users followed by the given ID.
     *
     * @param userId            ID of a User
     * @return                  Count of following users
     */
    @Override
    public int getFollowingCount(final String userId) {
        return FollowersDBRepo.getFollowingCount(userId);
    }

    /**
     * To suggest some other followers to follow.
     *
     * @param userId            Logged-in UserID
     * @return                  Collection of users to follow
     */
    @Override
    public Collection<User> suggestFollowers(final String userId) {
        Collection<User> followingList = FollowersDBRepo.getFollowing(userId);
        Collection<User> suggestionList = new ArrayList<>();

        for (User suggestUser :  UserDBRepo.getAllUsers()) {
            if (followingList.contains(suggestUser)) {
                continue;
            }
            suggestionList.add(suggestUser);
        }

        return suggestionList;
    }
}
