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
import com.ids.source.Source;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R1_Listener.java
 * @DateofCreation Oct 12, 2012
 * @CreatedTime 6:11:08 PM
 */
public class R1_MsgListener implements Runnable 
{
	int port;
	public ServerSocket r1_server;
	public BufferedReader r1_server_br = null;
	public Socket r1_client;

	
	public R1_MsgListener(int port) 
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
			if(this.port == Constants.R1_SERVER_PORT_NUM) 
   			{
				r1_server = new ServerSocket(Constants.R1_SERVER_PORT_NUM);
				
				while (true)
				{
					r1_client = r1_server.accept();
					r1_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r1_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r1_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r1_server_br.close();
					r1_client.close();
					R1.sendHeaderToNxtHop(Constants.PROFILE_NAME_2);
					try 
					{
						R1.sendRecData(buffer.toString());
					}
					catch (InterruptedException e) 
					{
						
						e.printStackTrace();
					} 
					
				
				}
   			}
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
