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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Controller
public class RestQnaController {

	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public ModelAndView openQnaList() throws Exception {
	
		ModelAndView mv = new ModelAndView("thymeleaf/qna/restQnaList");
		
		List<QnaDto> list = qnaService.selectQnaList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value="/qna/write", method = RequestMethod.GET)
	public String openQnaWrite() throws Exception {
		return "thymeleaf/qna/restQnaWrite";
	}

	@RequestMapping(value="/qna/write", method = RequestMethod.POST)
	public String insertQna(QnaDto qna, MultipartHttpServletRequest multipartHttpServletRequest)
			throws Exception {
		qnaService.insertQna(qna, multipartHttpServletRequest);
		return "redirect:/qna";
	}

	@RequestMapping(value = "/qna/{qnaIdx}", method = RequestMethod.GET)
	public ModelAndView openQnaDetail(@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		ModelAndView mv = new ModelAndView("thymeleaf/qna/restQnaDetail");

		qnaService.updateHitCount(qnaIdx);
		QnaDto qna = qnaService.selectQnaDetail(qnaIdx);
		mv.addObject("qna", qna);
		return mv;
	}

	@RequestMapping(value = "/qna/{qnaIdx}", method = RequestMethod.PUT)
	public String updateQna(QnaDto qna) throws Exception {
		qnaService.updateQna(qna);
		return "redirect:/qna";
	}

	@RequestMapping(value = "/qna/{qnaIdx}", method = RequestMethod.DELETE)
	public String deleteQna(@PathVariable("qnaIdx") int qnaIdx) throws Exception {
		qnaService.deleteQna(qnaIdx);
		return "redirect:/qna";
	}

	@RequestMapping(value = "/qna/file", method = RequestMethod.GET)
	public void downloadQnaFile(@RequestParam("idx") int idx, @RequestParam("qnaIdx") int qnaIdx,
			HttpServletResponse response) throws Exception {
		QnaFileDto qnaFile = qnaService.selectQnaFileInformation(idx, qnaIdx);
		if (ObjectUtils.isEmpty(qnaFile) == false) {
			String fileName = qnaFile.getOriginalFileName();

			byte[] files = FileUtils.readFileToByteArray(new File(qnaFile.getStoredFilePath()));

			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");

			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
	 
	 
}