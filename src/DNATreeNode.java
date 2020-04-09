/**
 * @author ramyanandigam, catherinesquillante
 *
 */
public interface DNATreeNode {
	public DNATreeNode insert(Sequence sequence);
	public DNATreeNode remove(Sequence sequence);
	public void print();
	public void printLengths();
	public void printStats();
	public void search(Sequence sequence);
	public void setLevel(int i);
	public int getLevel();
}
