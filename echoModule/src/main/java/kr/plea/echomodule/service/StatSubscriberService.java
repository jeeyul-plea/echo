package kr.plea.echomodule.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.echomodule.param.StatSubscriberParam;
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
		redisService.addList(key, statSubscriberParam);
	}

	public void printValues(String key) {
		List<Object> objList = Collections.singletonList(
			redisService.getParamList(key));
		System.out.println("objList = " + objList);
		if(objList.size() != 0) {
			List<StatSubscriberParam> paramList = objList.stream()
				.flatMap(obj -> Arrays.stream(mapper.convertValue(obj,StatSubscriberParam[].class)))
				.collect(Collectors.toList());

			for (StatSubscriberParam statSubscriberParam : paramList) {
				System.out.println("statSubscriberParam.toString() = " + statSubscriberParam.toString());
			}
		}
	}
}
