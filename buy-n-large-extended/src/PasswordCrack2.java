/**
 author: Jenny Zhen
 date: 04.06.14
 language: Java
 file: PasswordCrack2.java
 assignment: BuyNLargeExtended
 http://www.cs.rit.edu/~wrc/courses/csci251/projects/2/
 */

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

/**
 * PasswordCrack2 cracks the passwords of users in a database file that is 
 * read in. A brute-force attack is used.
 *
 * Passwords consist of anywhere from one through four characters, where each 
 * character is a lowercase letter a through z or a digit 0 through 9. Every 
 * such password is gone through, the digest of that password is computed, and 
 * checked to see if the password digest matches that of any user in the 
 * database.
 *
 * Example Run:
 * java pj2 PasswordCrack2 db.txt
 */
public class PasswordCrack2 extends Task {

    @Override
    /**
     * Runs the program: parses input file in data to be used by the threads 
	 * it start.
     * @param args array containing the name of the database file.
     */
    public void main(String[] args) throws Exception {
        // Check command line arguments.
        if(args.length != 1) {
            System.err.println("Usage: java PasswordCrack2 <databaseFile>");
            System.exit(0);
        }

        // Save command line arguments into something meaningful.
        String dbFile = args[0];

        // Read in the database file containing all users and their 
		// hashed passwords.
        final ArrayList<User> databaseOfUsers = new ArrayList<User>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dbFile));
            String line = reader.readLine();

            while (line != null) {
                String[] entry = line.split("\\s+");

                // Check if user exists already in the database array.
                boolean exists = false;
                for(User user : databaseOfUsers) {
                    if(user.getUser().equals(entry[0])) {
                        exists = true;
                        break;
                    }
                }
                // Add new user to database array.
                if(!exists)
                    databaseOfUsers.add(new User(entry[0], entry[1]));

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

		// Keeping track.
        final Semaphore numUsersProcessed = new Semaphore(0);
        final Semaphore numPasswordsFound = new Semaphore(0);

        // .exec() uses an anonymous class.
        parallelFor(0, databaseOfUsers.size() - 1).exec(new Loop() {
            @Override
            public void run(int i) throws Exception {
                User currentUser = databaseOfUsers.get(i);
                Generator generator = new Generator();

				// Generate all possible passwords, check for a match.
                for(String password : generator) {
                    String hashedPassword = 
						Hasher.byteArrayToHexString(Hasher.getHash(password));
					
					// Found a user with the generated password.
                    if(hashedPassword.equals(currentUser.getPassword())) {
                        System.out.println(
							currentUser.getUser() + " " + password);
						
						// Increment the count of the passwords cracked.
                        numPasswordsFound.release();
                        break;
                    }
                }
				
				// Increment the count of the users processed.
                numUsersProcessed.release();
            }
        });
        // As soon as all users have been processed, 
		// we need to print out the number processed.
        numUsersProcessed.acquire(databaseOfUsers.size());

		// Print the summary of the results.
        System.out.println(databaseOfUsers.size() + " users");
        System.out.println(
			numPasswordsFound.availablePermits() + " passwords found");
    }
}
