package com.leduo.backend.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class Task {
    private int taskId;

    private String taskName;

    private Date createdAt;

    private Date updatedAt;

    private Date dueDate;

    private String priority;

    private String status;

    private int assignedTo;

    private String description;

    private int projectId;
}