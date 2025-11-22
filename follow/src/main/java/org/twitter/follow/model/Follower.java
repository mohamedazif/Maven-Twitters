package org.twitter.follow.model;

/**
 * Model for the follow table
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class Follower {

    private final long followerId;
    private final long followingId;

    public Follower(final long followerId, final long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public long getFollowerId() {
        return followerId;
    }

    public long getFollowingId() {
        return followingId;
    }
}