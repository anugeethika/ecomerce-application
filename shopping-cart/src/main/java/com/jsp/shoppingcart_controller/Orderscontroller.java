package com.jsp.shoppingcart_controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Cartdao;
import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dao.Orderdao;
import com.jsp.shoppingcart.dao.Productdao;
import com.jsp.shoppingcart.dto.Cart;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Item;
import com.jsp.shoppingcart.dto.Orders;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class Orderscontroller {
	@Autowired
	Orderdao dao;
	@Autowired
	Customerdao custdao;
	@Autowired
	Productdao pdao;
	@Autowired
	Cartdao cdao;
	
	@RequestMapping("/addorder")
	public ModelAndView addOrder() {
		Orders o=new Orders();
		ModelAndView mav=new ModelAndView();
		mav.addObject("ordersobj",o);
		mav.setViewName("ordersform");
		return mav;
	}
	@RequestMapping("/saveorder")
	public ModelAndView saveOrder(@ModelAttribute("orderobj") Orders o, HttpSession session) {
		Customer c =(Customer) session.getAttribute("customerinfo");
		Customer customer=custdao.findCustomerById(c.getId());
		Cart cart=customer.getCart();
		
		List<Item> items=cart.getItems();
		List<Item> itemslist=new ArrayList<>();
		
		List<Item> itemswitchedgreaterquantity=new ArrayList<>();
		
		for(Item i:items) {
			Product p=pdao.findProductById(i.getP_id());
			if(i.getQuantity()<p.getStock()) {
				itemslist.add(i);
				p.setStock(p.getStock()-i.getQuantity());
				
				pdao.updateProduct(p);
			}else {
				itemswitchedgreaterquantity.add(i);
			}
		}
		
		o.setItems(itemslist);
		double totalpriceoforder=0;
		for(Item i:itemslist) {
			totalpriceoforder=totalpriceoforder+i.getPrice();
		}
		o.setTotalprice(totalpriceoforder);
		cart.setItems(itemswitchedgreaterquantity);
		
		double totalprice=0;
		for(Item i:itemswitchedgreaterquantity ) {
			totalprice=totalprice+i.getPrice();
		}
		
		cart.setTotalprice(totalprice);
		List<Orders> orders=customer.getOrders();
		if(orders.size()>0) {
			orders.add(o);
			customer.setOrders(orders);
			
		}else {
			List<Orders> orders1=new ArrayList<>();
			orders1.add(o);
			customer.setOrders(orders1);
			
		}
		customer.setCart(cart);
		cdao.updateCart(cart);
		dao.saveOrder(o);
		custdao.updateCustomer(customer);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","order placed successfully");
		mav.addObject("orderdetails",o);
		mav.setViewName("customerbill");
		return mav;
	}
}

