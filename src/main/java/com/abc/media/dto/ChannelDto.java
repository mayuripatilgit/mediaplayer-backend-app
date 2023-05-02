package com.abc.media.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.abc.media.entity.User;

public class ChannelDto {

    @NotBlank(message = "Channel name cannot be blank")
    private String channelName;

    @NotBlank(message = "About cannot be blank")
    private String about;

    @NotNull(message = "User id cannot be null")
    private Long userId;

    public MultipartFile getImage() {
		return image;
	}
 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    public void setImage(MultipartFile image) {
		this.image = image;
	}

	private MultipartFile image;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}



}
