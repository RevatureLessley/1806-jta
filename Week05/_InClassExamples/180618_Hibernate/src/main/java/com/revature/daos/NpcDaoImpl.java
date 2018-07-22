package com.revature.daos;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
		npc.setName("Bobberty");
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
	
	public void HQLExample(){
		Session session = HibernateUtil.getSession();

		Query query;
		String hql;
		/*
		 * HQL is just like SQL, EXCEPT, we omit the column selections of queries.
		 */

		System.out.println("=====GER ALL NPCS=====");
		hql = "FROM Npc";
		query = session.createQuery(hql);
		System.out.println(query.list());
		
		System.out.println("=====PARAMETERIZED HQL=====");
		hql = "FROM Npc WHERE id = :nid";
		query = session.createQuery(hql);
		query.setParameter("nid", 4);
		System.out.println(query.uniqueResult());
		session.close();
	}
	
	public void projections(){
		//Projections let you perform statistical queries on database
		Session session = HibernateUtil.getSession();
		
		System.out.println("======AGGREGATES WITH CRITERIA======");
		Long npcCount = (Long)session.createCriteria(Npc.class).setProjection(
					Projections.count("id")
				).uniqueResult();
		System.out.println(npcCount);
		session.close();
	}
	
	public void criterias(){
		//Fully object oriented based queries.
		Session session = HibernateUtil.getSession();
		
		
		
		List<Npc> npcs;
		Criteria crit = session.createCriteria(Npc.class); //select * from npc
		npcs = crit.list(); //execute query
		
		for(Npc n : npcs){
			System.out.println(n);
		}
		System.out.println("=====fancier with this====");
		npcs = crit.add(Restrictions.and(
				Restrictions.like("name", "%Bob%"),
				Restrictions.gt("lvl", 55))).list();
		/*
		 * Criterions are filters/conditions for you query. They are found within
		 * the Restrictions library.
		 */
		
		
		for(Npc n : npcs){
			System.out.println(n);
		}
		
		
		session.close();
	}
	
	public void executeNamedQueries(){
		Session session = HibernateUtil.getSession();
		Query query1 = session.getNamedQuery("getAllNpcs");
		Query query2 = session.getNamedQuery("getNpc");
		Query query3 = session.getNamedQuery("originalSql");
		
		query2.setParameter("grapes", 3);
		query3.setParameter("bananas", 20);
		
		System.out.println(query1.list());
		System.out.println(query2.list());
		System.out.println(query3.list());
	}
	
	public Npc selectNpc(Integer id){
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Npc npc = null;
		
		try {
			tx = session.beginTransaction();
			
			npc= (Npc)session.get(Npc.class, id);
			
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(tx!=null) {
				tx.rollback();
			}
		}finally {
			session.close();
		}
		
		
		return npc;
	}
}	
