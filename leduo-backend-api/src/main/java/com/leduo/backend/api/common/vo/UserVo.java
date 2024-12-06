package com.leduo.backend.api.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class UserVo {
    private int userId;

    private String username;

    private Date createdAt;

    private Date updatedAt;

    private String role; // LEAD, MANAGER, DEVELOPER, QA

    public UserVo(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
