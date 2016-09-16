/**
 * 
 */
package com.ids.support;

import java.awt.image.BufferedImage;

import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.imageio.ImageIO;



/**
 *  
 */
public class Utility
{
	
	
	public static String MD(File source1) throws NoSuchAlgorithmException, FileNotFoundException 
	{
		String output="";
		MessageDigest digest = MessageDigest.getInstance("MD5");
		InputStream is = new FileInputStream(source1);				
		byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while( (read = is.read(buffer)) > 0) 
			{
				digest.update(buffer, 0, read);
			}		
			byte[] md5sum = digest.digest();
				System.out.print("Digets:"+md5sum);
			
			System.out.println();
			BigInteger bigInt = new BigInteger(1, md5sum);
			output = bigInt.toString(16);
			System.out.println("MD5: " + output);
		}
		catch(IOException e) 
		{
			System.out.println("Unable to process file for MD5"+e);
		}
		return output;
	}
	
	/* MD5 Algorithm(Ends) */
	
/* MD5 Algorithm(Starts) */
	
	public static String MD(InputStream inputStream) throws NoSuchAlgorithmException, FileNotFoundException 
	{
		String output="";
		MessageDigest digest = MessageDigest.getInstance("MD5");
		InputStream is = inputStream;				
		byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while( (read = is.read(buffer)) > 0) 
			{
				digest.update(buffer, 0, read);
			}		
			byte[] md5sum = digest.digest();
				System.out.print("Digets:"+md5sum);
			
			System.out.println();
			BigInteger bigInt = new BigInteger(1, md5sum);
			output = bigInt.toString(16);
			System.out.println("MD5: " + output);
		}
		catch(IOException e) 
		{
			System.out.println("Unable to process file for MD5"+e);
		}
		return output;
	}
	public static String MD1(String input) throws NoSuchAlgorithmException
	{
		String output="";
		MessageDigest digest = MessageDigest.getInstance("MD5");
					
		byte[] buffer = new byte[8192];
		
		digest.update(buffer);
			
		byte[] md5sum = digest.digest();
		System.out.print("Digets:"+md5sum);

		System.out.println();
		BigInteger bigInt = new BigInteger(1, md5sum);
		output = bigInt.toString(16);
		System.out.println("MD5: " + output);
		return output;
	}
	/* MD5 Algorithm(Ends) */
	
	public static void main(String asdf[]) throws NoSuchAlgorithmException
	{
		MD1("hello");
		
	}
}
