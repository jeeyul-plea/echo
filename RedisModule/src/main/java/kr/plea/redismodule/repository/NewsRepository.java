package kr.plea.redismodule.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.plea.redismodule.entity.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    Optional<News> findByContentId(String contentId);
}
