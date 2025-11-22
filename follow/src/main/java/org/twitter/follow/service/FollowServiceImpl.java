package org.twitter.follow.service;

import org.twitter.follow.model.Follower;
import org.twitter.follow.repository.FollowRepository;
import org.twitter.user.model.User;
import org.twitter.user.service.UserRetrievalService;

import java.util.Collection;
import java.util.Objects;

/**
 * Provides services for the follow module.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class FollowServiceImpl implements FollowService {
    private final FollowRepository repository;
    private final UserRetrievalService userService;

    public FollowServiceImpl(final FollowRepository followRepository, final UserRetrievalService userService) {
        this.repository = followRepository;
        this.userService = userService;
    }

    /**
     * To check if the user-id registered or not.
     *
     * @param userId        User-Id to check
     * @return              True if registered else false
     */
    private boolean checkUser(final long userId) {
        return Objects.nonNull(userService.getUserById(userId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean followById(final Follower follower) {

        if (checkUser(follower.getFollowingId())) {
            repository.addFollower(follower);
        } else {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unfollowById(final Follower follower) {
        final boolean isFollowed = repository.getFollowing(follower.getFollowerId()).
                contains(userService.getUserById(follower.getFollowingId()));

        if (checkUser(follower.getFollowingId())) {

            if (isFollowed){
                repository.removeFollower(follower);
            } else {
                System.err.println("You are not a follower!");
            }

        } else {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> getFollowing(final long userId) {
        return repository.getFollowing(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> getFollowers(final long userId) {
        return repository.getFollowers(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFollowersCount(final long userId) {
        return repository.getFollowersCount(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFollowingCount(final long userId) {
        return repository.geFollowingCount(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> suggestFollowers(final long userId) {
        return repository.getNotFollowedBy(userId);
    }
}
