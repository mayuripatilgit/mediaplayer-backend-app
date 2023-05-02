package com.abc.media.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.abc.media.dto.ChannelDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.Video;
import com.abc.media.exception.UserNotFoundException;

public interface ChannelService {

	String deleteChannelById(Long channelId);

	Channel createChannel(ChannelDto channelDTO) throws IOException;

	List<Channel> getChannelsByUserId(Long userId);

}