package com.jarvis.hrm;

import org.apache.commons.codec.binary.Base64;

/**
 * Generating the password
 *
 */
public class PasswordGenerator 
{
    public static void main( String[] args )
    {
    	if (args.length < 1) {
            System.err.println("Please provide an input!");
            System.exit(0);
        }
        System.out.println(encodedValue(args[0]));
    }
    
    private static String encodedValue(String text) {
    	return new String(Base64.encodeBase64(text.getBytes()));
    }
}
