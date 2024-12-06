package com.leduo.backend.api.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class ProjectVo {
    private int projectId;

    private String projectName;

    private Date createdAt;

    private Date updatedAt;

    private String description;

    public ProjectVo(int projectId, String projectName, String description) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
    }
}
