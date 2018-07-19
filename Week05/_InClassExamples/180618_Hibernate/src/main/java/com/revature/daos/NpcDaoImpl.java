package com.revature.daos;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Item;
import com.revature.beans.Job;
import com.revature.beans.Npc;
import com.revature.beans.Shop;
import com.revature.util.HibernateUtil;

public class NpcDaoImpl {
	public Boolean populateDatabase(){
		//List<Shop> shops = Arrays.asList(a){new Shop(), new Shop()}
		
		//CREATE DATA
		Item item = new Item(
					"Premium Example Water",
					20
				);
		Shop shop = new Shop();
		shop.setName("bob's bobs");
		
		List<Shop> shops = Arrays.asList(shop);
		
		Npc npc = new Npc();
		
		Job job = new Job();
		job.setName("Professional bob");
		npc.setCurrency(999999);
		npc.setJob(job);
		npc.setLvl(99);
		npc.setName("Bobberto");
		shop.setOwnerId(npc);
		item.setShops(shops);
		
		
		
		//INSERT DATA
		
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.save(job);
			session.save(npc);
			session.save(shop);
			session.save(item);
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return true;
	}
}	
