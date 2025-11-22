package org.twitter.profile.controller;

import org.twitter.profile.service.ProfileService;
import org.twitter.timeline.model.TweetsAndRetweets;

import java.util.Collection;

/**
 * To coordinate the control between view and ProfileService.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class ProfileController {
    private final ProfileService service;

    public ProfileController(final ProfileService profileService) {
        this.service = profileService;
    }

    public int getFollowingCount(final long userId) {
        return service.getFollowingCount(userId);
    }

    public int getFollowersCount(final long userId) {
        return service.getFollowersCount(userId);
    }

    public Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId) {
        return service.getTweetsAndRetweets(userId);
    }

    public boolean deleteTweet(final int tweetId) {
        return service.deleteTweet(tweetId);
    }
}
