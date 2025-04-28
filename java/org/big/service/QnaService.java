package org.big.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.big.dto.QnaDto;
import org.big.dto.QnaFileDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface QnaService {

	List<QnaDto> selectQnaList() throws Exception;
	void insertQna(QnaDto qna, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	void updateHitCount(int qnaIdx) throws Exception;
	QnaDto selectQnaDetail(int qnaIdx) throws Exception;
	void updateQna(QnaDto qna) throws Exception;
	void deleteQna(int qnaIdx) throws Exception;
	QnaFileDto selectQnaFileInformation(int idx, int qnaIdx) throws Exception;
}
