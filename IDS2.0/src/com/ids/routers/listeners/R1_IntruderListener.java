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
import com.ids.dao.IdsDAO;
import com.ids.daoimplementation.IdsDAOImpl;
import com.ids.routers.R1;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R1_IntruderListener.java
 * @DateofCreation Oct 18, 2012
 * @CreatedTime 12:19:07 PM
 */
public class R1_IntruderListener implements Runnable 
{
	int port;
	public ServerSocket r1_server;
	public BufferedReader r1_intrud_server_br = null;
	public Socket r1_intrud_client;

	public R1_IntruderListener(int port) 
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
			IdsDAO ids = new IdsDAOImpl();
			if(this.port == Constants.INTRUDE_REC_R1_SERVER_PORT_NUM) 
   			{
				r1_server = new ServerSocket(Constants.INTRUDE_REC_R1_SERVER_PORT_NUM);
				
				while (true)
				{
					r1_intrud_client = r1_server.accept();
					r1_intrud_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r1_intrud_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r1_intrud_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r1_intrud_server_br.close();
					r1_intrud_client.close();
					ids.setAttackDDOSStatus(Constants.R1);
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
