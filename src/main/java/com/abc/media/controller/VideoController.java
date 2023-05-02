package com.abc.media.controller;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.media.dto.VideoDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.User;
import com.abc.media.entity.Video;
import com.abc.media.exception.VideoNotFoundException;
import com.abc.media.repository.ChannelRepository;
import com.abc.media.repository.UserRepository;
import com.abc.media.service.VideoService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/video")
public class VideoController {

	@Autowired
	private VideoService videoService;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private UserRepository userRepository;

	// video upload

	@PostMapping(value = "/videos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadVideo(@ModelAttribute VideoDto videoDTO) {
		try {

			String contentType = videoDTO.getVideoFile().getContentType();
			if (!contentType.equals(MediaType.APPLICATION_OCTET_STREAM_VALUE) && !contentType.equals("video/mp4")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only MP4 videos are allowed.");
			}

			Video video = new Video();
			video.setTitle(videoDTO.getTitle());
			video.setDescription(videoDTO.getDescription());
			video.setVideoFile(videoDTO.getVideoFile().getBytes());
		

			Optional<Channel> Optionalchannel = channelRepository.findById(videoDTO.getChannelId());
			Channel channel = Optionalchannel.get();
			video.setChannel(channel);

			Optional<User> Optionaluser = userRepository.findById(videoDTO.getUserId());
			User user = Optionaluser.get();
			video.setUser(user);

			video = videoService.uploadVideo(video);
			return ResponseEntity.ok(video);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/all")
	public List<Video> fetchAllVideos() {
		List<Video> list = videoService.getAllVideos();
		return list;
	}

	@DeleteMapping("/{videoId}")
	public ResponseEntity<String> removeVideo(@PathVariable("videoId") Long videoId) throws VideoNotFoundException {
		ResponseEntity<String> responseEntity = null;
		videoService.deleteVideo(videoId);
		responseEntity = new ResponseEntity<>("video deleted", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/search/title")
	public List<Video> searchVideosByTitle(@RequestParam("title") String title) {
		return videoService.searchVideosByTitle(title);
	}

	@GetMapping("/getByVideoId/{videoId}")
	public List<Video> searchVideosByVideoId(@PathVariable("videoId") Long videoId) {
		return videoService.searchVideosByVideoId(videoId);
	}

	@GetMapping("/video/getBychannelId/{channelId}")
	public ResponseEntity<List<Video>> fetchVideoById(@PathVariable("channelId") Long channelId)
			throws VideoNotFoundException {

		List<Video> video = videoService.getVideosByVideoId(channelId);
		ResponseEntity<List<Video>> responseEntity = new ResponseEntity<>(video, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/{id}/like")
	public ResponseEntity<Video> likeVideo(@PathVariable Long id) {
		Video video = videoService.likeVideo(id);
		if (video != null) {

			return ResponseEntity.ok(video);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/dislike")
	public ResponseEntity<Video> dislikeVideo(@PathVariable Long id) {
		Video video = videoService.dislikeVideo(id);
		if (video != null) {

			return ResponseEntity.ok(video);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	

}
