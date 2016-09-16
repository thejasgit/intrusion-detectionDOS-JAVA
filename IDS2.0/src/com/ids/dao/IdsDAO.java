/**
 * 
 */
package com.ids.dao;

import java.util.List;

/**
 * @author Prasoon Kumar Mishra
 *
 */
public interface IdsDAO 
{
	String[] getType1IP(String v_type1Node1);

	void storeFileName(String fname);

	void deletePreviousFname();

	String getFileName();

	void updateCurrentLod(String profileName, String rName);
 
	boolean checkProfileExistence(String header, String rName);

	void insertProfile(String header, int load, int minThreshold, String rName, String profileStatus);

	int getTotalProfiles(String rNmae); 

	void updateMaxThreshold(int maxThreshold,String rName);

	List getCurrLodAndMaxTh(String profName, String rName);

	void updateProfileStatus(String profName, String rName);

	String getStatus(String rName, String profName);

	String getRname2Attack();

	void setAttackDDOSStatus(String rName);               
	boolean setAttackDDOSStatus1(String ip,String hmac,int count,String ip_attacker);
	boolean checkAttackDDOSStatus1(String ip);
	boolean setAttackDDOSStatus2(String ip,String hmac,int count,String ip_attacker);
	int getAttackCount(String ip);
}
