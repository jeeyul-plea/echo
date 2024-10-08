package kr.plea.redismodule.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.redismodule.param.StatLatencyParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatSummaryService {
	private LocalDate resetDt = LocalDate.now();
	private final RedisTemplateService redisTemplateService;
	final String  key = resetDt.toString();
	final String key2 = key + "V2";
	private ObjectMapper mapper = new ObjectMapper();

	public void updateLatency(StatLatencyParam statLatencyParam) {
		redisTemplateService.addAll(key, statLatencyParam.getLatencyList());
	}

	public void updateLatencyV2(StatLatencyParam statLatencyParam) {
		System.out.println("statLatencyParam.getLatencyList() = " + statLatencyParam.getLatencyList());
		redisTemplateService.addAll(key2, statLatencyParam.getLatencyList());
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
        List<Object> obj = redisTemplateService.getParamList(key);
		List<Long> paramList = obj.stream()
			.flatMap(o -> ((List<?>) o).stream())
			.map(o -> Long.parseLong(o.toString()))
			.collect(Collectors.toList());

		System.out.println("paramList = " + paramList);

        for (Long latency : paramList) {
            System.out.println("latency = " + latency);
        }
	}

	// private List<StatLatencyParam> getStatLatencyParamList(List<Object> objList) {
	// 	List<StatLatencyParam> paramList = objList.stream()
	// 		.flatMap(obj -> Arrays.stream(mapper.convertValue(obj, StatLatencyParam[].class)))
	// 		.collect(Collectors.toList());
	// 	return paramList;
	// }

}
