package kr.plea.redismodule.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.redismodule.param.StatSubscriberParam;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatSubscriberService {

	private final RedisTemplateService redisTemplateService;
	private ObjectMapper mapper = new ObjectMapper();

	// @PostConstruct
	// void init() {
	// 	mapper.registerModule(new JavaTimeModule());
	// }

	public void update(StatSubscriberParam statSubscriberParam) {
		String key = statSubscriberParam.getSubscriberId().toString();
		redisTemplateService.addValue(key, statSubscriberParam);
	}

	public void printValues(String key) {
		StatSubscriberParam statSubscriberParam = mapper.convertValue(redisTemplateService.getValue(key), StatSubscriberParam.class);

		System.out.println("statSubscriberParam.toString() = " + statSubscriberParam.toString());

	}
}
