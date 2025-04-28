package org.big.service;

import java.util.List;

import org.big.common.FileUtils;
import org.big.dto.QnaDto;
import org.big.dto.QnaFileDto;
import org.big.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileUtils fileUtils;

	@Override
	public List<QnaDto> selectQnaList() throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.selectQnaList();
	}

	@Override
	public void insertQna(QnaDto qna, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.insertQna(qna);
		List<QnaFileDto> list = fileUtils.parseFileInfo(qna.getQnaIdx(), multipartHttpServletRequest);
		if (CollectionUtils.isEmpty(list) == false) {
			qnaMapper.insertQnaFileList(list);
		}
	}
	
	@Override
	public void updateHitCount(int qnaIdx) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.updateHitCount(qnaIdx);
	}
	
	@Override
	public QnaDto selectQnaDetail(int qnaIdx) throws Exception {
		// TODO Auto-generated method stub
		
		QnaDto qna = qnaMapper.selectQnaDetail(qnaIdx);
		
		List<QnaFileDto> fileList = qnaMapper.selectQnaFileList(qnaIdx);
		qna.setFileList(fileList);
		return qna;
	}
	
	@Override
	public void updateQna(QnaDto qna) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.updateQna(qna);
	}
	
	@Override
	public void deleteQna(int qnaIdx) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.deleteQna(qnaIdx);
	}
	
	@Override
	public QnaFileDto selectQnaFileInformation(int idx, int qnaIdx) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.selectQnaFileInformation(idx, qnaIdx);
	}
}
