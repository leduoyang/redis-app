# Spring Boot with Redis, MySQL, and MyBatis

This repository demonstrates how to integrate Redis into a TODO application for caching purposes, improving the app's performance by reducing database query overhead.

## Repository
The TODO app's primary purpose is to manage tasks, and it is implemented with MySQL at [here](https://github.com/leduoyang/mysql-mybatis-app).

## Features of Redis Integration
- **Caching for Task Retrieval:**
  - Reduces latency for frequently accessed endpoints, such as retrieving all tasks or a specific task by ID.
  - Minimizes the load on the database by storing task data temporarily in Redis.

- **Automatic Expiry:**
  - Ensures the cache is fresh by setting a TTL (time-to-live) for cached data.

## Redis Setup
1. **Docker-Based Redis Setup**:
   Run Redis using Docker:
   ```bash
   docker run --name redis-container -p 6379:6379 -d redis
   // or using docker-compose provided under the folder /docker-compose to build containers of MySQL and Redis
   // docker-compose up --build
   ```
2. **Verify Redis Installation:**
   Ensure Redis is running by connecting to its CLI:
   ```bash
   docker exec -it redis-container redis-cli
   ```
3. **Access Redis CLI**
   ```
   redis-cli -a {password}
   127.0.0.1:6379> GET task:1
   "{\"@class\":\"com.leduo.backend.data.entity.Task\",\"id\":1,\"title\":\"Learn Spring Boot\",\"description\":\"Work on a simple to-do app project\",\"status\":\"PENDING\",\"createdAt\":[\"java.util.Date\",1733369474000],\"updatedAt\":[\"java.util.Date\",1733369474000]}"
   127.0.0.1:6379> KEYS *
   1) "task:all"
   2) "task:1"
   ``` 

## Key Integration Points

1. **Cache Utility:**
   - Provides methods to interact with Redis, including `setValue` and `getValue`.

2. **Caching in Business Logic:**
   - For endpoints like `getAllTasks` and `getTaskById`, the application first checks Redis for cached data before querying the database.

3. **Cache Key Structure:**
   - Keys are designed to uniquely identify resources, such as:
     - `task:all` for all tasks.
     - `task:<taskId>` for individual tasks.

4. **TTL Configuration:**
   - Cached data is set with a configurable expiry time to ensure consistency with the database.

## Updates to Task Management

### Extended Data Model
- Tasks are now associated with additional entities:
  - **Project:** Contains information like project name and description.
  - **User:** Represents the user assigned to the task, including username and role.

### New Features
1. **Get Tasks by Project ID:**
   - Endpoint: `GET /project/{projectId}/task`
   - Returns all tasks associated with a specific project.

2. **Enhanced Task Details:**
   - Task responses now include:
     - Assigned user information (e.g., `username`, `role`).
     - Project details (e.g., `projectName`, `projectDescription`).

### Sample Updated Code for TaskService

```java
@Service
public class TaskService {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponse> getTasksByProjectId(int projectId) {
        String cacheKey = "task:project:" + projectId;
        List<Task> taskList = (List<Task>) cacheUtil.getValue(cacheKey);
        if (taskList == null) {
            taskList = taskRepository.getTasksByProjectId(projectId);
            cacheUtil.setValue(cacheKey, taskList);
        }
        final boolean isCache = (taskList != null);
        return taskList
                .stream()
                .map(task -> new TaskResponse()
                        .setId(task.getTaskId())
                        .setTitle(task.getTaskName())
                        .setDescription(task.getDescription())
                        .setStatus(task.getStatus())
                        .setCreated_at(task.getCreatedAt())
                        .setUpdated_at(task.getUpdatedAt())
                        .setAssignedTo(task.getAssignedTo())
                        .setProject(task.getProject())
                        .setCache(isCache))
                .collect(Collectors.toList());
    }
}
```

## Configuration

- Update Redis configuration (e.g., host, port, password) in `application.properties`:
  ```properties
  spring.redis.host=localhost
  spring.redis.port=6379
  spring.redis.password={password}
  ```

- Ensure MyBatis mappings are updated to reflect the new entity relationships, as shown in the `taskResultMap` configuration.

---
