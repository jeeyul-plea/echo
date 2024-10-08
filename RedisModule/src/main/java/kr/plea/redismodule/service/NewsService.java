package kr.plea.redismodule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.plea.redismodule.common.ApiStatusCode;
import kr.plea.redismodule.common.exception.RootException;
import kr.plea.redismodule.dto.NewsContentDto;
import kr.plea.redismodule.dto.NewsUpdateDto;
import kr.plea.redismodule.entity.News;
import kr.plea.redismodule.repository.NewsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {
	private final RedisTemplateService redisTemplateService;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final NewsRepository newsRepository;

	public void saveUsingTemplate(NewsContentDto newsDto) {
		String key = newsDto.getContentId();

		redisTemplateService.addValue(key, newsDto);
	}

	public void saveUsingRepository(NewsContentDto newsDto) {
		String key = newsDto.getContentId();
		News findNews = findUsingRepository(key);
		if (findNews.getContentId().equals("NO_CONTENT")) {
			News news = new News(newsDto);
			newsRepository.save(news);
		}else{
			throw new RootException(ApiStatusCode.NOT_FOUND, null);
		}

	}

	@Transactional(readOnly = true)
	public News findUsingRepository(String key){
		News fakeNews = new News("NO_CONTENT", "해당키값의 뉴스가 존재하지 않음");
		return newsRepository.findByContentId(key).orElse(fakeNews);
	}

	public void deleteUsingRepository(String key){
		News findNews = findUsingRepository(key);
		newsRepository.delete(findNews);
	}

	public void updateNews(NewsUpdateDto newsDto) {
		News findNews = findUsingRepository(newsDto.getContentId());
		if(findNews.getContentId().equals("NO_CONTENT")){
			throw new RootException(ApiStatusCode.NOT_FOUND, "해당 아이디의 뉴스 콘텐츠가 존재하지 않습니다.");
		}
		findNews.update(newsDto);
		newsRepository.save(findNews);
	}

	public void print(String key) {
		Object obj = redisTemplateService.getValue(key);
		if (obj != null) {
			NewsContentDto news = objectMapper.convertValue(obj, NewsContentDto.class);
			System.out.println("news = " + news.toString());
		}
	}
}
