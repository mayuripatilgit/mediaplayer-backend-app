package com.abc.media.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.media.dto.ChannelDto;
import com.abc.media.dto.CommentDto;
import com.abc.media.dto.UserDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.Comment;
import com.abc.media.entity.User;
import com.abc.media.entity.Video;
import com.abc.media.exception.UserNotFoundException;
import com.abc.media.exception.VideoNotFoundException;
import com.abc.media.repository.UserRepository;
import com.abc.media.repository.VideoRepository;
import com.abc.media.service.ChannelService;
import com.abc.media.service.CommentService;
import com.abc.media.service.UserService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private UserRepository userRepository;
	@PostMapping("/comment/save")
	public ResponseEntity<Comment> add(@Valid @RequestBody CommentDto commentdto) {
	    try {
	        Comment comment = commentService.addComment(commentdto);
	        return ResponseEntity.ok(comment);
	    } catch (VideoNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	@GetMapping("/comments/{videoId}")
	public ResponseEntity<List<Comment>> getCommentsByVideoId(@PathVariable Long videoId) throws VideoNotFoundException {
	List<Comment> comments = commentService.getAllCommentsByVideoId(videoId);
	return ResponseEntity.ok(comments);
	}
	


}
