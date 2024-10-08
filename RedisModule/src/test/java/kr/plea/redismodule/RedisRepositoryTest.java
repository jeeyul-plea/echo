package kr.plea.redismodule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.plea.redismodule.dto.NewsContentDto;
import kr.plea.redismodule.entity.News;
import kr.plea.redismodule.service.NewsService;

@SpringBootTest(classes = RedisModuleApplication.class)
public class RedisRepositoryTest {
    private final NewsContentDto NEWS_CONTENT  = new NewsContentDto("AKR20070807170200054", "C", "AKR0", "20070807", "171444", "YNA", "김요한", "07", "053",
        "대구경북취재본부", "대구 첫 전용미술관 9일 착공", "대구 첫 전용미술관 착공", "이 기사는 임의로 Element와 내용을 모두 채운 샘플입니다.", "대구 첫 전용미술관 9일 착공  ~~~ 말했따.", true);

    private NewsService newsService;

    @Autowired
    public RedisRepositoryTest(NewsService newsService) {
        this.newsService = newsService;
    }

    @AfterEach
    public void makeEmpty(){
        newsService.deleteUsingRepository("News:AKR20070807170200054");
    }

    @Test
    @DisplayName("RedisRepository를 통해서 News를 저장하고 조회 할 수 있다.")
    public void saveAndFind() {
        newsService.saveUsingRepository(NEWS_CONTENT);

        News findNews = newsService.findUsingRepository(NEWS_CONTENT.getContentId());

        Assertions.assertThat(findNews.getTitle()).isEqualTo(NEWS_CONTENT.getTitle());
    }
}
