package org.twitter.follow.repository;

import org.twitter.follow.model.Follower;
import org.twitter.user.model.User;

import java.util.Collection;

/**
 * Interface providing abstraction between FollowService and Repository.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface FollowRepository {

    void addFollower(final Follower follower);

    void removeFollower(final Follower follower);

    Collection<User> getFollowing(final long followerId);

    Collection<User> getFollowers(final long followingId);

    int getFollowersCount(final long followingId);

    int geFollowingCount(final long followerId);

    Collection<User> getNotFollowedBy(final long followerId);
}
