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
import com.ids.routers.R2;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R2_MsgListener.java
 * @DateofCreation Oct 15, 2012
 * @CreatedTime 2:45:20 PM
 */
public class R2_MsgListener implements Runnable 
{
	int port;
	public ServerSocket r2_server;
	public BufferedReader r2_server_br = null;
	public Socket r2_client;

	public R2_MsgListener(int port) 
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
			if(this.port == Constants.R2_SERVER_PORT_NUM) 
   			{
				r2_server = new ServerSocket(Constants.R2_SERVER_PORT_NUM);
				
				while (true)
				{
					r2_client = r2_server.accept();
					r2_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r2_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r2_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r2_server_br.close();
					r2_client.close();
					R2.sendHeaderToNxtHop(Constants.PROFILE_NAME_3);
					R2.sendRecData(buffer.toString()); 
					
				
				}
   			}
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
