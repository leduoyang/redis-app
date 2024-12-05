package com.leduo.backend.data.repository.impl;

import com.leduo.backend.data.dao.ITaskDao;
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
    ITaskDao taskDao;

    public TaskRepositoryImpl(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public long createTask(Task task) {
        return taskDao.createTask(task);
    }

    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public Task getTaskById(long id) {
        return taskDao.getTaskById(id);
    }

    public int deleteTask(long id) {
        return taskDao.deleteTask(id);
    }
}
