package kr.plea.redismodule.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisTemplateService {
	private final RedisTemplate<String, Object> redisTemplate;

	public void addValue(String key, Object data) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.set(key, data);
	}

	public void addValue(String key, Long data, Duration duration) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		ops.set(key, data, duration);
	}

	public void addAll(String key, List<Long> data) {
		ListOperations<String, Object> ops = redisTemplate.opsForList();
		ops.leftPushAll(key, data);
	}

	public void addList(String key, Object data) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();

		// Redis에서 값을 가져오고, 없으면 새 리스트 생성
		List<Object> list;
		Object cachedValue = ops.get(key);

		if (cachedValue == null) {
			list = new ArrayList<>();  // 새 리스트 생성
		} else {
			// 기존 값이 존재할 경우, 올바르게 캐스팅될 수 있도록 안전하게 변환
			try {
				list = (List<Object>) cachedValue;
			} catch (ClassCastException e) {
				// 잘못된 캐스팅이 발생하면 예외 처리 및 로깅
				log.error("잘못된 캐스팅이 발생하였습니다.", e);
				return;
			}
		}

		list.add(data);
		ops.set(key, list);  // Redis에 업데이트된 리스트 저장
	}

	public Object getValue(String key) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		return ops.get(key);
	}


	public List<Long> getValues(String key) {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		if(ops.get(key) != null){
			List<Long> valueList = (List<Long>)ops.get(key);
			return valueList;
		}
		else return new ArrayList<>();
	}

	public List<Object> getParamList(String key) {
		ListOperations<String, Object> ops = redisTemplate.opsForList();
		return redisTemplate.opsForList().range(key, 0, -1);
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
