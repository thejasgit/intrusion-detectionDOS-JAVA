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
import com.ids.routers.R2;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R2_IntruderListener.java
 * @DateofCreation Oct 22, 2012
 * @CreatedTime 11:52:11 AM
 */
public class R2_IntruderListener implements Runnable 
{
	int port;
	public ServerSocket r2_intrud_server;
	public BufferedReader r2_intrud_server_br = null;
	public Socket r2_intrud_client;
	
	
	public R2_IntruderListener(int port) 
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
			if(this.port == Constants.INTRUDE_REC_R2_SERVER_PORT_NUM) 
   			{
				r2_intrud_server = new ServerSocket(Constants.INTRUDE_REC_R2_SERVER_PORT_NUM);
				
				while (true)
				{
					r2_intrud_client = r2_intrud_server.accept();
					r2_intrud_server_br = new BufferedReader(new InputStreamReader(new BufferedInputStream(r2_intrud_client.getInputStream())));
					String strLine;
					StringBuffer buffer = new StringBuffer();
					
					while ((strLine = r2_intrud_server_br.readLine()) != null)
					{
						buffer.append(strLine + "\n");
					}
					r2_intrud_server_br.close();
					r2_intrud_client.close();
					ids.setAttackDDOSStatus(Constants.R2);
					R2.sendHeaderToNxtHop(Constants.PROFILE_NAME_3);
					try
					{
						R2.sendRecData(buffer.toString());
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
