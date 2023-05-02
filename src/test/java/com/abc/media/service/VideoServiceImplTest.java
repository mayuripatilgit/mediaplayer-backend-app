package com.abc.media.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import com.abc.media.dto.VideoDto;
import com.abc.media.entity.Channel;
import com.abc.media.entity.User;
import com.abc.media.entity.Video;
import com.abc.media.exception.ChannelNotFoundException;
import com.abc.media.exception.VideoNotFoundException;
import com.abc.media.repository.ChannelRepository;
import com.abc.media.repository.UserRepository;
import com.abc.media.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
public class VideoServiceImplTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ChannelRepository channelRepository;
    
    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private VideoServiceImpl videoService;

    private Video video;

    private Channel channel;

    private List<Video> videoList;

    @BeforeEach
    public void setUp() {
        video = new Video();
        video.setVideoId(1L);
        video.setTitle("Test Video");

        channel = new Channel();
        channel.setChannelId(1L);
        channel.setChannelName("Test Channel");

        videoList = new ArrayList<>();
        videoList.add(video);
    }



    @Test
    public void testUploadVideo() {
        when(videoRepository.save(any(Video.class))).thenReturn(video);
        Video result = videoService.uploadVideo(video);
        assertNotNull(result);
        assertEquals(video, result); 
    }



    @Test
    public void testGetAllVideos() {
        when(videoRepository.findAll()).thenReturn(videoList);
        List<Video> result = videoService.getAllVideos();
        assertNotNull(result);
        assertEquals(videoList, result);
    }

    @Test
    public void testDeleteVideo() throws VideoNotFoundException {
        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        videoService.deleteVideo(1L);
        verify(videoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteVideoNotFound() {
        when(videoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(VideoNotFoundException.class, () -> {
            videoService.deleteVideo(1L);
        });
    }

    @Test
    public void testSearchVideosByTitle() {
        when(videoRepository.findByTitle("Test Video")).thenReturn(videoList);
        List<Video> result = videoService.searchVideosByTitle("Test Video");
        assertNotNull(result);
        assertEquals(videoList, result);
    }

    @Test
    public void testSearchVideosByVideoId() {
        when(videoRepository.findByVideoId(1L)).thenReturn(videoList);
        List<Video> result = videoService.searchVideosByVideoId(1L);
        assertNotNull(result);
        assertEquals(videoList, result);
    }

}


