package javaClasses;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;


public class hashingValidateClass
{
    public static String hashThePass(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        //The number of iterations must be noted
        int iterations = 1000;
        char[] setChar = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(setChar,salt,iterations,32*4); //increase key length is possible
        SecretKeyFactory secretK = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); //the string changeable Note: Use a random characters Note: Must be changed to something else

        byte[] hash = secretK.generateSecret(spec).getEncoded();
        return  iterations + ":" + convertoHex(salt) + ":" + convertoHex(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG"); //the parameter is changeable Note: this too must be changed
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String convertoHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0" + paddingLength + "d",0) + hex;
        }
        else
        {
            return hex;
        }
    }

    //This will be the most used once the user has their credentials in the database
    public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = convertfromHex(parts[1]);
        byte[] hash = convertfromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),
                salt, iterations, hash.length * 8);
        SecretKeyFactory theSecretkey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = theSecretkey.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }


    private static byte[] convertfromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
