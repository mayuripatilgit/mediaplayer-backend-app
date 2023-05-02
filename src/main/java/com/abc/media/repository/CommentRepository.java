package com.abc.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.media.entity.Comment;
import com.abc.media.entity.Video;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	 

	List<Comment> findByVideo(Video video);

}
