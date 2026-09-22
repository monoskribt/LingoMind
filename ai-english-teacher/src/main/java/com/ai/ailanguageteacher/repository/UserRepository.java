package com.ai.ailanguageteacher.repository;

import com.ai.ailanguageteacher.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ChatUser, Long> {

    Optional<ChatUser> findByEmail(String email);

}
