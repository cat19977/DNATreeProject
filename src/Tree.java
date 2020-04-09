public class Tree {
    private DNATreeNode root = null; // Root of tree

    /**
     * Construct a Tree with an EmptyLeafNode (flyweight) as the root Node.
     * 
     */
    public Tree() {
        root = FlyweightNode.getInstance();
    }
    
    /**
     * Inserts a stored sequence into the tree. Prints a message indicating the
     * command is executing
     */
    public void insert(Sequence newSequence) {
    	Globals.level = 0;
    	Globals.exists = 0;
        root = root.insert(newSequence);
        if(Globals.exists == 0) {
        	System.out.print("sequence " + newSequence + " inserted at level " + Globals.level + "\n");
        }
    }
    
    public void remove(Sequence sequence) {
    	if(root instanceof FlyweightNode) {
    		System.out.print("sequence " + sequence + " does not exist\n");
    		return;
    	}
    	Globals.removeFound = 0;
    	root = root.remove(sequence);

        if(Globals.removeFound == 0){
        	System.out.print("sequence " + sequence + " removed\n");
        }
    }
    
    public void print() {
        System.out.print("tree dump:\n");
        root.print();
    }
    
    public void printLengths() {
        System.out.print("tree dump:\n");
        root.printLengths();
    }
    
    public void printStats() {
        System.out.print("tree dump:\n");
        root.printStats();
    }
    
    public void search(Sequence seq) {
    	Globals.searchNodes = 0;
    	Globals.matches.clear();
        root.search(seq);
        System.out.print("# of nodes visited: "+Globals.searchNodes +"\n");
        if(Globals.matches.isEmpty()) {
        	System.out.println("no sequence found");
        }
        for(Sequence matchSeq: Globals.matches) {
        	System.out.println("sequence: "+ matchSeq);
        }
    }

}