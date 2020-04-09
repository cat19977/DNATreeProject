/**
 * @author ramyanandigam, catherinesquillante
 *
 */
public class InternalNode implements DNATreeNode {

	private DNATreeNode A;
	private DNATreeNode C;
	private DNATreeNode G;
	private DNATreeNode T;
	private DNATreeNode $;
	private int level;

	public static final int MIN_NON_EMPTY_LEAF_CHILDREN = 2;
	
	
	/**
	 * Constructs an InternalNode given the SequenceNode to be pushed down and the
	 * new Sequence. Internal nodes are always created with exactly 2 sequences (one
	 * of which is an existing SequenceNode)
	 * 
	 * @param prevNode
	 * @param newSequence
	 */
	public InternalNode(LeafNode prevNode, Sequence newSequence) {
		/*
		 * Fill child to be empty leaf nodes by setting them to flyweight
		 */
		DNATreeNode flyweight = FlyweightNode.getInstance();
		A = flyweight;
		C = flyweight;
		G = flyweight;
		T = flyweight;
		$ = flyweight;
		this.level = Globals.level;

		/* get reference to existing node's sequence */
		Sequence prevSequence = prevNode.getSequence();
		
		if (prevSequence.equals(newSequence)) { //check if duplicate
			System.out.print("sequence " + newSequence + " already exists\n");
			Globals.exists = 1;
		}
		/*
		 * Prioritize insert by sequence length
		 */
		Sequence one, two;
		if (prevSequence.length() < newSequence.length()) {
			one = newSequence;
			two = prevSequence;
		} else {
			one = prevSequence;
			two = newSequence;
		}
		
		insert(one);
		insert(two);
			
	}

	private DNATreeNode getChild(char sequenceCharacter) {
		switch (sequenceCharacter) {
		case 'A':
			return A;
		case 'C':
			return C;
		case 'G':
			return G;
		case 'T':
			return T;
		case '$':
			return $;
		}
		return null;
	}

	@Override
	public DNATreeNode insert(Sequence sequence) {
		Globals.level++;
		//check if exists
		if (($ instanceof LeafNode) && ((LeafNode) $).getSequence().equals(sequence)) {
			System.out.print("Sequence "+ sequence + " already exists\n");
			Globals.exists = 1;
			return this;
		}
		if (sequence.hasNext()) {
			// Take the next character
			char sequenceChar = sequence.next();
			// Get the associated child node
			DNATreeNode child = getChild(sequenceChar);

			/*
			 *  If non-empty prefix is longer than sequence: switch
			 *  If child is empty: take child Prefix is empty and no other
			 *  child exist: take prefix
			 */
			if (($ instanceof LeafNode) && ((LeafNode) $).getSequence().length() > sequence.length()
					&& sequence.isPrefixOf(((LeafNode) $).getSequence())) {
				// Swap with prefix
				insert(swapPrefix(sequence));

			} else { /* Otherwise insert into respective child */
				/*
				 * Assign the child to the result of inserting into it: - If child is
				 * SequenceNode, insert will return the replacement InternalNode - If child is
				 * Flyweight, insert will return new SequenceNode - If child is InternalNode,
				 * insert will return the same InternalNode
				 */
				setChild(sequenceChar, child.insert(sequence));
			}}
		else {
				insertPrefix(sequence);
		}

		// InternalNode don't move during insert, so return this
		return this;
	}

	/**
	 * Assigns prefix to a new sequence and returns the current sequence.
	 * 
	 * NOTE: This can only be called when prefix node is confirmed to be a
	 * SequenceNode
	 * 
	 * @param newPrefixSeq
	 * @return
	 */
	private Sequence swapPrefix(Sequence newPrefixSeq) {
		Sequence oldPrefix = ((LeafNode) $).getSequence();
		((LeafNode) $).setSequence(newPrefixSeq);
		return oldPrefix;
	}


	public void insertPrefix(Sequence sequence) {
		if ($ instanceof FlyweightNode) {
			$ = $.insert(sequence);
		} else {
			insert(swapPrefix(sequence));
		}
	}

	public boolean setChild(char sequenceChar, DNATreeNode child) {
		switch (sequenceChar) {
		case 'A':
			A = child;
			return true;
		case 'C':
			C = child;
			return true;
		case 'G':
			G = child;
			return true;
		case 'T':
			T = child;
			return true;
		case '$':
			$ = child;
			return true;
		}
		return false;
	}

	/**
	 * Gets an array of child nodes
	 * 
	 * @return a Node array containing the children
	 */
	public DNATreeNode[] getChildren() {
		return new DNATreeNode[] { A, C, G, T, $ };
	}

	@Override
	public DNATreeNode remove(Sequence sequence) {
		
		if (sequence.hasNext()) {
			// Take the next character
			final char sequenceChar = sequence.next();
			// Get the associated child node
			DNATreeNode child = getChild(sequenceChar);
			setChild(sequenceChar, child.remove(sequence));
		
		} else {
			
			if ((($ instanceof LeafNode) && !((LeafNode) $).getSequence().equals(sequence))
			||!(($ instanceof LeafNode))){
				System.out.print("Sequence "+ sequence + " does not exist\n");
				Globals.removeFound = 1;
				return this;
			}
			$ = $.remove(sequence);
		}

		DNATreeNode collapsible = null;
		for (DNATreeNode child : getChildren()) {
			// We cannot collapse if a child is an InternalNode
			if (child instanceof InternalNode) {
				return this;
			} else if (child instanceof LeafNode) {
				// If we find more than 1 SequenceLeafNode, we can't collapse
				if (collapsible == null) {
					collapsible = child;
				} else {
					return this;
				}
			}
		}
		
		if(collapsible != null) {
			((LeafNode) collapsible).setLevel(this.level-1);
			((LeafNode) collapsible).getSequence().prev();
			return collapsible;
		}
		return this;
	}

	@Override
	public void print() {
		for (int i = 0; i < this.level + 1; i++) {
			System.out.print("  ");
		}
		System.out.print("I\n");
		A.setLevel(this.level + 2);
		A.print();
		C.setLevel(this.level + 2);
		C.print();
		G.setLevel(this.level + 2);
		G.print();
		T.setLevel(this.level + 2);
		T.print();
		$.setLevel(this.level + 2);
		$.print();

	}

	@Override
	public void printLengths() {
		for (int i = 0; i < this.level + 1; i++) {
			System.out.print("  ");
		}
		System.out.print("I\n");
		A.setLevel(this.level + 2);
		A.printLengths();
		C.setLevel(this.level + 2);
		C.printLengths();
		G.setLevel(this.level + 2);
		G.printLengths();
		T.setLevel(this.level + 2);
		T.printLengths();
		$.setLevel(this.level + 2);
		$.printLengths();
	}

	@Override
	public void printStats() {
		for (int i = 0; i < this.level + 1; i++) {
			System.out.print("  ");
		}
		System.out.print("I\n");
		A.setLevel(this.level + 2);
		A.printStats();
		C.setLevel(this.level + 2);
		C.printStats();
		G.setLevel(this.level + 2);
		G.printStats();
		T.setLevel(this.level + 2);
		T.printStats();
		$.setLevel(this.level + 2);
		$.printStats();
	}

	@Override
	public void search(Sequence seq) {
		Globals.searchNodes++;

		/* Check if we have seen all of the characters in the searchSequence */
		if (seq.hasNext()) {

			char curr = seq.next();
			/* Get the corresponding child node */
			DNATreeNode child = getChild(curr);
			/* Call search on child */
			child.search(seq);
		}
		else if(seq.toString().contains("$")) {
			$.search(seq);
		} else {
			A.search(seq);
			C.search(seq);
			G.search(seq);
			T.search(seq);
			$.search(seq);
		}
	}

	@Override
	public void setLevel(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

}