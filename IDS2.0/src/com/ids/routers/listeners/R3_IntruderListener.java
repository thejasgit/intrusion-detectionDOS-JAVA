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
import com.ids.routers.R2;
import com.ids.routers.R3;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R3_IntruderListener.java
 * @DateofCreation Oct 22, 2012
 * @CreatedTime 5:09:50 PM
 */
public class R3_IntruderListener implements Runnable 
{

	int port;
	public ServerSocket r3_intrud_server;
	public BufferedReader r3_intrud_server_br = null;
	public Socket r3_intrud_client;
	
	public R3_IntruderListener(int port) 
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
			if(this.port == Constants.INTRUDE_REC_R3_SERVER_PORT_NUM) 
   			{
				r3_intrud_server = new ServerSocket(Constants.INTRUDE_REC_R3_SERVER_PORT_NUM);
				
				while (true)
				{
					r3_intrud_client = r3_intrud_server.accept();
					r3_intrud_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r3_intrud_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r3_intrud_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r3_intrud_server_br.close();
					r3_intrud_client.close();
					ids.setAttackDDOSStatus(Constants.R3);
					R3.sendHeaderToNxtHop(Constants.PROFILE_NAME_3);
					try
					{
						R3.sendRecData(buffer.toString());
					}
					catch (Exception e) 
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
