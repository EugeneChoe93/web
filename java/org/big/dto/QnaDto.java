package org.big.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class QnaDto {

	private int qnaIdx;
	private String title;
	private String content;
	private int hitCnt;
	private LocalDateTime createdDatetime;
	private String creatorId;
	private LocalDateTime updatedDatetime;
	private String updatorId;
	private List<QnaFileDto> fileList;
}
