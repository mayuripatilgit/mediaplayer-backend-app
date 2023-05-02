package com.abc.media.service;

import java.util.List;

import com.abc.media.dto.CommentDto;
import com.abc.media.dto.UserDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.Comment;
import com.abc.media.entity.Video;
import com.abc.media.exception.UserNotFoundException;
import com.abc.media.exception.VideoNotFoundException;

public interface CommentService {

	Comment addComment(CommentDto commentDto) throws VideoNotFoundException, UserNotFoundException;
    List<Comment> getAllCommentsByVideoId(Long videoId) throws VideoNotFoundException;
	
}