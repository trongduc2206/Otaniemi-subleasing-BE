package com.ducvt.subleasing.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ducvt.subleasing.account.models.User;

import javax.swing.text.html.Option;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsernameOrEmail(String username, String email);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Page<User> findAllBy(Pageable pageable);

  Page<User> findByUsernameContains(String username, Pageable pageable);

}
