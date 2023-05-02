package com.abc.media.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.abc.media.exception.ChannelNotFoundException;
import com.abc.media.exception.UserNotFoundException;
import com.abc.media.repository.ChannelRepository;
import com.abc.media.repository.UserRepository;
import com.abc.media.service.ChannelService;
import com.abc.media.service.ChannelServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.abc.media.dto.ChannelDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import static org.mockito.BDDMockito.given;
public class ChannelServiceImplTest {

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ChannelService channelService = new ChannelServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetChannelsByChannelId_Success() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");

        List<Channel> channels = new ArrayList<>();
        Channel channel1 = new Channel();
        channel1.setChannelId(1L);
        channel1.setChannelName("Channel 1");
        channel1.setUser(user);
        Channel channel2 = new Channel();
        channel2.setChannelId(2L);
        channel2.setChannelName("Channel 2");
        channel2.setUser(user);
        channels.add(channel1);
        channels.add(channel2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(channelRepository.findByuser(user)).thenReturn(channels);

        List<Channel> result = channelService.getChannelsByUserId(1L);

        assertEquals(2, result.size());
        assertEquals("Channel 1", result.get(0).getChannelName());
        assertEquals("Channel 2", result.get(1).getChannelName());
    }

    @Test
    public void testGetChannelsByChannelId_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> channelService.getChannelsByUserId(1L));
    }

    @Test
    public void testGetChannelsByChannelId_ChannelNotFound() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(channelRepository.findByuser(user)).thenReturn(new ArrayList<>());

        assertThrows(ChannelNotFoundException.class, () -> channelService.getChannelsByUserId(1L));
    }


    @Test
    public void testDeleteChannelById() throws Exception {
        // Create a mock channel object
        Channel channel = new Channel();
        channel.setChannelId(1L);

        // Mock the channelRepository to return the mock channel when findById is called
        when(channelRepository.findById(anyLong())).thenReturn(Optional.of(channel));

        // Call the deleteChannelById method and assert that it returns a success message
        String message = channelService.deleteChannelById(1L);
        assertEquals("channel deleted ", message);
    }



    @Test
    public void testDeleteChannelById_ChannelNotFound() {
        when(channelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ChannelNotFoundException.class, () -> channelService.deleteChannelById(1L));
    }
    


   
   


    
}
