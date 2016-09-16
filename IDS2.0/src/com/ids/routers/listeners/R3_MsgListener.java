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
import com.ids.routers.R3;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R3_MsgListener.java
 * @DateofCreation Oct 13, 2012
 * @CreatedTime 5:00:45 PM
 */
public class R3_MsgListener implements Runnable 
{
	int port;
	public ServerSocket r3_server;
	public BufferedReader r3_server_br = null;
	public Socket r3_client;

	public R3_MsgListener(int port) 
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
			if(this.port == Constants.R3_SERVER_PORT_NUM) 
   			{
				r3_server = new ServerSocket(Constants.R3_SERVER_PORT_NUM);
				
				while (true)
				{
					r3_client = r3_server.accept();
					r3_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r3_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r3_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r3_server_br.close();
					r3_client.close();
					R3.sendRecData(buffer.toString());
				
				}
   			}
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
