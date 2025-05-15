# Using Redis

This guide explains how Redis is used for caching in our project.

Redis caching will be operated by the `RedisCacheUtil` class.

All values are stored as JSON strings.

## Installation

To use Redis, you need to install Redis on your system. I recommend using Docker to install Redis.

Here is the command to install & run Redis using Docker:

```
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```

As you can see, there are two ports exposed:

- `6379` for Redis itself
- `8001` for RedisInsight

RedisInsight is used to view the cached values stored on Redis. You can access RedisInsight at `REDIS_HOST:8001`.

## List of Methods

### `cacheValue(String key, T value)`

Caches a value with the given key.

Example:

```
RedisCacheUtil.cacheValue("key", "value");
```

&nbsp;

### `getCachedValue(String key, Class<T> className)`

Retrieves a cached value with the given key and converts it back to the specified class.

Example:

```
User user = RedisCacheUtil.getCachedValue("key", User.class);
```

&nbsp;

### `getCachedList(String key, TypeReference<T> typeRef)`

Retrieves a cached list with the given key using Jackson's `TypeReference`.

Example:

```
List<User> users = RedisCacheUtil.getCachedList("key", new TypeReference<List<User>>() {});
```

&nbsp;

### `deleteCache(String key)`

Deletes a cached value with the given key.

Example:

```
RedisCacheUtil.deleteCache("key");
```
