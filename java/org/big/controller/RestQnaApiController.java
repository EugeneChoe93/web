package org.big.controller;

import java.util.List;

import org.big.dto.QnaDto;
import org.big.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RestQnaApiController {

	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "/api/qna", method = RequestMethod.GET)
	public List<QnaDto> openQnaList() throws Exception {
		return qnaService.selectQnaList();
	}
	
	@RequestMapping(value="/api/qna/write", method = RequestMethod.POST)
	public void insertQna(@RequestBody QnaDto qna) throws Exception {
		qnaService.insertQna(qna, null);
	}
	
	@RequestMapping(value = "/api/qna/{qnaIdx}", method = RequestMethod.GET)
	public QnaDto openQnaDetail(@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		qnaService.updateHitCount(qnaIdx);
		QnaDto qna = qnaService.selectQnaDetail(qnaIdx);
		return qna;
	}
	
	@RequestMapping(value = "/api/qna/{qnaIdx}", method = RequestMethod.PUT)
	public String updateQna(@RequestBody QnaDto qna) throws Exception {
		qnaService.updateQna(qna);
		return "redirect:/qna";
	}

	@DeleteMapping("/api/qna/{qnaIdx}")
	public String deleteQna(@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		qnaService.deleteQna(qnaIdx);
		return "redirect:/qna";
	}
}








