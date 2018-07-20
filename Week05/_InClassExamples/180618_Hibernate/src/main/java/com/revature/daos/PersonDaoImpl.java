package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Person;
import com.revature.util.HibernateUtil;

public class PersonDaoImpl {
	public Integer insertPerson(Person person){
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Integer id = null;
		
		try{
			tx = session.beginTransaction();
			id = (Integer)session.save(person);
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return id;
	}
	public List<Person> getAllPersons(){
		/*
		 * Persistent objects can be in 1 of 3 states throughout hibernate.
		 * 1. Transient
		 * 		The persistent is instantiated as an object, but this object is not
		 * 		linked with hibernate data at the given moment.
		 * 2. Persistent
		 * 		The object has been linked to a session and now represents actual data
		 * 		from the database. If I make changes to the object, and choose to save it,
		 * 		the changes will reflect in the database as well. Therefore persistent.
		 * 3. Detached
		 * 		The session has been closed, and the object does not directly link with
		 * 		data from the database anymore.
		 */
		List<Person> persons = null; //At this point, we have a transient state.
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			persons = session.createQuery("FROM Person").list(); //At this point, persons is persistent
			/*
			 * For DQL, we have two options to use for accessing items.
			 * We can use HQL (Hibernate Query Language) which is a semi abstracted
			 * SQL language that can be used to write DQL queries.
			 * HQL is notable fo rnot needing the 'select *' phrase since it is implied
			 * you will be grabbing all data to begin with, since you will be populating
			 * object representations anyway.
			 * .list() is how you execute the HQL to bring back a result set.
			 * If you expect only a single result, use .uniqueResult().
			 * This way you dont get back a List of one object.
			 */
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close(); //persons is now in the detached state.
		}
		return persons;
	}
	/**
	 * When invoking this update, be sure that the Person object you pass has
	 * the id field set to the id you want to update. After that, fill in any
	 * fields that you wish to be changed. Null will be ignored.
	 * @param Person p
	 * @return Person
	 */
	public Person updatePersonByIdFull(Person p){
		Person person = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			person = (Person)session.get(Person.class, p.getId());
			if(person!=null){
				if(p.getName()!=null){
					person.setName(p.getName());
				}
				if(p.getSalary()!=null){
					person.setSalary(p.getSalary());
				}
				if(p.getTitle()!=null){
					person.setTitle(p.getTitle());
				}
				session.save(person);
			}
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close(); 
		}
		return person;
	}
	
	public Person updatePersonNameById(Integer id, String name){
		Person person = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			person = (Person)session.get(Person.class, id);
			if(person!=null){
				person.setName(name);
				session.save(person);
			}
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close(); 
		}
		return person;
	}
	
	public Person getPersonById(Integer id){
		Person person = null;
		Session session = HibernateUtil.getSession();

		try{
			person = (Person)session.get(Person.class, id);
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close(); 
		}
		return person;
	}
	
	public Boolean deletePersonById(Integer id){
		Person person = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Boolean result = false;

		try{
			tx = session.beginTransaction();
			person = (Person)session.get(Person.class, id);
			if(person!=null){
				session.delete(person);
				result = true;
			}
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close(); 
		}
		return result;
	}
}
