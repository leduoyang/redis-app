package com.leduo.backend.data.dao;

import com.leduo.backend.data.entity.Task;

import java.util.List;

public interface ITaskDao {
    int createTask(Task task);

    int updateTask(Task task);

    Task getTaskById(long id);

    List<Task> getAllTasks();

    int deleteTask(long id);
}
