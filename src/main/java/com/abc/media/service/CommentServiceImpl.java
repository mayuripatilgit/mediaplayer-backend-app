package com.abc.media.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc.media.dto.CommentDto;
import com.abc.media.dto.UserDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.Comment;
import com.abc.media.entity.User;
import com.abc.media.entity.Video;
import com.abc.media.exception.ChannelNotFoundException;

import com.abc.media.exception.UserNotFoundException;
import com.abc.media.exception.VideoNotFoundException;
import com.abc.media.repository.CommentRepository;
import com.abc.media.repository.UserRepository;
import com.abc.media.repository.VideoRepository;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Comment addComment(CommentDto commentDto) throws VideoNotFoundException, UserNotFoundException {
	    try {
	        Comment comment = new Comment();
	        comment.setText(commentDto.getText());
	        comment.setCreatedAt(commentDto.getCreatedAt());

	        Optional<Video> optionalVideo = videoRepository.findById(commentDto.getVideoId());
	        if (!optionalVideo.isPresent()) {
	            throw new VideoNotFoundException("Video not found with id: " + commentDto.getVideoId());
	        }
	        Video video = optionalVideo.get();
	        comment.setVideo(video);

	        Optional<User> optionalUser = userRepository.findById(commentDto.getUserId());
	        if (!optionalUser.isPresent()) {
	            throw new UserNotFoundException("User not found with id: " + commentDto.getUserId());
	        }
	        User user = optionalUser.get();
	        comment.setUser(user);

	        comment = commentRepository.save(comment);
	        return comment;
	    } catch (Exception e) {
	        e.printStackTrace();//where the exception occurred
	        throw new RuntimeException("Failed to save comment: " + e.getMessage());
	    }
	}

	@Override
	public List<Comment> getAllCommentsByVideoId(Long videoId) throws VideoNotFoundException {
		Optional<Video> optionalVideo = videoRepository.findById(videoId);
	    if (!optionalVideo.isPresent()) {
	        throw new VideoNotFoundException("Video not found with id: " + videoId);
	    }
	    Video video = optionalVideo.get();
	    return commentRepository.findByVideo(video);
	}

}