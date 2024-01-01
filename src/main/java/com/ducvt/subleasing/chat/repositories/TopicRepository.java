package com.ducvt.subleasing.chat.repositories;

import com.ducvt.subleasing.chat.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByName(String name);
    Optional<List<Topic>> findByNameContains(String name);
}
