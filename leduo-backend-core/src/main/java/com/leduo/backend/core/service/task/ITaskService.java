package com.leduo.backend.core.service.task;

import com.leduo.backend.api.task.request.TaskRequest;
import com.leduo.backend.api.task.response.TaskResponse;

import java.util.List;

public interface ITaskService {
    long createTask(TaskRequest request);

    void updateTask(long id, TaskRequest request);

    TaskResponse getTaskById(long id);

    List<TaskResponse> getAllTasks();

    int deleteTask(long id);
}
