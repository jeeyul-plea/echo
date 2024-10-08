package kr.plea.redismodule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NewsContentDto {
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

	public NewsContentDto(String contentId, String action, String serviceType, String sendData, String sendTime,
		String source, String writer, String categoryCode, String deskCode, String deskValue, String title,
		String subTitle,
		String comment, String body, Boolean isUsed) {
		this.contentId = contentId;
		this.action = action;
		this.serviceType = serviceType;
		this.sendDate = sendData;
		this.sendTime = sendTime;
		this.source = source;
		this.writer = writer;
		this.categoryCode = categoryCode;
		this.deskCode = deskCode;
		this.deskValue = deskValue;
		this.title = title;
		this.subTitle = subTitle;
		this.comment = comment;
		this.body = body;
		this.isUsed = isUsed;
	}
}