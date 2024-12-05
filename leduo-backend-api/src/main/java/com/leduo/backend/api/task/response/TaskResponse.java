package com.leduo.backend.api.task.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class TaskResponse {
    private long id;

    private String title;

    private String description;

    private String status;

    private Date created_at;

    private Date updated_at;

    private boolean isCache = false;
}
