package org.twitter.timeline.service;

import org.twitter.follow.repository.FollowersDBRepo;
import org.twitter.timeline.repository.TimelineDBRepo;
import org.twitter.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Timeline implements TimelineService {

    @Override
    public Collection<Map<String, Object>> getTimelineTweets(final String userId) {

        final Collection<Map<String, Object>> timelineTweets = new ArrayList<>();

        for (final User followingUser : FollowersDBRepo.getFollowing(userId)) {
            timelineTweets.addAll(TimelineDBRepo.getTweetsAndRetweets(followingUser.getId()));
        }

        return timelineTweets.stream()
                .sorted((a, b) -> ((LocalDateTime)b.get("postedAt"))
                        .compareTo((LocalDateTime)a.get("postedAt"))).toList();
    }
}
