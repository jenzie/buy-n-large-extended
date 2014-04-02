import edu.rit.pj2.Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 author: Jenny Zhen
 date: 04.06.14
 language: Java
 file: PasswordCrack2.java
 assignment: BuyNLargeExtended
 http://www.cs.rit.edu/~wrc/courses/csci251/projects/2/
 */

/**
 * PasswordCrack2 cracks the passwords of users in a database file that is read in. A brute-force attack is used.
 *
 * Passwords consist of anywhere from one through four characters, where each character is a lowercase letter
 * a through z or a digit 0 through 9. Every such password is gone through, the digest of that password is computed,
 * and checked to see if the password digest matches that of any user in the database.
 *
 * Example Run:
 * java pj2 PasswordCrack2 db.txt
 */
public class PasswordCrack2 extends Task {

    @Override
    /**
     * Runs the program: parses input file in data to be used by the threads it start.
     * @param args array containing the name of the database file.
     */
    public void main(String[] args) throws Exception {
        // Check command line arguments.
        if(args.length != 1) {
            System.err.println("Usage: java pj2 PasswordCrack2 <databaseFile>");
            System.exit(0);
        }

        // Save command line arguments into something meaningful.
        String dbFile = args[0];

        // Read in the database file containing all users and their hashed passwords.
        HashMap<String, String> databaseOfUsers = new HashMap<String, String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dbFile));
            String line = reader.readLine();

            while (line != null) {
                String[] entry = line.split("\\s+");
                if(!databaseOfUsers.containsKey(entry[0]))
                    databaseOfUsers.put(entry[0], entry[1]);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println(
                    "Usage: java PasswordCrack dictionary db\n" +
                            "Error: File " + dbFile + " could not be read.");
        } catch (IOException e) {
            System.err.println(
                    "Usage: java PasswordCrack dictionary db\n" +
                            "Error: File " + dbFile + " is empty.");
        }
    }
}
