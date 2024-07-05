package com.jsp.shoppingcart_controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Merchantdao;
import com.jsp.shoppingcart.dao.Productdao;
import com.jsp.shoppingcart.dto.Merchant;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class Productcontroller {
	@Autowired
	Productdao dao;
	@Autowired
	Merchantdao mdao;

	@RequestMapping("/addproduct")
	public ModelAndView addproduct() {

		Product p = new Product();
		ModelAndView mav = new ModelAndView();
		mav.addObject("productobj",p);
		mav.setViewName("productform");
		return mav;
	}

	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj") Product p, HttpSession session) {
		Merchant merchant = (Merchant) session.getAttribute("merchantinfo");
		List<Product> products = merchant.getProducts();
		if (products.size() > 0) {
			products.add(p);
			merchant.setProducts(products);
		} else {
			List<Product> productslist = new ArrayList<Product>();
			productslist.add(p);
			merchant.setProducts(productslist);
		}
		dao.saveProduct(p);
		mdao.updateMerchant(merchant);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "data saved successfully");
		mav.setViewName("merchantoption");
		return mav;
	}

	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam("id") int id, HttpSession session) {
		Merchant m = (Merchant) session.getAttribute("merchantinfo");
		mdao.deleteMerchantById(id);

		dao.deleteProductById(id);
		session.setAttribute("merchantinfo", m);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewallProducts");

		return mav;
	}

	@RequestMapping("displayproducts")
	public ModelAndView displayproducts() {
		List<Product> products = dao.fetchAllProducts();
		ModelAndView mav = new ModelAndView();
		mav.addObject("productslist", products);
		mav.setViewName("viewallproductstocustomer");
		return mav;

	}
	@RequestMapping("/displayproductsbybrand")
	public ModelAndView displayproductsbybrand(ServletRequest req) {
		String brand =req.getParameter("brand");
		List<Product> products=dao.findproductbybrand(brand);
		ModelAndView mav = new ModelAndView();
		mav.addObject("productlist",products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}

}

