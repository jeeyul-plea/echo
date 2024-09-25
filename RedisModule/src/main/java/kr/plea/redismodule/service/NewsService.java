package kr.plea.redismodule.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.plea.redismodule.dto.NewsContentFindDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {
	private final RedisService redisService;
	private ObjectMapper objectMapper = new ObjectMapper();

	public void save(NewsContentFindDto newsDto) {
		String key = newsDto.getContentId();

		redisService.addValue(key, newsDto);
	}

	public void print(String key) {
		Object obj = redisService.getValue(key);
		if (obj != null) {
			NewsContentFindDto news = objectMapper.convertValue(obj, NewsContentFindDto.class);
			System.out.println("news = " + news.toString());
		}
	}
}
