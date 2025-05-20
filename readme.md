# 📚 School Management

A Spring Boot application for managing school data.

## Prerequisites

- [Docker](https://www.docker.com/products/docker-desktop)
- `.env` file in the project root (based on `.env.example`)

## Start the application (with Docker)

```
docker-compose up --build
```

This will start:
- Spring Boot app
- PostgreSQL
- Redis
- RedisInsight

For more information on Redis, see [this file](src/main/java/com/telu/schoolmanagement/common/redis/redis.md)

## Stop application

```
docker-compose down
```
If you want to stop application and remove volumes on Docker use:

(this will also remove all changes in PostgreSQL and Redis)
```
docker-compose down -v
```