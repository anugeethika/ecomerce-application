package com.jsp.shoppingcart_controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Cartdao;
import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dto.Cart;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Item;

@Controller
public class Cartcontroller {
	@Autowired
	Cartdao dao;
	@Autowired
	Customerdao custdao;
	@RequestMapping("/fetchitemsfromcart")
	public ModelAndView fetchItemsFromCart( HttpSession session) {
		Customer c =(Customer) session.getAttribute("customerinfo");
		Customer customer =custdao.findCustomerById(c.getId());
		Cart cart=customer.getCart();
		List<Item> items=cart.getItems();
				
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("itemslist",items);
		mav.addObject("totalprice",cart.getTotalprice());
		mav.setViewName("displaycartitemstocustomer");
		return mav;
	}
}
