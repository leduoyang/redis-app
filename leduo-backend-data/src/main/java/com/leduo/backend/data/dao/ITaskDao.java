package com.leduo.backend.data.dao;

import com.leduo.backend.data.dto.TaskDto;
import com.leduo.backend.data.entity.Task;

import java.util.List;

public interface ITaskDao {
    int createTask(Task task);

    int updateTask(Task task);

    TaskDto getTaskById(int id);

    List<TaskDto> getAllTasks();

    int deleteTask(int id);

    List<TaskDto> getTasksByProjectId(int projectId);
}
