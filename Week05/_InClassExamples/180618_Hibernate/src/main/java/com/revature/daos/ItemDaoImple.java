package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Item;
import com.revature.util.HibernateUtil;

public class ItemDaoImple {
	public List<Item> getAllitems(){
		List<Item> items = null; //At this point, we have a transient state.
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			items = session.createQuery("FROM Item").list(); //At this point, items is persistent
			items.toString();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close(); 
		}
		return items;
	}

}
