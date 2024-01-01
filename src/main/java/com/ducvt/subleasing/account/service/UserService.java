package com.ducvt.subleasing.account.service;

import com.ducvt.subleasing.account.models.User;
import com.ducvt.subleasing.account.models.dto.UserPageDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
//    Page<User> getAll(int page, int offset);
    UserPageDto getAll(int page, int offset);

    UserPageDto searchByUsername(String username, int page, int offset);

    void update(User user);

    User getById(Long id);

    User getByUsername(String username);
}
