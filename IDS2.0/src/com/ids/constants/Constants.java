/**
 * 
 */
package com.ids.constants;

/**
 * @author Prasoon Kumar Mishra
 *
 */
public interface Constants 
{
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //Type 4
	public static final String JDBC_HOST_URL_WITH_DBNAME = "jdbc:mysql://localhost:3306/ids1_db";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "admin";

//----------------------------------------------------------------------------------------------------------------------	
	
	public static final String SOURCE = "source";
	public static final int SOURCE_SERVER_PORT_NUM = 5001;
	
	public static final String R1 = "r1";
	public static final int R1_SERVER_PORT_NUM = 9002;
	
	public static final String FILE_REC_R1 = "f_rec_r1";
	public static final int FILE_REC_R1_SERVER_PORT_NUM = 9003;
	
	public static final String HEADER_REC_R1 = "h_rec_r1";//header rec r1
	public static final int HEADER_REC_R1_SERVER_PORT_NUM = 9010;
	
	
	public static final String INTRUDE_REC_R1 = "intrud_rec_r1";
	public static final int INTRUDE_REC_R1_SERVER_PORT_NUM = 9013;
	
	public static final String INTRUDE_H_REC_R1 = "intrud_h_rec_r1";
	public static final int INTRUDE_H_REC_R1_SERVER_PORT_NUM = 9016;
	
	
//-----------------------------------------------------------------------------------------------------------------------------	
	

	public static final String R3 = "r3";
	public static final int R3_SERVER_PORT_NUM = 9006;
	
	public static final String FILE_REC_R3 = "f_rec_r3";
	public static final int FILE_REC_R3_SERVER_PORT_NUM = 9007;
	
	public static final String HEADER_REC_R3 = "h_rec_r3";//header rec r3
	public static final int HEADER_REC_R3_SERVER_PORT_NUM = 9012;
	
	public static final String INTRUDE_REC_R3 = "intrud_rec_r3";
	public static final int INTRUDE_REC_R3_SERVER_PORT_NUM = 9015;

	public static final String INTRUDE_H_REC_R3 = "intrud_h_rec_r3";
	public static final int INTRUDE_H_REC_R3_SERVER_PORT_NUM = 9018;
	
	
//-----------------------------------------------------------------------------------------------------------------------------	
	
	
	public static final String R2 = "r2";
	public static final int R2_SERVER_PORT_NUM = 9004;
	
	public static final String FILE_REC_R2 = "f_rec_r2";
	public static final int FILE_REC_R2_SERVER_PORT_NUM = 9005; 
	
	public static final String HEADER_REC_R2 = "h_rec_r2";//header rec r2 
	public static final int HEADER_REC_R2_SERVER_PORT_NUM = 9011;
	
	public static final String INTRUDE_REC_R2 = "intrud_rec_r2";
	public static final int INTRUDE_REC_R2_SERVER_PORT_NUM = 9014;
	
	public static final String INTRUDE_H_REC_R2 = "intrud_h_rec_r2";
	public static final int INTRUDE_H_REC_R2_SERVER_PORT_NUM = 9017;
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------
	
	
//-----------------------------------------------------------------------------------------------------------------------------	
	
	
	public static final String DEST = "dest";
	public static final int DEST_SERVER_PORT_NUM = 9008;
	
	public static final String FILE_REC_DEST = "f_rec_dest";
	public static final int FILE_REC_DEST_SERVER_PORT_NUM = 9009; 
	
	
//-----------------------------------------------------------------------------------------------------------------------
	
	public static final String R1_STORAGE = "R1_Files/";
	public static final String R2_STORAGE = "R2_Files/";
	public static final String R3_STORAGE = "R3_Files/";
	
	public static final String DEST_STORAGE = "Dest_Files/";
	
	
	
//------------------------------------------------------------------------------------------------------------------------	

	
//-----------------------------------------------------------------------------------------------------------------------
	
	public static final String PROFILE_NAME_1 = "S1";//src
	public static final String PROFILE_NAME_2 = "S2";//r1
	public static final String PROFILE_NAME_3 = "S3";//r2
	public static final String PROFILE_NAME_4 = "S4A";// Ist attacker
	public static final String PROFILE_NAME_5 = "S5A";//2nd attacker
	
	
	public static final String PROFILE_OPEN = "Open";//profile staus
	public static final String PROFILE_CLOSE = "Close";//profile staus
	
	public static final String GARBAGE = "hgafjsdghfvgbdjfsakghdjlsfhj";
	public static final String GARBAGE1 = "hihello67hjkjk";
	public static final String R_Status = "true";
	
	
//------------------------------------------------------------------------------------------------------------------------	

//------------------------------------------------------------------------------------------------------------------------	
	
	public static final int R1_LOAD = 25;
	public static final int R2_LOAD = 15;
	public static final int R3_LOAD = 15;
	public static final int MIN_THRESHOLD = 1;
	
	
//-----------------------------------------------------------------------------------------------------------------------

	
	
}
