package com.kr.plea.echomodule;

import static org.assertj.core.api.Assertions.*;
import static org.awaitility.Awaitility.await;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.echomodule.EchoModuleApplication;
import kr.plea.echomodule.dto.NewsContentFindDto;
import kr.plea.echomodule.param.StatLatencyParam;
import kr.plea.echomodule.param.StatSubscriberParam;
import kr.plea.echomodule.service.NewsService;
import kr.plea.echomodule.service.RedisService;
import kr.plea.echomodule.service.StatSubscriberService;
import kr.plea.echomodule.service.StatSummaryService;

@SpringBootTest(classes = EchoModuleApplication.class)
public class RedisCrudTest {
	final String KEY = "key";
	final Long VALUE = 11223L;
	final List<Long> LIST = List.of(VALUE, 11111L, 11112L, 11114L);
	final List<Long> V2LIST = List.of(22222L, 33333L, 44444L, 55555L);
	final Duration DURATION = Duration.ofMillis(5000);
	StatLatencyParam PARAM1;
	StatLatencyParam PARAM2;
	private RedisService redisService;
	private StatSummaryService statSummaryService;
	private StatSubscriberService statSubscriberService;
	private NewsService newsService;
	private NewsContentFindDto NEWS_CONTENT;
	private ObjectMapper objectMapper = new ObjectMapper();

	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String formattedDate = now.format(formatter);

	StatSubscriberParam STATSUBSCRIBER_PARAM = new StatSubscriberParam(BigInteger.valueOf(1), formattedDate, 2,3,1,1,"N");
	List<StatSubscriberParam> STATSUBSCRIBER_PARRAM_LIST = new ArrayList<StatSubscriberParam>();

	@Autowired
	public RedisCrudTest(RedisService redisService, StatSummaryService statSummaryService,
		StatSubscriberService statSubscriberService, NewsService newsService) {
		this.redisService = redisService;
		this.statSummaryService = statSummaryService;
		this.statSubscriberService = statSubscriberService;
		this.newsService = newsService;
	}

	@BeforeEach
	void init() {
		redisService.addValue(KEY, VALUE, DURATION);
		PARAM1 = new StatLatencyParam(LIST);
		PARAM2 = new StatLatencyParam(V2LIST);
		STATSUBSCRIBER_PARRAM_LIST.add(STATSUBSCRIBER_PARAM);
		NEWS_CONTENT = new NewsContentFindDto("AKR20070807170200054", "C", "AKR0", "20070807", "171444", "YNA", "신창섭", "07", "053",
			"대구경북취재본부", "대구 첫 전용미술관 9일 착공", "대구 첫 전용미술관 착공", "이 기사는 임의로 Element와 내용을 모두 채운 샘플입니다.", "대구 첫 전용미술관 9일 착공  ~~~ 말했따.", true,false);
	}

	@AfterEach
	void makeEmpty() {
		redisService.deleteValues(KEY);
		redisService.deleteValues(LocalDate.now().toString());
		redisService.deleteValues(LocalDate.now() + "V2");
		redisService.deleteValues(BigInteger.valueOf(1).toString());
		redisService.deleteValues("AKR20070807170200054");
	}

	@Test
	@DisplayName("Redis에 데이터를 저장하면 정상적으로 조회된다.")
	void saveAndFindTest() {        //when
		Long value =objectMapper.convertValue(redisService.getValue(KEY), Long.class);

		//then
		assertThat(value).isEqualTo(VALUE);

	}

	@Test
	@DisplayName("Redis에 저장된 데이터를 수정할 수 있다.")
	void updateTest() {
		//given
		Long updateValue = 2222L;
		redisService.addValue(KEY, updateValue);

		//when
		Long findValue = objectMapper.convertValue (redisService.getValue(KEY), Long.class);

		//then
		assertThat(updateValue).isEqualTo(findValue);
	}

	@Test
	@DisplayName("Redis에 저장된 데이터를 삭제할 수 있다.")
	void deleteTest() {
		//when
		redisService.deleteValues(KEY);
		List<Long> findValue = redisService.getValues(KEY);

		//then
		assertThat(findValue.size()).isEqualTo(0);

	}

	@Test
	@DisplayName("Redis에 저장된 데이터는 만료시간이 지나면 삭제된다.")
	void expiredTest() {
		await().pollDelay(Duration.ofMillis(6000)).untilAsserted(
			() -> {
				Object expiredValue = redisService.getValue(KEY);
				assertThat(expiredValue).isNotEqualTo(1);
				assertThat(expiredValue).isNull();
			}
		);
	}

	@Test
	@DisplayName("PramList 출력해보기")
	void printTest() {
		List<Long> latencyList = PARAM1.getLatencyList();
		for (Long latency1 : latencyList) {
			System.out.println("latency1 = " + latency1);
		}

		List<Long> latencyList2 = PARAM2.getLatencyList();
		for (Long latency2 : latencyList2) {
			System.out.println("latency2 = " + latency2);
		}
		System.out.println();
	}

	@Test
	@DisplayName("StatLatencyParam으로 데이터를 전달받아 redis에 저장하고 조회 할 수 있다.")
	void statLatencyTest() {
		statSummaryService.updateLatency(PARAM1);
		statSummaryService.updateLatencyV2(PARAM2);

		System.out.println("저장된 PARAM1 출력하기");
		statSummaryService.printList();
		System.out.println();
		System.out.println("저장된 PARAM2 출력하기");
		statSummaryService.printListV2();
	}

	@Test
	@DisplayName("StatSubscriberParam으로 데이터를 전달받아 redis에 저장하고 조회 할 수 있다.")
	void statSubscriberTest() {
		statSubscriberService.update(STATSUBSCRIBER_PARAM);

		System.out.println("저장된 STATSUBSCRIBER_PARAM 출력하기");
		statSubscriberService.printValues(STATSUBSCRIBER_PARAM.getSubscriberId().toString());
	}

	@Test
	@DisplayName("NewsData를 저장하고 가지고 올 수 있다.")
	void newsDataTest() {
		newsService.save(NEWS_CONTENT);

		System.out.println("저장된 뉴스 출력하기");
		newsService.print(NEWS_CONTENT.getContentId());
	}

}
