package com.jsp.shoppingcart_controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Merchantdao;
import com.jsp.shoppingcart.dto.Merchant;

@Controller
public class Merchantcontroller {
	@Autowired
	Merchantdao dao;
	@RequestMapping("/addmerchant")
	public ModelAndView addmerchant() {
		Merchant m=new Merchant();
		ModelAndView mav=new ModelAndView();
		mav.addObject("merchantobj", m);
		mav.setViewName("merchantform");
		return mav;
		
	}
	@RequestMapping("/savemerchant")
	public ModelAndView saveMerchant(@ModelAttribute("merchantobj") Merchant m) {
		dao.saveMerchant(m);
		ModelAndView mav=new ModelAndView();
		mav.addObject("message", "data saved succesfully");
		mav.setViewName("menu");
		return mav;
		
	}
	@RequestMapping("/loginvalidations")
	public ModelAndView login(ServletRequest request ,HttpSession session) {
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		
		Merchant m=dao.login(email,password);
		ModelAndView mav=new ModelAndView();
		
		if(m!=null) {
			mav.addObject("msg","successfully loged in");
			mav.setViewName("merchantoptions");
			session.setAttribute("merchantinfo",m);
			return mav;
		}else {
			mav.addObject("msg","invaild credintials");
			mav.setViewName("merchantloginform");
			return mav;
		}
		
	}

}
