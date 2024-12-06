package com.leduo.backend.data.repository;

import com.leduo.backend.data.dto.TaskDto;
import com.leduo.backend.data.entity.Task;

import java.util.List;

public interface ITaskRepository {
    long createTask(Task task);

    void updateTask(Task task);

    TaskDto getTaskById(int id);

    List<TaskDto> getAllTasks();

    int deleteTask(int id);

    List<TaskDto> getTasksByProjectId(int projectId);
}
