package timeline.model;

import java.time.LocalDateTime;

public record TweetsAndRetweets(int tweetId, String postedBy, String content, LocalDateTime postedAt, boolean isRetweet,
                                String retweetedBy, int likesCount, int retweetsCount) {

}