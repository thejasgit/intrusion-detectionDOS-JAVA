/**
 * 
 */
package com.ids.destination.listener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.ids.constants.Constants;
import com.ids.destination.Destination;
import com.ids.routers.R1;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName Dest_MsgListener.java
 * @DateofCreation Oct 15, 2012
 * @CreatedTime 3:48:04 PM
 */
public class Dest_MsgListener implements Runnable 
{
	int port;
	public ServerSocket dest_server;
	public BufferedReader dest_server_br = null;
	public Socket r1_client;
	
	public Dest_MsgListener(int port) 
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
			if(this.port == Constants.DEST_SERVER_PORT_NUM) 
   			{
				dest_server = new ServerSocket(Constants.DEST_SERVER_PORT_NUM);
				
				while (true)
				{
					r1_client = dest_server.accept();
					dest_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r1_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = dest_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					dest_server_br.close();
					r1_client.close();
					
					Destination.sendRecData(buffer.toString()); 
					
				
				}
   			}
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
