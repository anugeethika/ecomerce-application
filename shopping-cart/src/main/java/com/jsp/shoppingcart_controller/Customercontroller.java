package com.jsp.shoppingcart_controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dto.Customer;

@Controller
public class Customercontroller {
	@Autowired
	Customerdao cdao;
	@RequestMapping("/addcustomer")
	public ModelAndView addcustomer() {
		Customer c=new Customer();
		ModelAndView mav=new ModelAndView();
		mav.addObject("customerobj",c);
		mav.setViewName("customerform");
		return mav;
		
	}
	@RequestMapping("/savecustomer")
	public ModelAndView savecustomer( @ModelAttribute("customerobj") Customer c) {
		cdao.saveCustomer(c);
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","registed");
		mav.setViewName("customerloginform");
		return mav;
	}
	@RequestMapping("/customerloginvalidations")
	public ModelAndView  loginvalidation(ServletRequest req ,HttpSession session) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Customer c=cdao.login(email,password);
		if(c!=null) {
			ModelAndView mav=new ModelAndView();
			mav.addObject("msg","login succesful");
			mav.setViewName("customeroptions");
			session.setAttribute("customerinfo",c);
			return mav;
		}
		else {
			ModelAndView mav=new ModelAndView();
			mav.addObject("msg","invaild");
			mav.setViewName("customerloginform");
			return mav;
		}
	}
	
}
