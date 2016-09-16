/**
 * 
 */
package com.ids.support;

import java.util.List;

import com.ids.constants.Constants;
import com.ids.dao.IdsDAO;
import com.ids.daoimplementation.IdsDAOImpl;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName SupportCalculation.java
 * @DateofCreation Oct 16, 2012
 * @CreatedTime 4:07:41 PM
 */
public class SupportCalculation 
{
	
	public static int getMaxTh(int totProfile, int load) 
	{
		int th = load/totProfile; 
		System.out.println(" Load................"+load);
		System.out.println(" totProfile................."+totProfile);
		System.out.println(" threshold................."+th);
		return th;
	}

	public static void checkCriteria(List values, String profName, String rName) 
	{
		IdsDAO ids = new IdsDAOImpl();
		int curr_lod = Integer.parseInt(values.get(0).toString());
		int max_th = Integer.parseInt(values.get(1).toString());
		
		System.out.println(" curr_lod................"+curr_lod);

		System.out.println(" max_th................"+max_th);
		if(curr_lod > max_th)
		{
			
			ids.updateProfileStatus(profName,rName);
		}
		
		
		if(curr_lod==max_th)
		{
			
			ids.updateProfileStatus(profName,rName);
		}
		
		
		IdsDAOImpl impl=new IdsDAOImpl();
		
		int count=impl.getAttackCount(profName);
		
		if(count>=3)
		{
			ids.updateProfileStatus(profName,rName);
		
		}
		
	
	}



}
