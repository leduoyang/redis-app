package com.leduo.backend.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class Project {
    private int projectId;

    private String projectName;

    private Date createdAt;

    private Date updatedAt;

    private String description;
}