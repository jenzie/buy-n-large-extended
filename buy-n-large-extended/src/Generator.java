/**
 author: Jenny Zhen
 date: 04.06.14
 language: Java
 file: Generator.java
 assignment: BuyNLargeExtended
 http://www.cs.rit.edu/~wrc/courses/csci251/projects/2/
 */

import java.util.Iterator;

/**
 * Generator takes in a plaintext password and then it gets the next one in the sequence.
 *
 * Passwords consist of anywhere from one through four characters, where each character is a lowercase letter
 * a through z or a digit 0 through 9.
 */
public class Generator implements Iterator<String> {
    char[] password = {'a', 0, 0, 0};

    @Override
    public boolean hasNext() {
        return new String(password).equals("9999");
    }

    @Override
    public String next() {
        // Save the current password to return.
        String currentPassword = new String(password);

        // Increment to get the next password before returning the current password.
        for(int i = 0; i < password.length; i++) {
            if(password[i] == 'z') {
                password[i] = '0';
                break;
            }
            else if(password[i] == '9') {
                password[i] = 'a';
            }
            else if(password[i] == 0)
                password[i] = 'a';
            else
                password[i] = password[i]++;
        }

        return currentPassword;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
