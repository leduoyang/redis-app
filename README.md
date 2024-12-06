# Spring Boot with Redis, MySQL, and MyBatis

This repository demonstrates how to integrate Redis into a TODO application for caching purposes, improving the app's performance by reducing database query overhead.

## Repository
The TODO app's primary purpose is to manage tasks, and it is implmeneted with MySQL at [here](https://github.com/leduoyang/mysql-mybatis-app).

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
3. **Access Redis cli**
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

## Sample Code for Redis Caching

```java
@Service
public class TaskService {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private TaskRepository taskRepository;

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
}
```

## Configuration

- Update Redis configuration (e.g., host, port, password) in `application.properties`:
  ```properties
  spring.redis.host=localhost
  spring.redis.port=6379
  spring.redis.password={password}
  ```

