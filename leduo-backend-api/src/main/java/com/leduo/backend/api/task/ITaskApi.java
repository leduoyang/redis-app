package com.leduo.backend.api.task;

import com.leduo.backend.api.task.request.TaskRequest;
import com.leduo.backend.api.task.response.TaskResponse;
import com.leduo.backend.api.common.APIResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/api/v1")
public interface ITaskApi {
    @GetMapping(value = "/task/{taskId}")
    APIResponse<TaskResponse> getTaskById(
            @NotNull @PathVariable int taskId,
            @RequestParam(required = false, defaultValue = "false") boolean byCache
    );

    @GetMapping(value = "/project/{projectId}/tasks")
    APIResponse<List<TaskResponse>> getTaskByProjectId(
            @NotNull @PathVariable int projectId,
            @RequestParam(required = false, defaultValue = "false") boolean byCache
    );

    @GetMapping(value = "/tasks")
    APIResponse<List<TaskResponse>> getAllTasks(
            @RequestParam(required = false, defaultValue = "false") boolean byCache
    );

    @PostMapping(value = "/task")
    APIResponse<Long> createTask(
            @Validated @RequestBody TaskRequest request
    );

    @PatchMapping(value = "/task/{taskId}")
    APIResponse<Void> updateTask(
            @NotNull @PathVariable int taskId,
            @Validated @RequestBody TaskRequest request
    );

    @DeleteMapping(value = "/task/{taskId}")
    APIResponse<Void> deleteTask(
            @NotNull @PathVariable int taskId
    );
}
