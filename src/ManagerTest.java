import student.TestCase;

/**
 * Test cases for the manager class
 * 
 * @author ramyanandigam, catherinesquillante
 */
public class ManagerTest extends TestCase {

	private Manager manager;

	public void setUp() {
		manager = new Manager();
	}

	public void testInsert() {
		Sequence test = new Sequence("BBBB");
		manager.insert(test);
		String e = "sequence BBBB is invalid";
		assertFuzzyEquals(e, systemOut().getHistory());
		systemOut().clearHistory();
		
		Sequence test0 = new Sequence(" ");
		manager.insert(test0);
		String e3 = "sequence  is invalid";
		assertFuzzyEquals(e3, systemOut().getHistory());
		systemOut().clearHistory();

		Sequence test1 = new Sequence("ATTTTTA");
		manager.insert(test1);
		String e1 = "sequence ATTTTTA inserted at level 0";
		assertFuzzyEquals(e1, systemOut().getHistory());
		systemOut().clearHistory();
		
		Sequence test2 = new Sequence("ATTTTTA");
		manager.insert(test2);
		String e4 = "sequence ATTTTTA already exists";
		assertFuzzyEquals(e4, systemOut().getHistory());
		systemOut().clearHistory();
		
		Sequence test3 = new Sequence("CT");
		manager.insert(test3);
		String e5 = "sequence CT inserted at level 1";
		assertFuzzyEquals(e5, systemOut().getHistory());
		systemOut().clearHistory();
		
		Sequence test4 = new Sequence("CTTG");
		manager.insert(test4);
		String e6 = "sequence CTTG inserted at level 3";
		assertFuzzyEquals(e6, systemOut().getHistory());
		systemOut().clearHistory();
		
		Sequence test5 = new Sequence("CTGT");
		manager.insert(test5);
		String e7 = "sequence CTGT inserted at level 3";
		assertFuzzyEquals(e7, systemOut().getHistory());
		systemOut().clearHistory();
		
		Sequence test6 = new Sequence("CT");
		manager.insert(test6);
		String e8 = "sequence CT already exists";
		assertFuzzyEquals(e8, systemOut().getHistory());
		systemOut().clearHistory();
	}
	
	public void testSearch() {
		Manager man1 = new Manager();
		Sequence t = new Sequence("AAA");
		Sequence t1 = new Sequence("AA");
		man1.insert(t);
		man1.insert(t1);
		systemOut().clearHistory();
		man1.search(new Sequence("AA"));
		String e = "# of nodes visited: 8\nsequence: AAA\nsequence: AA";
		assertFuzzyEquals(e, systemOut().getHistory());
	    systemOut().clearHistory();
	}
	
	 public void testRemove() {
       Sequence test = new Sequence("BBBB");
       manager.remove(test);
       String e = "sequence BBBB is invalid";
       assertFuzzyEquals(e, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test0 = new Sequence(" ");
       manager.remove(test0);
       String e1 = "sequence  is invalid";
       assertFuzzyEquals(e1, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test1 = new Sequence("ATTTTTA");
       manager.remove(test1);
       String e2 = "sequence ATTTTTA does not exist";
       assertFuzzyEquals(e2, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test2 = new Sequence("ATTTTTA");
       manager.insert(test2);
       String e3 = "sequence ATTTTTA inserted at level 0";
       assertFuzzyEquals(e3, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test3 = new Sequence("ATTTTTA");
       manager.remove(test3);
       String e4 = "sequence ATTTTTA removed";
       assertFuzzyEquals(e4, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test4 = new Sequence("ATTTTTA");
       manager.insert(test4);
       String e5 = "sequence ATTTTTA inserted at level 0";
       assertFuzzyEquals(e5, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test5 = new Sequence("CT");
       manager.insert(test5);
       String e6 = "sequence CT inserted at level 1";
       assertFuzzyEquals(e6, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test6 = new Sequence("CTTG");
       manager.insert(test6);
       String e7 = "sequence CTTG inserted at level 3";
       assertFuzzyEquals(e7, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test7 = new Sequence("CTGT");
       manager.insert(test7);
       String e8 = "sequence CTGT inserted at level 3";
       assertFuzzyEquals(e8, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test8 = new Sequence("CTTG");
       manager.remove(test8);
       String e9 = "sequence CTTG removed";
       assertFuzzyEquals(e9, systemOut().getHistory());
       systemOut().clearHistory();
       
       Manager man3 = new Manager();
       man3.insert(new Sequence("ACG"));
       man3.insert(new Sequence("GAT"));
       man3.insert(new Sequence("GCAT"));
       man3.insert(new Sequence("GTA"));
       systemOut().clearHistory();
       man3.remove(new Sequence("GGCT"));
       String e10 = "sequence GGCT does not exist";
       assertFuzzyEquals(e10, systemOut().getHistory());
       systemOut().clearHistory();
       man3.remove(new Sequence("GGGT"));
       String e11 = "sequence GGGT does not exist";
       assertFuzzyEquals(e11, systemOut().getHistory());
   }
	
	 public void testInsRemove() {
		 Manager man2 = new Manager();
		 Sequence test = new Sequence("AAAA");
	     man2.insert(test);
	     String e = "sequence AAAA inserted at level 0";
	     assertFuzzyEquals(e, systemOut().getHistory());
	     systemOut().clearHistory();
	 }
	 
	 public void testPrint() {
       manager.print();
       String e = "tree dump:\nE";
       assertFuzzyEquals(e, systemOut().getHistory());
       systemOut().clearHistory();
       
       Sequence test1 = new Sequence("AC");
       manager.insert(test1);
       systemOut().clearHistory();
       manager.print();
       String e1 = "tree dump:\nAC";
       assertFuzzyEquals(e1, systemOut().getHistory());
       systemOut().clearHistory();
       manager.remove(test1);
       systemOut().clearHistory();
       manager.print();
       String e2 = "tree dump:\nE";
       assertFuzzyEquals(e2, systemOut().getHistory());
       
       Sequence test2 = new Sequence("ATTTTTA");
       Sequence test3 = new Sequence("CT");
       manager.insert(test2);
       manager.insert(test3);
       manager.remove(test1);
       systemOut().clearHistory();
       
      //
       
       
   }
}