package com.leduo.backend.core.service.task.impl;

import com.leduo.backend.api.common.vo.ProjectVo;
import com.leduo.backend.api.common.vo.UserVo;
import com.leduo.backend.core.service.task.ITaskService;
import com.leduo.backend.api.task.request.TaskRequest;
import com.leduo.backend.api.task.response.TaskResponse;
import com.leduo.backend.data.dto.TaskDto;
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
    public TaskResponse getTaskById(int id, boolean byCache) {
        String cacheKey = "task:" + id;
        TaskDto taskDto = null;
        if(byCache) {
            taskDto = (TaskDto) cacheUtil.getValue(cacheKey);
        }
        boolean isCache = taskDto != null;
        if (taskDto == null) {
            taskDto = taskRepository.getTaskById(id);
            cacheUtil.setValue(cacheKey, taskDto);
        }

        return new TaskResponse()
                .setId(taskDto.getTaskId())
                .setTitle(taskDto.getTaskName())
                .setDescription(taskDto.getDescription())
                .setStatus(taskDto.getStatus())
                .setCreated_at(taskDto.getCreatedAt())
                .setUpdated_at(taskDto.getUpdatedAt())
                .setAssignedTo(
                        new UserVo(
                                taskDto.getAssignedTo().getUserId(),
                                taskDto.getAssignedTo().getUsername(),
                                taskDto.getAssignedTo().getRole()))
                .setProject(
                        new ProjectVo(
                                taskDto.getProject().getProjectId(),
                                taskDto.getProject().getProjectName(),
                                taskDto.getProject().getDescription()))
                .setCache(isCache);
    }

    @Override
    public List<TaskResponse> getAllTasks(boolean byCache) {
        String cacheKey = "task:all";
        List<TaskDto> taskDtoList = null;
        if (byCache) {
            taskDtoList = (List<TaskDto>) cacheUtil.getValue(cacheKey);
        }
        final boolean isCache = (taskDtoList != null);
        if (taskDtoList == null) {
            taskDtoList = taskRepository.getAllTasks();
            cacheUtil.setValue(cacheKey, taskDtoList);
        }
        return taskDtoList.stream()
                .map(taskDto -> new TaskResponse()
                        .setId(taskDto.getTaskId())
                        .setTitle(taskDto.getTaskName())
                        .setDescription(taskDto.getDescription())
                        .setStatus(taskDto.getStatus())
                        .setAssignedTo(
                                new UserVo(
                                        taskDto.getAssignedTo().getUserId(),
                                        taskDto.getAssignedTo().getUsername(),
                                        taskDto.getAssignedTo().getRole()))
                        .setProject(
                                new ProjectVo(
                                        taskDto.getProject().getProjectId(),
                                        taskDto.getProject().getProjectName(),
                                        taskDto.getProject().getDescription()))
                        .setCreated_at(taskDto.getCreatedAt())
                        .setUpdated_at(taskDto.getUpdatedAt())
                        .setCache(isCache))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getTasksByProjectId(int projectId, boolean byCache) {
        String cacheKey = "task:project:" + projectId;
        List<TaskDto> taskList = null;
        if (byCache) {
            taskList = (List<TaskDto>) cacheUtil.getValue(cacheKey);
        }
        final boolean isCache = (taskList != null);
        if (taskList == null) {
            taskList = taskRepository.getTasksByProjectId(projectId);
            cacheUtil.setValue(cacheKey, taskList);
        }
        return taskList.stream()
                .map(taskDto -> new TaskResponse()
                        .setId(taskDto.getTaskId())
                        .setTitle(taskDto.getTaskName())
                        .setDescription(taskDto.getDescription())
                        .setStatus(taskDto.getStatus())
                        .setAssignedTo(
                                new UserVo(
                                        taskDto.getAssignedTo().getUserId(),
                                        taskDto.getAssignedTo().getUsername(),
                                        taskDto.getAssignedTo().getRole()))
                        .setProject(
                                new ProjectVo(
                                        taskDto.getProject().getProjectId(),
                                        taskDto.getProject().getProjectName(),
                                        taskDto.getProject().getDescription()))
                        .setCreated_at(taskDto.getCreatedAt())
                        .setUpdated_at(taskDto.getUpdatedAt())
                        .setCache(isCache))
                .collect(Collectors.toList());
    }

    @Override
    public long createTask(TaskRequest request) {
        Task task = new Task()
                .setTaskName(request.getTaskName())
                .setDescription(request.getDescription())
                .setStatus(request.getStatus().name());
        return taskRepository.createTask(task);
    }

    @Override
    public void updateTask(int id, TaskRequest request) {
        Task task = new Task()
                .setTaskId(id)
                .setTaskName(request.getTaskName())
                .setDescription(request.getDescription())
                .setStatus(request.getStatus().name());
        taskRepository.updateTask(task);
    }

    @Override
    public int deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
}
