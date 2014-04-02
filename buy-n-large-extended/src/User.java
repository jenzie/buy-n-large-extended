/**
 author: Jenny Zhen
 date: 04.06.14
 language: Java
 file: User.java
 assignment: BuyNLargeExtended
 http://www.cs.rit.edu/~wrc/courses/csci251/projects/2/
 */

public class User {
    private String user;
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}
