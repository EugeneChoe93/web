package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.QnaDto;
import org.big.dto.QnaFileDto;

@Mapper
public interface QnaMapper {

	List<QnaDto> selectQnaList() throws Exception;
	void insertQna(QnaDto qna) throws Exception;
	void updateHitCount(int qnaIdx) throws Exception;
	QnaDto selectQnaDetail(int qnaIdx) throws Exception;
	void updateQna(QnaDto qna) throws Exception;
	void deleteQna(int qnaIdx) throws Exception;
	void insertQnaFileList(List<QnaFileDto> list) throws Exception;
	List<QnaFileDto> selectQnaFileList(int qnaIdx) throws Exception;
	QnaFileDto selectQnaFileInformation(@Param("idx") int idx, @Param("qnaIdx") int qnaIdx) throws Exception;
}
