package kr.plea.echomodule.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.echomodule.param.StatLatencyParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatSummaryService {
	private LocalDate resetDt = LocalDate.now();
	private final RedisService redisService;
	final String  key = resetDt.toString();
	final String key2 = key + "V2";
	private ObjectMapper mapper = new ObjectMapper();

	public void updateLatency(StatLatencyParam statLatencyParam) {
		redisService.addList(key, statLatencyParam);
	}

	public void updateLatencyV2(StatLatencyParam statLatencyParam) {
		redisService.addList(key2, statLatencyParam);
	}

	public void printList() {
		System.out.println("key = " + key);
		printList(key);
	}

	public void printListV2() {
		System.out.println("key2 = " + key2);
		printList(key2);
	}

	private void printList(String key) {
		List<Object> objList = Collections.singletonList(redisService.getParamList(key2));
		List<StatLatencyParam> paramList = getStatLatencyParamList(objList);
		System.out.println(paramList.size());
		int listCount = 1;
		for (StatLatencyParam statLatencyParam : paramList) {
			List<Long> latencyList = statLatencyParam.getLatencyList();
			System.out.println("listCount = " + listCount++);
			for (Long latency : latencyList) {
				System.out.println("latency = " + latency);
			}
		}
	}

	private List<StatLatencyParam> getStatLatencyParamList(List<Object> objList) {
		List<StatLatencyParam> paramList = objList.stream()
			.flatMap(obj -> Arrays.stream(mapper.convertValue(obj, StatLatencyParam[].class)))
			.collect(Collectors.toList());
		return paramList;
	}

}
