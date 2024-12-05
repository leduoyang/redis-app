package com.leduo.backend.api.task.request;

import com.leduo.backend.api.task.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TaskRequest {
    @NotNull
    @Size(min = 1, max = 40)
    private String title;

    @Size(min = 1, max = 200)
    private String description;

    private TaskStatus status;
}
