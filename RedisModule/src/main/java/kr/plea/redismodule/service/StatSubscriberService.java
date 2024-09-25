package kr.plea.redismodule.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.redismodule.param.StatLatencyParam;
import kr.plea.redismodule.param.StatSubscriberParam;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatSubscriberService {

	private final RedisService redisService;
	private ObjectMapper mapper = new ObjectMapper();

	// @PostConstruct
	// void init() {
	// 	mapper.registerModule(new JavaTimeModule());
	// }

	public void update(StatSubscriberParam statSubscriberParam) {
		String key = statSubscriberParam.getSubscriberId().toString();
		redisService.addValue(key, statSubscriberParam);
	}

	public void printValues(String key) {
		StatSubscriberParam statSubscriberParam = mapper.convertValue(redisService.getValue(key), StatSubscriberParam.class);

		System.out.println("statSubscriberParam.toString() = " + statSubscriberParam.toString());

	}
}
