package com.abc.media.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class CommentDto {

    
    @NotBlank
    private String text;

    private LocalDateTime createdAt;

    private Long userId;

	public Long getVideoId() {
		return videoId;
	}





	private Long videoId;


	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
	

	
    public CommentDto() {
    }
}