import java.io.FileNotFoundException;

/**
 * @author ramyanandigam, catherinesquillante
 *
 */
public class DNAtree {

    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args){
    	String fileName = args[0];

		Parser parser = new Parser();
		parser.parseFile(fileName);
    }

}