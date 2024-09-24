package kr.plea.echomodule.service;

import java.time.Duration;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.plea.echomodule.param.StatLatencyParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlaService {

	private final RedisService redisService;

	@Async
	public void sendSla(Long latency) {
		redisService.addValue("latency" , latency, Duration.ofMillis(5000));
	}

	public List<Long> getSla(String key){
		return redisService.getValues(key);
	}

	@Scheduled(fixedRate = 5000)
	public void sendSla() {
		List<Long> latencyList = getSla("latency");

		if(latencyList.isEmpty()){
			log.info("Now latencyList is empty. So skip sending SLA.");
			return;
		}
		StatLatencyParam statLatencyParam = new StatLatencyParam(latencyList);
		// redisService.addValue();
	}

}