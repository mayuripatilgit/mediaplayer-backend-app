package com.abc.media.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.media.entity.Channel;
import com.abc.media.entity.User;

@Repository
@Transactional
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    

	List<Channel> findByuser(User user);

	

}
	