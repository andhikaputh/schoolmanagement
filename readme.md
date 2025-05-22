# 📚 School Management

A Spring Boot application for managing school data.

## Prerequisites

- [Makefile](#make-installation)
- [Docker](https://www.docker.com/products/docker-desktop)
- `.env.local` and/or `.env.staging` file in the project root (based on `.env.example`)

## Start the Application

### Make Installation

Since we will be using Makefile to run the application, you need to install it first:
- On Windows, you can install it using [Chocolatey](https://chocolatey.org/install).
After installing Chocolatey, you can run `choco install make` to install Makefile.
- On Linux, you can install it using `sudo apt-get install make`.

And then you can run the application in bash terminal using these commands below.

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