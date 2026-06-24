package com.workintech.twitterApi.service;

import com.workintech.twitterApi.entity.Like;

public interface LikeService {

    Like saveLike(Like like);

    void deleteLike(Long userId, Long tweetId);
}
