package org.twitter.service.interfaces;

import org.twitter.model.Tweet;
import org.twitter.model.User;

import java.util.Collection;

public interface TimelineService {
    Collection<Tweet> getTimelineTweets(User user);

    boolean likeTweet(User user, Tweet tweet);

    boolean retweet(User user, Tweet tweet);
}
