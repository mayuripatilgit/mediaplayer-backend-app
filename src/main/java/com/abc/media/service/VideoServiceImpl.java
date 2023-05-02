package com.abc.media.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.media.entity.Channel;

import com.abc.media.entity.Video;
import com.abc.media.exception.ChannelNotFoundException;

import com.abc.media.exception.VideoNotFoundException;
import com.abc.media.repository.ChannelRepository;
import com.abc.media.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private ChannelRepository channelRepository;

	

	@Override
	public Video uploadVideo(Video video) {
		return videoRepository.save(video);
	}

	@Override
	public List<Video> getAllVideos() {

		List<Video> video = videoRepository.findAll();
		return video;
	}

	@Override
	public void deleteVideo(Long videoId) throws VideoNotFoundException {
		Optional<Video> optionalVideo = videoRepository.findById(videoId);
		if (optionalVideo.isEmpty()) {
			throw new VideoNotFoundException("video not found with id :" + videoId);

		}
		videoRepository.deleteById(videoId);
	}

	@Override
	public List<Video> searchVideosByTitle(String title) {

		return videoRepository.findByTitle(title);
	}

	@Override
	public void saveVideo(Video video) {
		videoRepository.save(video);
	}

	@Override
	public Video likeVideo(Long id) {
		Optional<Video> optionalVideo = videoRepository.findById(id);
		if (optionalVideo.isPresent()) {
			Video video = optionalVideo.get();
			video.setLikes(video.getLikes() + 1);
			return videoRepository.save(video);
		} else {
			return null;
		}
	}

	@Override
	public Video dislikeVideo(Long id) {
		Optional<Video> optionalVideo = videoRepository.findById(id);
		if (optionalVideo.isPresent()) {
			Video video = optionalVideo.get();
			video.setDislikes(video.getDislikes() + 1);
			return videoRepository.save(video);
		} else {
			return null;
		}
	}

	public int getLikes() {

		Video video = videoRepository.findById(1L).orElse(null);
		if (video != null) {
			return video.getLikes();
		} else {

			return 0;
		}
	}

	public int getDislikes() {

		Video video = videoRepository.findById(1L).orElse(null);
		if (video != null) {
			return video.getDislikes();
		} else {

			return 0;
		}
	}

	
	@Override
	public List<Video> searchVideosByVideoId(Long videoId) {

		return videoRepository.findByVideoId(videoId);
	}


	@Override
	public List<Video> getVideosByVideoId(Long channelId) throws VideoNotFoundException {
		Optional<Channel> optionalChannel = channelRepository.findById(channelId);
		if (optionalChannel.isEmpty()) {
			throw new ChannelNotFoundException("There are no video with this Id :" + channelId);
		}
		Channel channel = optionalChannel.get();
		List<Video> list = videoRepository.findBychannel(channel);
		if (list.isEmpty()) {
			throw new VideoNotFoundException("There are no video for the the Id :" + channelId);
		}
		return list;
	}

}
