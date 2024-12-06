package com.leduo.backend.data.dto;

import com.leduo.backend.data.entity.Project;
import com.leduo.backend.data.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class TaskDto {
    private int taskId;

    private String taskName;

    private Date createdAt;

    private Date updatedAt;

    private Date dueDate;

    private String priority;

    private String status;

    private String description;

    private User assignedTo;

    private Project project;
}
