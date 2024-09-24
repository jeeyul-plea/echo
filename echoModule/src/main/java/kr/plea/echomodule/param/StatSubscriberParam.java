package kr.plea.echomodule.param;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StatSubscriberParam {
	private BigInteger subscriberId;
	private String accessDt;
	private Integer newsClickCount;
	private Integer pageViewCount;
	private Integer mmsClickCount;
	private Integer shareViewCount;

	private String sendTodayStatus; //당일 발송된 뉴스 여부
}
