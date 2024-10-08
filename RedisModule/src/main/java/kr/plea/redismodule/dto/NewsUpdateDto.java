package kr.plea.redismodule.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class NewsUpdateDto {
    private String contentId;
    private String writer;
    private String title;
    private String subTitle;
    private String comment;
    private String body;
    private Boolean isUsed;
}
