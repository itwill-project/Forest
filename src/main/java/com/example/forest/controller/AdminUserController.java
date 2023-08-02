package com.example.forest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.forest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/adminUser")
public class AdminUserController {
	
	private final UserService userService;

	@GetMapping("/main")
	public void main() {
		log.info("main() GET");
	}
	
	@GetMapping("/adminUser")
	public void adminUser() {
		log.info("adminUser() GET");
	}
	
	@GetMapping("/adminReport")
	public void adminReport() {
		log.info("adminReport() GET");
	}
	
	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String postdelete(String no) throws Exception {
		Service.delete(no); // adminUserService 만들어야함.
	  return "redirect:adminUser";
	}
	
	// 게시물 선택삭제
	@RequestMapping(value = "/delete")
	public String ajaxTest(HttpServletRequest request) {
		
		String[] ajaxMsg = request.getParameterValues("valueArr");
		int size = ajaxMsg.length;
		for(int i=0; i<size; i++) {
			service.delete(ajaxMsg[i]); // adminUserService 만들기
		}
		return "redirect:adminUser";
	}
}























