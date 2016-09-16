/**
 * 
 */
package com.ids.source.listener;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.ids.constants.Constants;
import com.ids.source.Source;


/**
 * @author Prasoon Kumar Mishra
 * @ProjectName TARF
 * @FileName Source_Listener.java
 * @DateofCreation Sep 14, 2012
 * @CreatedTime 6:46:35 PM
 */
public class Source_Listener implements Runnable 
{

	int port;
	public ServerSocket src_server;
	public BufferedReader src_server_br = null;
	public Socket src_client;
	
	public Source_Listener(int port) 
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
			if(this.port == Constants.SOURCE_SERVER_PORT_NUM) 
   			{
				src_server = new ServerSocket(Constants.SOURCE_SERVER_PORT_NUM);
				while (true)
				{
					src_client = src_server.accept();
					src_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(src_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = src_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					src_server_br.close();
					src_client.close();
					Source.getReceivedData(buffer.toString());
				
				}
   			}
			
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
