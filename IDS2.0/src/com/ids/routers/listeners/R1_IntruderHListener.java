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
 * @FileName R1_IntruderHListener.java
 * @DateofCreation Oct 18, 2012
 * @CreatedTime 1:26:25 PM
 */
public class R1_IntruderHListener implements Runnable 
{
	int port;
	public ServerSocket r1_i_h_server;
	public BufferedReader r1_i_h_server_br = null;
	public Socket r1_i_h_client;
	
	
	public R1_IntruderHListener(int port) 
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
			if(this.port == Constants.INTRUDE_H_REC_R1_SERVER_PORT_NUM) 
   			{
				r1_i_h_server = new ServerSocket(Constants.INTRUDE_H_REC_R1_SERVER_PORT_NUM);
				
				while (true)
				{
					r1_i_h_client = r1_i_h_server.accept();
					r1_i_h_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r1_i_h_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r1_i_h_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r1_i_h_server_br.close();
					r1_i_h_client.close();
					
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
