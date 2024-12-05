USE base;

INSERT INTO task (title, description, status, created_at, updated_at) VALUES
('Learn Spring Boot', 'Work on a simple to-do app project', 'PENDING', NOW(), NOW()),
('Set up MySQL Database', 'Install and configure MySQL for the project', 'DONE', NOW(), NOW()),
('Explore MyBatis', 'Learn how to integrate MyBatis with Spring Boot', 'IN_PROGRESS', NOW(), NOW()),
('Implement Redis Cache', 'Use Redis to cache frequently accessed data', 'PENDING', NOW(), NOW()),
('Create REST APIs', 'Develop API endpoints for CRUD operations', 'DONE', NOW(), NOW()),
('Write Unit Tests', 'Ensure code reliability with JUnit tests', 'PENDING', NOW(), NOW()),
('Design Database Schema', 'Create tables for storing task details', 'DONE', NOW(), NOW()),
('Optimize SQL Queries', 'Refactor queries for better performance', 'IN_PROGRESS', NOW(), NOW()),
('Learn Elasticsearch', 'Index and search data with Elasticsearch', 'PENDING', NOW(), NOW()),
('Implement Kafka', 'Set up Kafka for event-driven architecture', 'IN_PROGRESS', NOW(), NOW()),
('Deploy to AWS', 'Deploy the to-do app on AWS EC2', 'PENDING', NOW(), NOW()),
('Implement Security', 'Add authentication and authorization layers', 'PENDING', NOW(), NOW()),
('Refactor Code', 'Clean up codebase for better maintainability', 'DONE', NOW(), NOW()),
('Add Pagination', 'Implement pagination in the API responses', 'IN_PROGRESS', NOW(), NOW()),
('Enhance UI', 'Improve user interface and experience', 'PENDING', NOW(), NOW()),
('Monitor Performance', 'Set up Grafana for performance monitoring', 'PENDING', NOW(), NOW()),
('Learn MongoDB', 'Explore MongoDB for future projects', 'DONE', NOW(), NOW()),
('Create Docker Image', 'Containerize the to-do app using Docker', 'IN_PROGRESS', NOW(), NOW()),
('Automate Testing', 'Integrate tests into CI/CD pipelines', 'PENDING', NOW(), NOW()),
('Update Documentation', 'Write detailed project documentation', 'DONE', NOW(), NOW());
