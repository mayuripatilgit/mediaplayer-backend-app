package com.abc.media.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abc.media.controller.CommentController;
import com.abc.media.dto.CommentDto;
import com.abc.media.entity.Comment;
import com.abc.media.service.CommentService;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private CommentDto commentDto;
    private Comment comment;
    private List<Comment> comments;

    @BeforeEach
    void setUp() throws Exception {
        commentDto = new CommentDto();
        commentDto.setText("Great video!");

        comment = new Comment();
        comment.setId(1L);
        comment.setText("Great video!");

        comments = new ArrayList<>();
        comments.add(comment);
    }

    @Test
    @DisplayName("Add Comment Test")
    void testAddComment() throws Exception {
        when(commentService.addComment(any(CommentDto.class))).thenReturn(comment);

        ResponseEntity<Comment> responseEntity = commentController.add(commentDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(comment);
    }

    @Test
    @DisplayName("Get Comments By Video Id Test")
    void testGetCommentsByVideoId() throws Exception {
        Long videoId = 1L;
        when(commentService.getAllCommentsByVideoId(videoId)).thenReturn(comments);

        ResponseEntity<List<Comment>> responseEntity = commentController.getCommentsByVideoId(videoId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(comments);
    }
}

