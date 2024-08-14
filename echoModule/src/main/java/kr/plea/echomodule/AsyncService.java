package kr.plea.echomodule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class AsyncService {
	private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Async
	void asyncMethod(int i) {
		log.info("---- {}: asyncMethod ----", i);
	}
}
