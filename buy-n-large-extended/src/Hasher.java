/**
 author: Jenny Zhen
 date: 04.06.14
 language: Java
 file: Hasher.java
 assignment: BuyNLargeExtended
 http://www.cs.rit.edu/~wrc/courses/csci251/projects/2/
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * Hasher takes in a plaintext password and converts it into a byte array after using SHA-256 to hash. The byte array
 * is then converted into a hexadecimal string.
 */
public class Hasher {

    /**
     * Gets the hashed value of the plaintext password as a byte array.
     * @param data plaintext password to hash.
     * @return hashed password as a byte array.
     */
    public static byte[] getHash(String data) {
        MessageDigest messageDigest = null;
        byte[] result = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            result = data.getBytes("UTF-8");

            if (messageDigest != null) {
                messageDigest.update(result);
                result = messageDigest.digest();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Converts a hashed byte array into a hexadecimal string.
     * @param data the byte array to convert into a hexadecimal string.
     * @return a hexadecimal string representing the byte array given.
     */
    public static String byteArrayToHexString(byte[] data) {
        StringBuilder hexString = new StringBuilder();
        for (byte piece : data) {
            // Convert each byte to hex, while ANDing with 0xFF to get the least significant byte (masking).
            String hex = Integer.toHexString(0xFF & piece);

            // Check each byte for missing leading zeroes.
            if (hex.length() == 1)
                hexString.append('0');

            hexString.append(hex);
        }
        return hexString.toString();
    }
}