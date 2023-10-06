package com.ducvt.subleasing.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ducvt.subleasing.account.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsernameAndStatus(String username, Integer status);

  Optional<User> findByUsernameAndType(String username, String type);

  Optional<User> findByEmailAndType(String email, String type);

  Optional<User> findByThirdPartyIdAndType(String thirdPartyId, String type);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Page<User> findAllBy(Pageable pageable);

  Page<User> findByUsernameContains(String username, Pageable pageable);

  List<User> findAllByStatus(Integer status);
}
