package com.revature.main;

import com.revature.beans.Person;
import com.revature.daos.ItemDaoImple;
import com.revature.daos.NpcDaoImpl;
import com.revature.daos.PersonDaoImpl;

public class Driver {

	public static void main(String[] args) {
		System.out.println(" =====Launching App=====");
		
		PersonDaoImpl pd = new PersonDaoImpl();
		int id3 = pd.insertPerson(new Person("Bobbert", "Professional Bob", 1232000));
		int id2 = pd.insertPerson(new Person("Ryan", "Lead Trainer", -16));
		int id = pd.insertPerson(new Person("Timbert", "Lead Trainer", 0));
		//session.save() returns the id of the record to be inserted.
		
		
		pd.deletePersonById(id);
		pd.updatePersonNameById(id2, "Fragliminate");
		pd.updatePersonByIdFull(new Person(id3,null,null,2500000));
		
		System.out.println(pd.getAllPersons());
		
		System.out.println("=====NPC DATABASE=====");
		NpcDaoImpl nd = new NpcDaoImpl();
		nd.populateDatabase();
		
		ItemDaoImple idi = new ItemDaoImple();
		System.out.println(idi.getAllitems());
		
		System.out.println("  =====GET VS LOAD=====");
		
		idi.getVsLoad();
		idi.SaveVsPersist();
		idi.mergeVsUpdate();
		
		NpcDaoImpl ndi = new NpcDaoImpl();
		ndi.HQLExample();
		ndi.projections();
		
		ndi.criterias();
		ndi.executeNamedQueries();
		
		System.out.println("=====L2 CACHE=====");
		
		System.out.println(ndi.selectNpc(3).getName());
		System.out.println(ndi.selectNpc(3).getName());
		
		System.out.println("=====Terminating App=====");
		System.exit(0);
	}

}
