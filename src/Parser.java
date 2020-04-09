import java.io.File;
import java.util.Scanner;

/**
 * Parses the input file based on command.
 * 
 * @author ramyanandigam, catherinesquillante
 * @version 2020-2-21
 */
public class Parser {

    /**
     * Parses the command file. Separates the command from the parameters and 
     * then uses a manager object to call the appropriate command with given 
     * parameters.
     * 
     * @param filename file to parse
     */
    public void parseFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
            Manager manager = new Manager();

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                while (line == null || line.isEmpty() || line.trim().equals("") 
                        && sc.hasNextLine()) {
                    line = sc.nextLine();
                }
                line = line.trim();
                line = line.replaceAll("[\\n\\t]", " ");
                String[] splitLine = line.split("\\s+");

                String cmd = splitLine[0].trim();
                
                if (cmd.contentEquals("print") && splitLine.length == 2) {
                	if(splitLine[1].contentEquals("stats")) {
                		manager.printStats();
                	}
                	else if((splitLine[1].contentEquals("lengths"))) {
                		manager.printLengths();
                	}
                }

                else if (cmd.contentEquals("insert")) {
                    String insertSeq = splitLine[1];
                    Sequence newSequence = new Sequence(insertSeq);
                    manager.insert(newSequence);
                } 
                else if (cmd.contentEquals("remove")) {
                    // include both remove name & remove x,y,w,h
                	 String remSeq = splitLine[1];
                     Sequence newSequence = new Sequence(remSeq);
                     manager.remove(newSequence);
                    }

                else if (cmd.contentEquals("search")) {
                	 if(splitLine.length <2) {
                		 break;
                	 }
                	 String searchSeq = splitLine[1];
                     Sequence searchSequence = new Sequence(searchSeq);
                     manager.search(searchSequence);

                } 
                else if (cmd.contentEquals("print")) {
                	manager.print();
                }
                else {
                	//
                }
            }
            sc.close();
        }

        catch (Exception e) {
            e.printStackTrace();
           
        }
    }

}