package com.leduo.backend.data.repository.impl;

import com.leduo.backend.data.dao.ITaskDao;
import com.leduo.backend.data.dto.TaskDto;
import com.leduo.backend.data.entity.Task;
import com.leduo.backend.data.repository.ITaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Slf4j
@Repository
public class TaskRepositoryImpl implements ITaskRepository {
    @Autowired
    private ITaskDao taskDao;

    public TaskRepositoryImpl(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public long createTask(Task task) {
        return taskDao.createTask(task);
    }

    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    public List<TaskDto> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public TaskDto getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    public int deleteTask(int id) {
        return taskDao.deleteTask(id);
    }

    public List<TaskDto> getTasksByProjectId(int projectId) {
        return taskDao.getTasksByProjectId(projectId);
    }
}