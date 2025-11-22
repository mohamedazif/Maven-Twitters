package org.twitter.profile.service;

import org.twitter.follow.service.FollowService;
import org.twitter.timeline.model.TweetsAndRetweets;
import org.twitter.timeline.service.TimelineService;
import org.twitter.tweet.service.TweetService;

import java.util.Collection;

public class ProfileServiceImpl implements ProfileService {
    private final FollowService followService;
    private final TimelineService timelineService;
    private final TweetService tweetService;

    public ProfileServiceImpl(final FollowService followService, final TimelineService timelineService,
                              final TweetService tweetService) {
        this.followService = followService;
        this.timelineService = timelineService;
        this.tweetService = tweetService;
    }

    @Override
    public int getFollowingCount(final long userId) {
        return followService.getFollowingCount(userId);
    }

    @Override
    public int getFollowersCount(final long userId) {
        return followService.getFollowersCount(userId);
    }

    @Override
    public Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId) {
        return timelineService.getTweetsAndRetweets(userId);
    }

    @Override
    public boolean deleteTweet(final int tweetId) {
        return tweetService.delete(tweetId);
    }
}
