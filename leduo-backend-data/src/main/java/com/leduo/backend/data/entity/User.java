package com.leduo.backend.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class User {
    private int userId;
    private String username;
    private Date createdAt;
    private Date updatedAt;
    private String role; // LEAD, MANAGER, DEVELOPER, QA
}