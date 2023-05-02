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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.media.dto.ChannelDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.User;
import com.abc.media.repository.ChannelRepository;
import com.abc.media.repository.UserRepository;
import com.abc.media.service.ChannelService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ChannelController {

	@Autowired
	private ChannelService channelService;



	@PostMapping("/create")
    public ResponseEntity<?> createChannel(@RequestBody ChannelDto channelDTO) throws IOException {
        Channel channel = channelService.createChannel(channelDTO);
        return new ResponseEntity<>(channel, HttpStatus.CREATED);
    }

	
	
	@GetMapping("/channel/getByUserId/{userId}")
	public ResponseEntity<List<Channel>> fetchChannelById(@PathVariable("userId") Long userId) {

		List<Channel> channel = channelService.getChannelsByUserId(userId);
		ResponseEntity<List<Channel>> responseEntity = new ResponseEntity<>(channel, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/channel/deleteByChannelId/{channelId}")
	public ResponseEntity<String> CancelChannel(@PathVariable("channelId") Long channelId) {

		String response = channelService.deleteChannelById(channelId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		return responseEntity;
	}

}
