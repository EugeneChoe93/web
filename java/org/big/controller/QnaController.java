package org.big.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.big.dto.QnaDto;
import org.big.dto.QnaFileDto;
import org.big.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@RequestMapping("/qna/openQnaList.do")
	public ModelAndView openQnaList() throws Exception {
		log.debug("==============리스트 체크===========");
	
		ModelAndView mv = new ModelAndView("thymeleaf/qna/qnaList");
		
		List<QnaDto> list = qnaService.selectQnaList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/qna/openQnaWrite.do")
	public String openQnaWrite() throws Exception {
		return "thymeleaf/qna/qnaWrite";
	}
	
	@RequestMapping("/qna/insertQna.do")
	public String insertQna(QnaDto qna, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		qnaService.insertQna(qna, multipartHttpServletRequest);
		return "redirect:/qna/openQnaList.do";
	}
	
	@RequestMapping("/qna/openQnaDetail.do")
	public ModelAndView openQnaDetail(@RequestParam("qnaIdx") int qnaIdx) throws Exception{
		ModelAndView mv = new ModelAndView("thymeleaf/qna/qnaDetail");
		
		qnaService.updateHitCount(qnaIdx);
		QnaDto qna = qnaService.selectQnaDetail(qnaIdx);
		mv.addObject("qna", qna);
		return mv;
	}
	
	@RequestMapping("/qna/updateQna.do")
	public String updateQna(QnaDto qna) throws Exception {
		qnaService.updateQna(qna);
		return "redirect:/qna/openQnaList.do";
	}
	
	@RequestMapping("/qna/deleteQna.do")
	public String deleteQna(@RequestParam("qnaIdx") int qnaIdx) throws Exception {
		qnaService.deleteQna(qnaIdx);
		return "redirect:/qna/openQnaList.do";
	}
	
	@RequestMapping("/qna/downloadQnaFile.do")
	public void downloadQnaFile(@RequestParam("idx") int idx, @RequestParam("qnaIdx") int qnaIdx, HttpServletResponse response) throws Exception {
		QnaFileDto qnaFile = qnaService.selectQnaFileInformation(idx, qnaIdx);
		if(ObjectUtils.isEmpty(qnaFile) == false) {
			String fileName = qnaFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File(qnaFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}









