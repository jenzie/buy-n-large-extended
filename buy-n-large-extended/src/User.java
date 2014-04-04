/**
 author: Jenny Zhen
 date: 04.06.14
 language: Java
 file: User.java
 assignment: BuyNLargeExtended
 http://www.cs.rit.edu/~wrc/courses/csci251/projects/2/
 */

 /**
 * User stores the credentials for one user.
 * The password is hashed once using SHA-256.
 */
public class User {
    private String user;
    private String password;

	/**
	* Constructor.
	* @param user username of the user.
	* @password SHA-256 hashed password of the user.
	*/
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

	/**
	* Getter for the user's username.
	* @return the user.
	*/
    public String getUser() {
        return this.user;
    }

	/**
	* Getter for the user's SHA-256 hashed password.
	* @return the password.
	*/
    public String getPassword() {
        return this.password;
    }
}
