package com.leduo.backend.data.repository;

import com.leduo.backend.data.entity.Task;

import java.util.List;

public interface ITaskRepository {
    long createTask(Task task);

    void updateTask(Task task);

    Task getTaskById(long id);

    List<Task> getAllTasks();

    int deleteTask(long id);
}
