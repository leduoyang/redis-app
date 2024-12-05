package com.leduo.backend.web.controller.task;

import com.leduo.backend.api.common.APIResponse;
import com.leduo.backend.api.task.ITaskApi;
import com.leduo.backend.api.task.request.TaskRequest;
import com.leduo.backend.api.task.response.TaskResponse;
import com.leduo.backend.core.service.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TaskController implements ITaskApi {
    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public APIResponse<TaskResponse> getTaskById(int taskId) {
        TaskResponse taskResponse = taskService.getTaskById(taskId);
        return APIResponse.getOKJsonResult(taskResponse);
    }

    @Override
    public APIResponse<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> taskListResponse = taskService.getAllTasks();
        return APIResponse.getOKJsonResult(taskListResponse);
    }

    @Override
    public APIResponse<Long> createTask(
            TaskRequest request
    ) {
        long id = taskService.createTask(request);
        return APIResponse.getOKJsonResult(id);
    }

    @Override
    public APIResponse<Void> updateTask(int taskId, TaskRequest request) {
        taskService.updateTask(taskId, request);
        return APIResponse.getOKJsonResult(null);
    }

    @Override
    public APIResponse<Void> deleteTask(int taskId) {
        taskService.deleteTask(taskId);
        return APIResponse.getOKJsonResult(null);
    }
}
