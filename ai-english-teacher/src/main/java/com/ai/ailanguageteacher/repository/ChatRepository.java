package com.ai.ailanguageteacher.repository;

import com.ai.ailanguageteacher.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByChatUserId(Long userId);

}
