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
import com.ids.routers.R2;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R2_HeaderContentListener.java
 * @DateofCreation Oct 16, 2012
 * @CreatedTime 6:01:54 PM
 */
public class R2_HeaderContentListener implements Runnable 
{

	int port;
	public ServerSocket r2h_server;
	public BufferedReader r2h_server_br = null;
	public Socket r2h_client;
	
	public R2_HeaderContentListener(int port)
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
			if(this.port == Constants.HEADER_REC_R2_SERVER_PORT_NUM) 
   			{
				r2h_server = new ServerSocket(Constants.HEADER_REC_R2_SERVER_PORT_NUM);
				
				while (true)
				{
					r2h_client = r2h_server.accept();
					r2h_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r2h_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r2h_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r2h_server_br.close();
					r2h_client.close();
					
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
