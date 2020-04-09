/**
 * @author ramyanandigam, catherinesquillante
 *
 */
public class FlyweightNode implements DNATreeNode {
	private static FlyweightNode flyweight = null;
	private int level;

	/**
	 * Method to access flyweight
	 * 
	 * @return
	 */
	public static FlyweightNode getInstance() {
		
		if (flyweight == null) {
			flyweight = new FlyweightNode();
		}
		return flyweight;
	}
	
	private FlyweightNode(){
		
	}
	
	@Override
	public DNATreeNode insert(Sequence sequence) {
			return new LeafNode(sequence);
	}


	@Override
	public DNATreeNode remove(Sequence sequence) {
		Globals.removeFound = 1;
		System.out.print("sequence "+ sequence+ " does not exist\n");
		return new FlyweightNode();
	}

	@Override
	public void print() {
		for(int i=0; i < this.level; i++) {
			System.out.print("  ");
		}
		System.out.print("E\n");
	}

	@Override
	public void printLengths() {
		for(int i=0; i < this.level; i++) {
			System.out.print("  ");
		}
		System.out.print("E\n");
	}

	@Override
	public void printStats() {
		for(int i=0; i < this.level; i++) {
			System.out.print("  ");
		}
		System.out.print("E\n");
	}

	@Override
	public void search(Sequence seq) {
		Globals.searchNodes++;
	}
	
	public void setLevel(int i) {
		this.level = i;
	}
		
	public int getLevel() {
		return this.level;
	}

}