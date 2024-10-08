package kr.plea.redismodule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.plea.redismodule.dto.NewsContentDto;
import kr.plea.redismodule.dto.NewsUpdateDto;
import kr.plea.redismodule.entity.News;
import kr.plea.redismodule.service.NewsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public ResponseEntity saveNews(@RequestBody NewsContentDto newsContentDto) {
        newsService.saveUsingRepository(newsContentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity findNews(@PathVariable("key") String key) {
        News findNews = newsService.findUsingRepository(key);
        return ResponseEntity.ok().body(findNews);
    }

    @PatchMapping()
    public ResponseEntity updateNews(@RequestBody NewsUpdateDto newsUpdateDto) {
        newsService.updateNews(newsUpdateDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{key}")
    public ResponseEntity deleteNews(@PathVariable("key") String key) {
        newsService.deleteUsingRepository(key);
        return ResponseEntity.ok().build();
    }
}
