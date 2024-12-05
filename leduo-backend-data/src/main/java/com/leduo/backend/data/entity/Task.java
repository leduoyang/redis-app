package com.leduo.backend.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class Task {
    private long id;

    private String title;

    private String description;

    private String status;

    private Date createdAt;

    private Date updatedAt;
}