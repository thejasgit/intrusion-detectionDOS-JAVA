/**
 * 
 */
package com.ids.routers.listeners;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.ids.constants.Constants;
import com.ids.routers.R1;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R1_HeaderContentListener.java
 * @DateofCreation Oct 16, 2012
 * @CreatedTime 2:48:36 PM
 */
public class R1_HeaderContentListener implements Runnable 
{
	int port;
	public ServerSocket r1h_server;
	public BufferedReader r1h_server_br = null;
	public Socket r1h_client;
	
	public R1_HeaderContentListener(int port) 
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
			if(this.port == Constants.HEADER_REC_R1_SERVER_PORT_NUM) 
   			{
				r1h_server = new ServerSocket(Constants.HEADER_REC_R1_SERVER_PORT_NUM);
				
				while (true)
				{
					r1h_client = r1h_server.accept();
					r1h_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r1h_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r1h_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r1h_server_br.close();
					r1h_client.close();
					
					R1.sendRecHeader(buffer.toString()); 
					
				
				}
   			}
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
