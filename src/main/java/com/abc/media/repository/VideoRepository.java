package com.abc.media.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.media.entity.Channel;
import com.abc.media.entity.User;
import com.abc.media.entity.Video;

@Transactional
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

	List<Video> findBychannel(Channel channel);

	List<Video> findByTitle(String title);

	List<Video> findByVideoId(Long videoId);   



}

