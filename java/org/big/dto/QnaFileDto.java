package org.big.dto;

import lombok.Data;

@Data
public class QnaFileDto {
	private int idx;
	private int qnaIdx;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
}
