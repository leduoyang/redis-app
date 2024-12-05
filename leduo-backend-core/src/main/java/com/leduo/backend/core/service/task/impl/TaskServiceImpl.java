package com.leduo.backend.core.service.task.impl;

import com.leduo.backend.core.service.task.ITaskService;
import com.leduo.backend.api.task.request.TaskRequest;
import com.leduo.backend.api.task.response.TaskResponse;
import com.leduo.backend.data.entity.Task;
import com.leduo.backend.data.repository.ITaskRepository;
import com.leduo.backend.middleware.CacheUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TaskServiceImpl implements ITaskService {
    private ITaskRepository taskRepository;

    private CacheUtil cacheUtil;

    public TaskServiceImpl(ITaskRepository taskRepository, CacheUtil cacheUtil) {
        this.taskRepository = taskRepository;
        this.cacheUtil = cacheUtil;
    }

    @Override
    public TaskResponse getTaskById(long id) {
        String cacheKey = "task:" + id;
        boolean isCache = true;
        Task task = (Task) cacheUtil.getValue(cacheKey);
        if (task == null) {
            task = taskRepository.getTaskById(id);
            cacheUtil.setValue(cacheKey, task);
            isCache = false;
        }

        TaskResponse taskResponse = new TaskResponse()
                .setId(task.getId())
                .setTitle(task.getTitle())
                .setDescription(task.getDescription())
                .setStatus(task.getStatus())
                .setCreated_at(task.getCreatedAt())
                .setUpdated_at(task.getUpdatedAt())
                .setCache(isCache);

        return taskResponse;
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        String cacheKey = "task:all";
        List<Task> taskList = (List<Task>) cacheUtil.getValue(cacheKey);
        if (taskList == null) {
            taskList = taskRepository.getAllTasks();
            cacheUtil.setValue(cacheKey, taskList);
        }
        final boolean isCache = (taskList != null);
        return taskList
                .stream()
                .map(x -> new TaskResponse()
                        .setId(x.getId())
                        .setTitle(x.getTitle())
                        .setDescription(x.getDescription())
                        .setStatus(x.getStatus())
                        .setCreated_at(x.getCreatedAt())
                        .setUpdated_at(x.getUpdatedAt())
                        .setCache(isCache))
                .collect(Collectors.toList());
    }

    @Override
    public long createTask(TaskRequest request) {
        Task task = new Task()
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .setStatus(request.getStatus().name());
        return taskRepository.createTask(task);
    }

    @Override
    public void updateTask(long id, TaskRequest request) {
        Task task = new Task()
                .setId(id)
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .setStatus(request.getStatus().name());
        taskRepository.updateTask(task);
    }

    @Override
    public int deleteTask(long id) {
        return taskRepository.deleteTask(id);
    }

}
