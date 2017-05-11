package com.mycompany.rankj;

import junit.framework.TestCase;

public class RankHolderTest extends TestCase {

	private Journal ja;
	private Journal jb;
	private Journal jc;
	
	private RankHolder rh;
	
	protected void setUp() {
		
		rh = new RankHolder();
		
		
	}
	/*
	 * Scenario 1: Rank journals
	 * test three ranking position.
	 */
	public final void testGetRank() {
			
		ja = new Journal("Journal A", 5.6);
		jb = new Journal("Journal B", 2.1);
		jc = new Journal("Journal C", 3.1);
		
		rh.put(ja);
		rh.put(jb);
		rh.put(jc);
		
		rh.printAll();
		
		assertTrue(rh.getRank(jb) == 3);
		assertTrue(rh.getRank(jc) == 2);
		assertTrue(rh.getRank(ja) == 1);
		
	}
	
	/*
	 * the list of journals should be empty.
	 * ranking would be 0
	 */
	public final void testVoidList() {
		
		assertTrue(rh.getRank(new Journal("any name", 10.0)) == 0);
		
	}
	
	/*
	 * the list has only one journal
	 */
	public final void testRankOne() {
		
		ja = new Journal("Journal A", 5.6);
		
		rh.put(ja);
		assertTrue(rh.getRank(ja) == 1);
		
	}
	
	/*
	 * Scenario 2: Rank journals with a shared rank,
	 */
	public final void testSameRank() {
		
		ja = new Journal("Journal A", 2.2);
		jb = new Journal("Journal B", 6.2);
		jc = new Journal("Journal C", 6.2);
	
		rh.put(ja);
		rh.put(jb);
		rh.put(jc);
		
		rh.printAll();
		
		assertTrue(rh.getRank(jb) == 1);
		assertTrue(rh.getRank(jc) == 1);
		assertTrue(rh.getRank(ja) == 3);
		
	}
	
	/*
	 * Scenario 3: Rank journals excluding review journals:
	 */
	public final void testRankReview() {
		
		ja = new Journal("Journal A", 5.6, true);
		jb = new Journal("Journal B", 2.4);
		jc = new Journal("Journal C", 3.1);
	
		rh.put(ja);
		rh.put(jb);
		rh.put(jc);
		
		rh.printAll();
		
		assertTrue(rh.getRank(jb) == 2);
		assertTrue(rh.getRank(jc) == 1);
		assertTrue(rh.getRank(ja) == null);
		
		
	}
	
	
}
