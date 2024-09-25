package kr.plea.redismodule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.plea.redismodule.service.SlaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/stat")
@RequiredArgsConstructor
public class StatController {
	private final SlaService slaService;

	@PostMapping("/sla")
	public ResponseEntity<Long> getSLA(@RequestParam Long latency) {
		slaService.sendSla(latency);
		List<Long> valueList = slaService.getSla("latency");
		System.out.println("valueList = " + valueList);

		return new ResponseEntity(((Number)valueList.get(valueList.size()-1)).longValue(), HttpStatus.OK);
	}
}
