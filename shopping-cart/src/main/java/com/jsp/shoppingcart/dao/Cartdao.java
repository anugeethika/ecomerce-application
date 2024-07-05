package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Cart;
@Repository
public class Cartdao {
	@Autowired
	EntityManagerFactory emf;
	public void saveCart(Cart cart) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et =em.getTransaction();
		et.begin();
		em.persist(cart);
		et.commit();
		}
	public Cart findcartbyid(int id) {
		EntityManager em=emf.createEntityManager();
		Cart i=em.find(Cart.class,id);
		if(i!=null)
			return i;
		else
			return null;
	}
	public void updateCart(Cart cart) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et =em.getTransaction();
		et.begin();
		em.persist(cart);
		et.commit();
	}
	public void deletecartbyid(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et =em.getTransaction();
		Cart i=em.find(Cart.class,id);
		et.begin();
		em.persist(i);
		et.commit();
		
	}
	public Cart removeAllItemFromCart(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et =em.getTransaction();
		Cart c =em.find(Cart.class,id);
		c.setItems(null);
		c.setTotalprice(0);
		et.begin();
		em.merge(c);
		et.commit();
		
		
	}
	public Cart removeItemCartFromCartById(int cart_id, int item_id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et =em.getTransaction();
		Cart c=em.find(Cart.class,item_id);
		et.begin();
		em.persist(c);
		et.commit();
		return c;
	}
	public Cart findItemById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et =em.getTransaction();
		Cart c=em.find(Cart.class,id);
		if(c!=null) return c;
		else
			return null;
	}
		
	}
	
