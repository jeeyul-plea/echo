package kr.plea.echomodule.service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisService {
	private final RedisTemplate<String, Object> redisTemplate;

	public void setValue(String key, String data) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.set(key, data);
	}

	public void setValues(String key, String data, Duration duration) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.set(key, data, duration);


	}

	@Transactional(readOnly = true)
	public String getValues(String key) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		String value = (String) ops.get(key);
		return value == null ? "false" : value;

	}

	public void deleteValues(String key) {
		redisTemplate.delete(key);
	}

	public void expireValues(String key, int timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
	}

	public void setHashOps(String key, Map<String, String> data) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		ops.putAll(key, data);
	}

	@Transactional(readOnly = true)
	public String getHashOps(String key, String hashKey) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		return Boolean.TRUE.equals(ops.hasKey(key, hashKey)) ? (String) ops.get(key, hashKey) : "";
	}

	public boolean deleteHashOps(String key, String hashKey) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		return ops.delete(key, hashKey) > 0;
	}

	public boolean isValueExists(String value) {
		return !value.equals("false");
	}
}
