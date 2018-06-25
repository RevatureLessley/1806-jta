package p0;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GenericTest {

	public static AccountList test;
	public static Launch pgm = new Launch();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Administrator admin = new Administrator("A", "B", "C",pgm);
		Banker banker = new Banker("B", "C", "D",pgm);
		Loaner loaner = new Loaner("C", "D", "E",pgm);
		ArrayList<Player> players = new ArrayList();
		players.add(new Player("D","E","F",100,100,0,pgm));
		pgm.Active = new AccountList(players,admin,banker,loaner);
	}

	@Test
	public void test1() 
	{
		Player p = pgm.Active.getList().get(0);
		int value1 = p.getbBalance();
		p.incrementTime();
		int value2 = p.getbBalance();
		assertTrue(value2 == (int)(value1*pgm.Active.getBanker().getInterest()));
	}

	@Test
	public void test2() 
	{
		int value1 = pgm.Active.getList().size();
		pgm.Active.add(new Player("E","F","G",100,100,100,pgm));
		int value2 = pgm.Active.getList().size();
		
		assertTrue(value1 == 1 && value2 == 2);
	}
	@Test
	public void test3() 
	{
		
		Player p = pgm.Active.getList().get(1);
		p.incrementTime();
		p.setHasLoan(true);
		p.incrementTime();
		assertTrue(p.getlBalance() == 100 * pgm.Active.getLoaner().getInterest());
	}
}
