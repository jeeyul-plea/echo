package kr.plea.redismodule.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import kr.plea.redismodule.dto.NewsContentDto;
import kr.plea.redismodule.dto.NewsUpdateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@RedisHash("News")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class News {
    @Id
    private String contentId;
    private String action;
    private String serviceType;
    private String sendDate;
    private String sendTime;
    private String source;
    private String writer;
    private String categoryCode;
    private String deskCode;
    private String deskValue;
    private String title;
    private String subTitle;
    private String comment;
    private String body;
    private Boolean isUsed;

    public News(NewsContentDto dto) {
        this.contentId = dto.getContentId();
        this.action = dto.getAction();
        this.serviceType = dto.getServiceType();
        this.sendDate = dto.getSendDate();
        this.sendTime = dto.getSendTime();
        this.source = dto.getSource();
        this.writer = dto.getWriter();
        this.categoryCode = dto.getCategoryCode();
        this.deskCode = dto.getDeskCode();
        this.deskValue = dto.getDeskValue();
        this.title = dto.getTitle();
        this.subTitle = dto.getSubTitle();
        this.comment = dto.getComment();
        this.body = dto.getBody();
        this.isUsed = dto.getIsUsed();
    }

    public News(String contentId, String title) {
        this.contentId = contentId;
        this.title = title;
    }

    public void update(NewsUpdateDto dto) {
        this.writer = dto.getWriter();
        this.title = dto.getTitle();
        this.subTitle = dto.getSubTitle();
        this.comment = dto.getComment();
        this.body = dto.getBody();
        this.isUsed = dto.getIsUsed();
    }
}
