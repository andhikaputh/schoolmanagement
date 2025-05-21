# Using Redis

This guide explains how Redis is used for caching in our project.

Redis caching will be operated by the `RedisCacheUtil` class.

All values are stored as JSON strings.

## Installation

To use Redis, you need to install Redis on your system.

Since we are using Docker for this project, the Redis database and RedisInsight is already installed.
RedisInsight is used to view the cached values stored on Redis database. You can access RedisInsight at `localhost:5540`.

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
