package com.abc.media.service;

import java.io.IOException;
import java.util.List;

import java.util.Optional;

import com.abc.media.dto.VideoDto;
import com.abc.media.entity.Video;

import com.abc.media.exception.VideoNotFoundException;

public interface VideoService {

	List<Video> getAllVideos();

	void deleteVideo(Long videoId) throws VideoNotFoundException;

	List<Video> searchVideosByTitle(String title);

	List<Video> searchVideosByVideoId(Long VideoId);

	Video likeVideo(Long videoId);

	List<Video> getVideosByVideoId(Long channelId) throws VideoNotFoundException;

	void saveVideo(Video video);

	Video dislikeVideo(Long id);

	Video uploadVideo(Video video);

}
