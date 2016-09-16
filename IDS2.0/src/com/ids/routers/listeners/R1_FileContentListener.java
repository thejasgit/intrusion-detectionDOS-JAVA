/**
 * 
 */
package com.ids.routers.listeners;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.ids.constants.Constants;
import com.ids.dao.IdsDAO;
import com.ids.daoimplementation.IdsDAOImpl;
import com.ids.routers.R1;
import com.ids.routers.R3;


/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R1_FileContentListener.java
 * @DateofCreation Oct 13, 2012
 * @CreatedTime 12:00:14 PM
 */
public class R1_FileContentListener implements Runnable 
{
	int port;
	public Socket connection;
	public BufferedReader server_br = null;
	public ServerSocket server;
	PrintWriter put=null;
	static String fileName;


	public R1_FileContentListener(int port) 
	{
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		try
		{
			if(this.port == Constants.FILE_REC_R1_SERVER_PORT_NUM) 
   			{
				server = new ServerSocket(Constants.FILE_REC_R1_SERVER_PORT_NUM); 
				IdsDAO ids = new IdsDAOImpl();
				
				 while (true)
				 {
					 connection = server.accept();
					 server_br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
					 put=new PrintWriter(connection.getOutputStream());
					 fileName = ids.getFileName();
//					 String file = received_data1.trim();//file name to save
					 put.println(fileName);
			        	
					 File f1=new File(Constants.R1_STORAGE+fileName);  
					 FileOutputStream  fs=new FileOutputStream(f1);
					 BufferedInputStream d=new BufferedInputStream(connection.getInputStream());
	   			        	
					 BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(f1));
					 byte buffer[] = new byte[1024];
					 int read;
	   			        	
					 while((read = d.read(buffer))!=-1)
					 {
						 outStream.write(buffer, 0, read);
						 outStream.flush();
					 }
					 fs.close();
					 connection.close();
					 R1.sendHeaderToNxtHop(Constants.PROFILE_NAME_2);
					 Thread.sleep(2000);
					 R1.sendRecACk(fileName);
				 }
   			}
			
			
		}
		catch (Exception e)
		{
			System.out.println("----------------------------------------"+e); 
		}

	}

	public static void sendFileName(String fname) 
	{
		fileName = fname;
		
		
	}

	
}
