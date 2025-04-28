package org.big.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.big.dao.PqsDao;
import org.big.dto.PqsDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PqsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int LISTCOUNT = 5; 
	
	@RequestMapping("/QnaListAction.do")
	public String listAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestQnaList(request);
		return "/qna/list";
	}
	
	@RequestMapping("/QnaWriteForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestLoginName(request);
		return "/qna/writeForm";
	}
	
	@RequestMapping("/QnaWriteAction.do")
	public String writeAction(HttpServletRequest request) throws ServletException, IOException {
		requestQnaWrite(request);
		return "qna:/QnaListAction.do";
	}
	
	@RequestMapping("/QnaViewAction.do")
	public String viewAction(HttpServletRequest request)
			throws ServletException, IOException {
		requestQnaView(request);
		return "/qna/view";
	}
	 
	
	@RequestMapping("/QnaUpdateAction.do")
	public String updateAction(HttpServletRequest request) throws ServletException, IOException {
		requestQnaUpdate(request);
		return "redirect:/QnaListAction.do";
	}
	
	@RequestMapping("/QnaDeleteAction.do")
	public String deleteAction(HttpServletRequest request) throws ServletException, IOException {
		requestQnaDelete(request);
		return "redirect:/QnaListAction.do";
	}
	
	public void requestQnaList(HttpServletRequest request){
			
		PqsDao dao = PqsDao.getInstance();
		ArrayList<PqsDto> qnalist = new ArrayList<PqsDto>();
		
	  	int pageNum=1;
		int limit=LISTCOUNT;
		
		if(request.getParameter("pageNum")!=null)
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
				
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		
		int total_record=dao.getListCount(items, text);
		qnalist = dao.getQnaList(pageNum,limit, items, text); 
		
		
		int total_page;
		
		if (total_record % limit == 0){     
	     	total_page =total_record/limit;
	     	Math.floor(total_page);  
		}
		else{
		   total_page =total_record/limit;
		   Math.floor(total_page); 
		   total_page =  total_page + 1; 
		}		
   
   		request.setAttribute("pageNum", pageNum);		  
   		request.setAttribute("total_page", total_page);   
		request.setAttribute("total_record",total_record); 
		request.setAttribute("qnalist", qnalist);								
	}
	
	public void requestLoginName(HttpServletRequest request){
					
		String id = request.getParameter("id");
		
		PqsDao dao = PqsDao.getInstance();
		
		String name = dao.getLoginNameById(id);		
		
		request.setAttribute("name", name);									
	}
   
	public void requestQnaWrite(HttpServletRequest request){
					
		PqsDao dao = PqsDao.getInstance();		
		
		PqsDto qna = new PqsDto();
		qna.setId(request.getParameter("id"));
		qna.setName(request.getParameter("name"));
		qna.setSubject(request.getParameter("subject"));
		qna.setContent(request.getParameter("content"));	
		
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("subject"));
		System.out.println(request.getParameter("content"));
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date()); 
		
		qna.setHit(0);
		qna.setRegist_day(regist_day);
		qna.setIp(request.getRemoteAddr());			
		
		dao.insertQna(qna);								
	}
	
	public void requestQnaView(HttpServletRequest request){
					
		PqsDao dao = PqsDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));	
		
		PqsDto qna = new PqsDto();
		qna = dao.getQnaByNum(num, pageNum);		
		
		request.setAttribute("num", num);		 
   		request.setAttribute("page", pageNum); 
   		request.setAttribute("qna", qna);   									
	}
	
	public void requestQnaUpdate(HttpServletRequest request){
					
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));	
		
		PqsDao dao = PqsDao.getInstance();		
		
		PqsDto qna = new PqsDto();		
		qna.setNum(num);
		qna.setName(request.getParameter("name"));
		qna.setSubject(request.getParameter("subject"));
		qna.setContent(request.getParameter("content"));		
		
		 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		 String regist_day = formatter.format(new java.util.Date()); 
		 
		 qna.setHit(0);
		 qna.setRegist_day(regist_day);
		 qna.setIp(request.getRemoteAddr());			
		
		 dao.updateQna(qna);								
	}
	
	public void requestQnaDelete(HttpServletRequest request){
					
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));	
		
		PqsDao dao = PqsDao.getInstance();
		dao.deleteQna(num);							
	}	
}

