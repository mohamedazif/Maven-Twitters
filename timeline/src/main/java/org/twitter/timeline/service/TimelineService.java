package org.twitter.timeline.service;

import java.util.Collection;
import java.util.Map;

public interface TimelineService {
    Collection<Map<String, Object>> getTimelineTweets(String userId);
}
