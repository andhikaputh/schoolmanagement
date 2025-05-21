# 📚 School Management

A Spring Boot application for managing school data.

## Prerequisites

- [Makefile](https://www.gnu.org/software/make/)
- [Docker](https://www.docker.com/products/docker-desktop)
- `.env.local` and/or `.env.staging` file in the project root (based on `.env.example`)

## Start the Application

### Local Environment

```
make run-local
```

This will start:

- Spring Boot app
- PostgreSQL
- Redis
- RedisInsight

### Staging Environment

```
make run-staging
```

This will start:

- Spring Boot app
- Redis
- RedisInsight

As for the database in the staging environment, it is managed using Supabase.

For more information on Redis usage on this project, see [this file](src/main/java/com/telu/schoolmanagement/common/redis/redis.md).

## Delete Application Volumes in Docker

### Local Environment

(This will also remove all changes in PostgreSQL and Redis)

```
make reset-local
```

### Staging Environment

```
make reset-staging
```
