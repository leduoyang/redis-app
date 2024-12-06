package com.leduo.backend.api.task.response;

import com.leduo.backend.api.common.vo.ProjectVo;
import com.leduo.backend.api.common.vo.UserVo;
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

    private ProjectVo project;

    private UserVo assignedTo;

    private boolean isCache = false;
}
