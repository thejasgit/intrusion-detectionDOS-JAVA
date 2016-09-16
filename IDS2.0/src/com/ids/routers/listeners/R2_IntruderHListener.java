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
 * @FileName R2_IntruderHListener.java
 * @DateofCreation Oct 22, 2012
 * @CreatedTime 11:39:27 AM
 */
public class R2_IntruderHListener implements Runnable 
{
	int port;
	public ServerSocket r2_i_h_server;
	public BufferedReader r2_i_h_server_br = null;
	public Socket r2_i_h_client;
	
	public R2_IntruderHListener(int port) 
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
			if(this.port == Constants.INTRUDE_H_REC_R2_SERVER_PORT_NUM) 
   			{
				r2_i_h_server = new ServerSocket(Constants.INTRUDE_H_REC_R2_SERVER_PORT_NUM);
				
				while (true)
				{
					r2_i_h_client = r2_i_h_server.accept();
					r2_i_h_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r2_i_h_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r2_i_h_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r2_i_h_server_br.close();
					r2_i_h_client.close();
					
					R2.sendRecHeader(buffer.toString()); 
					
				
				}
   			}
			
		}
		catch (IOException e)
		{
			System.out.println(e); 
		}


	}

}
