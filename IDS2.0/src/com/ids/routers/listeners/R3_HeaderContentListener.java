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
 * @FileName R3_HeaderContentListener.java
 * @DateofCreation Oct 17, 2012
 * @CreatedTime 10:25:19 AM
 */
public class R3_HeaderContentListener implements Runnable 
{

	int port;
	public ServerSocket r3h_server;
	public BufferedReader r3h_server_br = null;
	public Socket r3h_client;
	
	public R3_HeaderContentListener(int port) 
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
			if(this.port == Constants.HEADER_REC_R3_SERVER_PORT_NUM) 
   			{
				r3h_server = new ServerSocket(Constants.HEADER_REC_R3_SERVER_PORT_NUM);
				
				while (true)
				{
					r3h_client = r3h_server.accept();
					r3h_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r3h_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r3h_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r3h_server_br.close();
					r3h_client.close();
					
					R3.sendRecHeader(buffer.toString()); 
					
				
				}
   			}
			
		}       
		catch (IOException e)
		{
			System.out.println(e); 
		}

	}

}
