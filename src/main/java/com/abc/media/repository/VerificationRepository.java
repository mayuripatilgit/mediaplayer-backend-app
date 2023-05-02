package com.abc.media.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.media.entity.Verification;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Integer> {

	Optional<Verification> findByUserName(String email);

	public void deleteByUserName(String userName);
	

}
