package com.ducvt.subleasing.account.models.dto;

import com.ducvt.subleasing.account.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserPageDto {
    private List<User> content;
    private long totalElements;
}
