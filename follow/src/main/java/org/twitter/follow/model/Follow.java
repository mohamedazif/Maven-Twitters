package org.twitter.follow.model;

/**
 * Model for the follow table
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class Follow {

    private final String userId;
    private final String followingId;

    public Follow(final String userId, final String followingId) {
        this.userId = userId;
        this.followingId = followingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFollowingId() {
        return followingId;
    }
}
