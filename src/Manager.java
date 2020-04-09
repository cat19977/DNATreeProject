import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manager class that implements all of the project specified methods. It uses a
 * BST instance of type Rectangle to store and retrieve Rectangle data. It then
 * performs the necessary operations on the data and outputs correctly formatted
 * results.
 * 
 * @author ramyanandigam, catherinesquillante
 * @version 2020-2-21
 */
public class Manager {
    private Tree tree;

    /**
     * creates new manager instance with an empty BST of type Rectangle
     */
    public Manager() {
        this.tree = new Tree();
    }

    /**
     * 
     */
    public void insert(Sequence sequence) {
        // check if name is valid
        String regex = "[ACGT]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sequence.toString());
        char first = (sequence.toString()).charAt(0);
        if (!Character.isLetter(first) || !matcher.matches()) {
            System.out.print("sequence " + sequence + " is invalid" + "\n");
            return;
        }
        
        // successful insert
        tree.insert(sequence);
    }
    /**
     * 
     */
    public void remove(Sequence remSeq) {
        // check if name is valid
        String regex = "[ACGT]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(remSeq.toString());
        char first = (remSeq.toString()).charAt(0);
        if (!Character.isLetter(first) || !matcher.matches()) {
            System.out.print("sequence " + remSeq + " is invalid" + "\n");
            return;
        }
        
        tree.remove(remSeq);
    }

   
    /**
     * Traverses the tree in its current state and prints the data and level 
     * of each node in order.
     */
    public void search(Sequence sequence) {
    	// check if name is valid
        String regex = "[ACGT$]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sequence.toString());
        char first = (sequence.toString()).charAt(0);
        if (!Character.isLetter(first) || !matcher.matches()) {
            System.out.print("sequence " + sequence + " is invalid" + "\n");
            return;
        }
       tree.search(sequence);
    }

    /**
     * 
     */
    public void print() {
    	tree.print();

    }

    public void printLengths() {
    	tree.printLengths();

        }
    
    public void printStats() {
    	tree.printStats();

        }
}