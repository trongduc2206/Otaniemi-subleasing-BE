package com.ducvt.subleasing.chat.repositories;

import com.ducvt.subleasing.chat.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findByTopic(Long topic);
}
