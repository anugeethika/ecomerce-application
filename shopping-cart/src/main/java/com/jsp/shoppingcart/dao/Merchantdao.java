package com.jsp.shoppingcart.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Merchant;
import com.jsp.shoppingcart.dto.Product;
@Repository
public class Merchantdao {
	@Autowired
	EntityManagerFactory emf;
	Productdao pdao;
	public void saveMerchant(Merchant merchant) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(merchant);
		et.commit();
		
	}
	public Merchant login(String email,String password) {
		EntityManager em=emf.createEntityManager();
		Query query =em.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		query.setParameter(1,email);
		query.setParameter(2,password);
		try {
		Merchant merchant =(Merchant)query.getSingleResult();
		
			return merchant;
		}catch(NoResultException e) {
			return null;
			
		}
		
	}
	public void updateMerchant(Merchant m) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.persist(m);
		et.commit();
	}
	
	public void deleteMerchantById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Merchant m =em.find(Merchant.class, id);
		et.begin();
		em.remove(m);
		et.commit();
		}
	public Merchant findMerchantById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Merchant m =em.find(Merchant.class, id);
		if(m!=null) return m;
		else 
			return null;
				
	}
	public Merchant deleteproductfrommerchant(int merchant_id,int product_id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Merchant merchant=em.find(Merchant.class, merchant_id);
		List<Product> products=merchant.getProducts();
		//products.stream().filter(product->product.getId()!=product_id).collect(Collectors.toList());
		List<Product> productlist=new ArrayList();
		for(Product p:products) {
			if(p.getId()!=product_id) {
				productlist.add(p);
			}
			merchant.setProducts(productlist);
			
		
			}
		merchant.setProducts(productlist);
		System.out.println(productlist.size());
		return merchant;
	}
}
