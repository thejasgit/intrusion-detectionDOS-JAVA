/**
 * 
 */
package com.ids.servicelocator;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ids.constants.Constants;

/**
 * @author Prasoon Kumar Mishra
 *
 */
public class DBConnector 
{
	public static Connection db_connector()
	{
		Connection con=null;
		try
		{
			Class.forName(Constants.JDBC_DRIVER);
			con = DriverManager.getConnection(Constants.JDBC_HOST_URL_WITH_DBNAME,Constants.DB_USERNAME, Constants.DB_PASSWORD);
			System.out.println("  connected  ");
		}
		catch (Exception e)
		{
			System.out.println("---- Exception in backend Connection ----- " +e); 
		}
		
		
		return con;
	}

}
