package kr.plea.redismodule.param;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class StatLatencyParam {
	private List<Long> latencyList;

	@Builder
	public StatLatencyParam(List<Long> latencyList) {
		this.latencyList = latencyList;
	}
}