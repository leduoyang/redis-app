package com.leduo.backend.core.service.task;

import com.leduo.backend.api.task.request.TaskRequest;
import com.leduo.backend.api.task.response.TaskResponse;

import java.util.List;

public interface ITaskService {
    long createTask(TaskRequest request);

    void updateTask(int id, TaskRequest request);

    TaskResponse getTaskById(int id, boolean byCache);

    List<TaskResponse> getAllTasks(boolean byCache);

    List<TaskResponse> getTasksByProjectId(int projectId, boolean byCache);

    int deleteTask(int id);
}
