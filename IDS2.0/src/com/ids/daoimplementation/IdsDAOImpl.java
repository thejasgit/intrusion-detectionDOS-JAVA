/**
 * 
 */
package com.ids.daoimplementation;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ids.constants.Constants;
import com.ids.dao.IdsDAO;
import com.ids.servicelocator.DBConnector;



/**
 * @author Prasoon Kumar Mishra
 *
 */
public class IdsDAOImpl implements IdsDAO 
{
	private Connection connection = null;
	private Statement stmt= null;
	private ResultSet res = null;
	
	
	public String[] getType1IP(String v_type1Node1)
	{
		String address[] = null;
		ArrayList rowValues = new ArrayList();
		try
		{
			String query = "select node_ip,node_port from address_tbl where node_name = '"+v_type1Node1+"'";
			
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			res = stmt.executeQuery(query);
			while(res.next())
			{
				
				rowValues.add(res.getString("node_ip"));
				rowValues.add(res.getString("node_port"));
			
			}
			address = (String[]) rowValues.toArray(new String[rowValues.size()]);
		}
		
		catch(Exception e)
		{
			
		}
		return address;
	}

	public void storeFileName(String fname)
	{
		try
		{
			String query1 = "insert into m_fname (f_name) values ('"+fname.trim()+"')";
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			stmt.execute(query1);
			
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public void deletePreviousFname()
	{
		try
		{
			String query1 = "delete from m_fname";
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			stmt.execute(query1);
			
		}
		
		catch(Exception e)
		{
			System.out.println(e); 
		}
	}
	
	public String getFileName()
	{
		String fname="";
		try
		{
			String query1 = "select * from m_fname";
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			res = stmt.executeQuery(query1);
			while(res.next())
			{
				fname = res.getString("f_name");
			}
			
			
			
		}
		
		catch(Exception e)
		{
			System.out.println(e); 
		}
		return fname;
	}
	
	
	public void updateCurrentLod(String profileName,String rName)
	{
		try
		{
			
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			if(rName.equals(Constants.R1))
			{
				String query1 = "update m_r1 set current_lod=current_lod+'1'where profile_name='"+profileName.trim()+"'";
				stmt.executeUpdate(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "update m_r2 set current_lod=current_lod+'1'where profile_name='"+profileName.trim()+"'";
				stmt.executeUpdate(query1);
			}
			else
			{
				String query1 = "update m_r3 set current_lod=current_lod+'1'where profile_name='"+profileName.trim()+"'";
				stmt.executeUpdate(query1);
			}
			
			
		}
		
		catch(Exception e)
		{
			System.out.println(e); 
		}
	}
	
	public boolean checkProfileExistence(String header,String rName)
	{
		boolean flag=false;
		try
		{
			
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			if(rName.equals(Constants.R1))
			{
				String query1 = "select * from m_r1 where profile_name = '"+header.trim()+"'";
				res = stmt.executeQuery(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "select * from m_r2 where profile_name = '"+header.trim()+"'";
				res = stmt.executeQuery(query1);
			}
			else
			{
				String query1 = "select * from m_r3 where profile_name = '"+header.trim()+"'";
				res = stmt.executeQuery(query1);
			}
			
			
			
			while(res.next())
			{
				flag = true;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return flag; 
	}
	
	
	public void insertProfile(String header, int load, int minThreshold, String rName , String profileStatus)
	{
		try
		{
			 
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			if(rName.equals(Constants.R1))
			{
				String query1 = "insert into m_r1 (profile_name,tot_load,th_min,current_lod,profile_status)values('"+header.trim()+"','"+load+"','"+minThreshold+"','0','"+profileStatus+"')";
				stmt.execute(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "insert into m_r2 (profile_name,tot_load,th_min,current_lod,profile_status)values('"+header.trim()+"','"+load+"','"+minThreshold+"','0','"+profileStatus+"')";
				stmt.execute(query1);
			}
			else
			{
				String query1 = "insert into m_r3 (profile_name,tot_load,th_min,current_lod,profile_status)values('"+header.trim()+"','"+load+"','"+minThreshold+"','0','"+profileStatus+"')";
				stmt.execute(query1);
			}
			
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	public int getTotalProfiles(String rNmae)
	{
		int totProf = 0;
		try
		{
			 
			
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			if(rNmae.equals(Constants.R1))
			{
				String query1 = "select max(profile_no) from m_r1";
				res = stmt.executeQuery(query1);
			}
			else if(rNmae.equals(Constants.R2))
			{
				String query1 = "select max(profile_no) from m_r2";
				res = stmt.executeQuery(query1);
			}
			else
			{
				String query1 = "select max(profile_no) from m_r3";
				res = stmt.executeQuery(query1);
			}
			
			
			while(res.next())
			{
				totProf = res.getInt(1);
			}
			
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return totProf;
	}
	
	public void updateMaxThreshold(int maxThreshold , String rName)
	{
		try
		{
			 
			
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			if(rName.equals(Constants.R1))
			{
				String query1 = "update m_r1 set th_max = '"+maxThreshold+"'";
				stmt.executeUpdate(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "update m_r2 set th_max = '"+maxThreshold+"'";
				stmt.executeUpdate(query1);
			}
			else
			{
				String query1 = "update m_r3 set th_max = '"+maxThreshold+"'";
				stmt.executeUpdate(query1);
			}
			
			 
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public List getCurrLodAndMaxTh(String profName, String rName)
	{
		List values = new ArrayList();
		

		try
		{	
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			if(rName.equals(Constants.R1))
			{
				String query1 = "select current_lod,th_max from m_r1 where profile_name = '"+profName.trim()+"'"; 
				res = stmt.executeQuery(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "select current_lod,th_max from m_r2 where profile_name = '"+profName.trim()+"'";
				res = stmt.executeQuery(query1);
			}
			else
			{
				String query1 = "select current_lod,th_max from m_r3 where profile_name = '"+profName.trim()+"'";
				res = stmt.executeQuery(query1);
			}
			
			
			while(res.next())
			{
				values.add(res.getInt("current_lod"));
				values.add(res.getInt("th_max"));
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return values;
	}
	
	
	public void updateProfileStatus(String profName, String rName)
	{
		try
		{	
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			if(rName.equals(Constants.R1))
			{
				String query1 = "update m_r1 set profile_status='"+Constants.PROFILE_CLOSE+"' where profile_name='"+profName.trim()+"' "; 
				System.out.println(query1);
				stmt.executeUpdate(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "update m_r2 set profile_status='"+Constants.PROFILE_CLOSE+"' where profile_name='"+profName.trim()+"' ";
				System.out.println(query1);
				stmt.executeUpdate(query1);
			}
			else
			{
				String query1 = "update m_r3 set profile_status='"+Constants.PROFILE_CLOSE+"' where profile_name='"+profName.trim()+"' ";
				System.out.println(query1);
				stmt.executeUpdate(query1);
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public String getStatus(String rName, String profName)
	{
		String status="";
		
		try
		{	
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			if(rName.equals(Constants.R1))
			{
				String query1 = "select profile_status from m_r1 where profile_name = '"+profName.trim()+"'"; 
				System.out.println(query1);
				res = stmt.executeQuery(query1);
			}
			else if(rName.equals(Constants.R2))
			{
				String query1 = "select profile_status from m_r2 where profile_name = '"+profName.trim()+"'";
				System.out.println(query1);
				res = stmt.executeQuery(query1);
			}
			else
			{
				String query1 = "select profile_status from m_r3 where profile_name = '"+profName.trim()+"'";
				System.out.println(query1);
				res = stmt.executeQuery(query1);
			}
			
			
			while(res.next())
			{
				status = res.getString("profile_status");
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		return status;
		
	}
	
	public String getRname2Attack()
	{
		String rName="";
		
		try
		{	
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			String query1 = "SELECT r_name from m_ddos_atk_status where r_status='false' ORDER BY RAND() LIMIT 1 ";
			System.out.println(query1);
			res = stmt.executeQuery(query1);
			
			
			while(res.next())
			{
				rName = res.getString(1);
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return rName;
	}
	
	
	public void setAttackDDOSStatus(String rName)
	{
		try
		{
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			String query1 = " UPDATE m_ddos_atk_status SET r_status = '"+Constants.R_Status+"' where r_name = '"+rName.trim()+"'";
			
			stmt.executeUpdate(query1);
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}
	public boolean setAttackDDOSStatus1(String ip,String hmac,int count,String ip_attacker)
	{
		boolean flag=false;
		
		try
		{
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			String query1 = " insert into m_hash (ip_address,hmac,count,ip_attacker) values('"+ip+"','"+hmac+"','"+(count+1)+"','"+ip_attacker+"')";
			int c=stmt.executeUpdate(query1);
			if(c>0)
			{
				System.out.println("   setAttackDDOSStatus1   "+flag);
			
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return flag;
		
	}
	
	public boolean setAttackDDOSStatus2(String ip,String hmac,int count,String ip_attacker)
	{
		boolean flag=false;
		
		try
		{
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			String query1 = "update  m_hash set ip_address='"+ip+"',hmac='"+hmac+"',count=count+1,ip_attacker='"+ip_attacker+"' where ip_address='"+ip+"'";
			int c=stmt.executeUpdate(query1);
			if(c>0)
			{
				System.out.println("    setAttackDDOSStatus1   "+flag);
			
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return flag;
		
	}
	
	public boolean checkAttackDDOSStatus1(String ip)
	{
		boolean flag=false;
		
		try
		{
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			String query1 = "SELECT ip_address from m_hash  where ip_address='"+ip+"'";
			System.out.println(query1);
			res = stmt.executeQuery(query1);
			
			
			while(res.next())
			{
				flag=true;
				System.out.println("  checkAttackDDOSStatus1   "+flag);
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return flag;
		
	}

	public int getAttackCount(String ip)
	{
		int count=0;
		
		try
		{	
			connection = DBConnector.db_connector();
			stmt = connection.createStatement();
			
			String query1 = "SELECT count from m_hash where ip_address='"+ip+"'" ;
			System.out.println(query1);
			res = stmt.executeQuery(query1);
			
			
			while(res.next())
			{
				count = res.getInt(1);
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return count;
	}

}
