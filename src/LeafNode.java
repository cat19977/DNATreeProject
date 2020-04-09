/**
 * @author ramyanandigam, 
 *
 */
public class LeafNode implements DNATreeNode {
	private Sequence sequence;
	private int level;
	
	public LeafNode(Sequence sequence) {
		this.sequence = sequence;
	}
	

	@Override
	public DNATreeNode insert(Sequence newSequence) {
		if (this.sequence.equals(newSequence)) {
			System.out.print("sequence "+newSequence+ " already exists\n");
			Globals.exists = 1;
			return this;
		}
		else {
			Globals.level--;
			return new InternalNode(this, newSequence);
		}
	}

	@Override
	public DNATreeNode remove(Sequence sequence) {
		if (this.sequence.equals(sequence)) {
			return FlyweightNode.getInstance();
		}
		else {
			Globals.removeFound = 1;
			System.out.print("sequence " + sequence + " does not exist\n");
			return this;
		}
	}

	@Override
	public void print() {
		for(int i=0; i < this.level; i++) {
			System.out.print("  ");
		}
		System.out.print(sequence.toString() + '\n');
	}

	@Override
	public void printLengths() {
		for(int i=0; i < this.level; i++) {
			System.out.print("  ");
		}
		System.out.print(sequence.toString() + " " + sequence.length() + '\n');
	}

	@Override
	public void printStats() {
		for(int i=0; i < this.level; i++) {
			System.out.print("  ");
		}
		System.out.print(sequence.toString() + " " + sequence.getStats() + '\n');
	}

	@Override
	public void search(Sequence seq) {
		Globals.searchNodes++;
		Boolean match = true;
		if(seq.toString().contains("$")){
			String newStr = seq.toString().substring(0, seq.toString().length()-1);
			seq = new Sequence(newStr);
		}
		if(seq.length()>this.sequence.length()) {
			return;
		}
		while(seq.hasNext() && this.sequence.hasNext()) {
			if(seq.next() != this.sequence.next()) {
				match =false;
			}
		}
		if(match) {
			Globals.matches.add(this.sequence);
		}
	}
	
	public Sequence getSequence() {
		return this.sequence;
	}
	
	public void setSequence(Sequence newSequence) {
		this.sequence = newSequence;
	}
	
	public void setLevel(int i) {
		this.level = i;
	}
		
	public int getLevel() {
		return this.level;
	}

}