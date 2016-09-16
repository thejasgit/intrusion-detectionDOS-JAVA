/**
 * 
 */
package com.ids.routers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.ids.constants.Constants;
import com.ids.dao.IdsDAO;
import com.ids.daoimplementation.IdsDAOImpl;
import com.ids.routers.listeners.R1_FileContentListener;
import com.ids.routers.listeners.R1_MsgListener;
import com.ids.routers.listeners.R2_IntruderHListener;
import com.ids.routers.listeners.R2_IntruderListener;
import com.ids.routers.listeners.R3_FileContentListener;
import com.ids.routers.listeners.R3_HeaderContentListener;
import com.ids.routers.listeners.R3_IntruderHListener;
import com.ids.routers.listeners.R3_IntruderListener;
import com.ids.routers.listeners.R3_MsgListener;
import com.ids.source.Source;
import com.ids.support.SupportCalculation;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName R3.java
 * @DateofCreation Oct 13, 2012
 * @CreatedTime 4:54:09 PM
 */
public class R3 extends JFrame

{
	public static String type1_route_IP[];
	public static int type1_route_port;
	public Socket nodes;
	public static String profName;
	public static String profNametoNxtHop;
	BufferedOutputStream bos = null;
	byte[] byteArray=new byte[512];
	
	
	private JPanel i_jp;
	private JPanel o_jp;
	
	private JButton i_browse_btn;
	private JButton i_send_btn;
	private JButton i_calc_btn;
	
	private static JTextArea i_content_txtarea;
	private JScrollPane i_sc;
	
	private static JLabel background_img_lbl;
	private static JLabel i_background_img_lbl;
	private static JLabel o_ack_lbl;
	private static JLabel atch_img_lbl; 
	private JLabel title_img_lbl;
	
	private static JLabel file_lbl;
	
	
	
	
	URL background_img_url,title_img_url,i_background_img_url,atch_img_url;
	Image i1,i2,i3,i4;
	Image background_img,title_img,i_background_img,atch_img;
	ImageIcon title_img_icon,background_img_icon,i_background_img_icon,atch_img_icon;

	public R3()throws IOException 
	{
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			
		}
		catch (Exception e)
		{
			System.out.println("Failed to load");
		}
		
		initialze();
		
		Thread t = new Thread(new R3_MsgListener(Constants.R3_SERVER_PORT_NUM)); 
		t.setName("Listener-" + Constants.R3_SERVER_PORT_NUM);
		t.start(); 
		
		Thread t1 = new Thread(new R3_FileContentListener(Constants.FILE_REC_R3_SERVER_PORT_NUM)); 
		t1.setName("Listener-" + Constants.FILE_REC_R3_SERVER_PORT_NUM);
		t1.start();
		
		Thread t2 = new Thread(new R3_HeaderContentListener(Constants.HEADER_REC_R3_SERVER_PORT_NUM)); 
		t2.setName("Listener-" + Constants.HEADER_REC_R3_SERVER_PORT_NUM);
		t2.start();
		
		Thread t3 = new Thread(new R3_IntruderListener(Constants.INTRUDE_REC_R3_SERVER_PORT_NUM)); 
		t3.setName("Listener-" + Constants.INTRUDE_REC_R3_SERVER_PORT_NUM);
		t3.start();
		
		Thread t4 = new Thread(new R3_IntruderHListener(Constants.INTRUDE_H_REC_R3_SERVER_PORT_NUM)); 
		t4.setName("Listener-" + Constants.INTRUDE_H_REC_R3_SERVER_PORT_NUM);
		t4.start();
	}
	
	private void initialze() throws IOException 
	{
		this.setSize(750,585);
		this.setLocation(200,100);
		this.setContentPane(getOuterJPanel());
		this.setTitle("R3");
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
	}
	
	private Container getOuterJPanel() throws IOException 
	{
		if(o_jp==null)
		{
			
			title_img_lbl = new JLabel();
			title_img_url = Source.class.getResource("title.jpg");
			i1 = ImageIO.read(title_img_url); 
			title_img = i1.getScaledInstance(745,90, java.awt.Image.SCALE_SMOOTH);
			title_img_icon = new ImageIcon(title_img);
			title_img_lbl.setBounds(-1,-155,1000,400);
			title_img_lbl.setIcon(title_img_icon);
			title_img_lbl.setVisible(true);
			
			
//			o_ack_lbl = new JLabel();
//			o_ack_lbl.setBounds(160, 455, 500,30);
//			o_ack_lbl.setFont(new java.awt.Font("verdana",java.awt.Font.BOLD,14)); 
//			o_ack_lbl.setVisible(false);
			
			background_img_lbl = new JLabel();
			background_img_url = R1.class.getResource("main_background.png");
			i1 = ImageIO.read(background_img_url);  
			background_img = i1.getScaledInstance(1000,850, java.awt.Image.SCALE_SMOOTH);
			background_img_icon = new ImageIcon(background_img);
			background_img_lbl.setBounds(1,-20,1100,900);
			background_img_lbl.setIcon(background_img_icon);
			background_img_lbl.setVisible(true);
			
			
			o_jp = new JPanel();
			o_jp.setLayout(null);
			o_jp.add(getInnerJPanel());
			o_jp.add(title_img_lbl);
			
			o_jp.add(background_img_lbl);
		
		}
		return o_jp;
	}
	
	
	private Component getInnerJPanel() throws IOException 
	{
		if(i_jp==null) 
		{
			i_background_img_lbl = new JLabel();
			i_background_img_url = R1.class.getResource("backgrnd.png");
			i2 = ImageIO.read(i_background_img_url); 
			i_background_img = i2.getScaledInstance(610,350, java.awt.Image.SCALE_SMOOTH);
			i_background_img_icon = new ImageIcon(i_background_img);
			i_background_img_lbl.setBounds(1,-330,1100,900);
			i_background_img_lbl.setIcon(i_background_img_icon);
			i_background_img_lbl.setVisible(true);
			
			
			atch_img_lbl = new JLabel();
			atch_img_url = Source.class.getResource("attach.png");
			i3 = ImageIO.read(atch_img_url); 
			atch_img = i3.getScaledInstance(32,40, java.awt.Image.SCALE_SMOOTH);
			atch_img_icon = new ImageIcon(atch_img);
			atch_img_lbl.setBounds(300,125,50,50);
			atch_img_lbl.setIcon(atch_img_icon);
			atch_img_lbl.setVisible(false);
			
			
			file_lbl = new JLabel();
			file_lbl.setBounds(340, 145, 200,16);
			file_lbl.setFont(new Font("verdana",java.awt.Font.BOLD,12));
			file_lbl.setVisible(false);
			
			
			
			i_content_txtarea = new JTextArea();
			i_sc = new JScrollPane();
			i_sc.setBounds(65,45,200,220);
			i_content_txtarea.setColumns(20);
			i_content_txtarea.setRows(10);
			i_content_txtarea.setBackground(new Color(89,153,235));
			i_content_txtarea.setForeground(new Color(0,0,0));
			i_content_txtarea.setEditable(false);
			i_sc.setViewportView(i_content_txtarea);
			i_sc.setVisible(true);
			
			i_jp =new JPanel();
			i_jp.setBounds(new java.awt.Rectangle(75,140,600,285));
			i_jp.setBorder(new TitledBorder(""));
			i_jp.setLayout(null);
//			i_jp.add(getCalculateJButton());
//			i_jp.add(getBrowseJButton());
//			i_jp.add(getSendJbutton()); 
			i_jp.add(i_sc);
			i_jp.add(atch_img_lbl);
			i_jp.add(file_lbl);
			i_jp.add(i_background_img_lbl);


		}
		return i_jp;
	}
	

	public static void sendRecData(String rec_data)  
	{
		IdsDAO ids = new IdsDAOImpl(); 
		Socket user;
		String server_IP[];
		int server_port;
		BufferedOutputStream server_bos = null;
		byte server_byteArray[] = new byte[1024];
		
		List values = new ArrayList();
		try 
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e) 
		{
			
			e.printStackTrace();
		}
		
		try
		{
			i_content_txtarea.setText(rec_data);
			
//			ids.updateCurrentLod(profName,Constants.R3);
			values = ids.getCurrLodAndMaxTh(profName,Constants.R3);
			SupportCalculation.checkCriteria(values,profName,Constants.R3);
			
			String updateStatus = ids.getStatus(Constants.R3,profName);
			if(updateStatus.trim().equals(Constants.PROFILE_OPEN))
			{
				ids.updateCurrentLod(profName,Constants.R3);
				i_content_txtarea.setText(rec_data);
				
			}
		
			
			server_IP = ids.getType1IP(Constants.DEST);
			server_port = Integer.parseInt(server_IP[1]); 
			
			
			user = new Socket(server_IP[0], server_port);
			server_bos = new BufferedOutputStream(user.getOutputStream());
			String data1 = i_content_txtarea.getText().trim(); 
			 
			server_byteArray = (data1).getBytes();
			server_bos.write(server_byteArray, 0, server_byteArray.length);
			server_bos.flush();
			server_bos.close();
			user.close();
			
			
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		
	}

	public static void sendRecACk(String fileName) 
	{
		atch_img_lbl.setVisible(true);
		file_lbl.setText(fileName);
		file_lbl.setVisible(true);
		try
		{
			String nxtHopNode[];
			int nxtHopNode_port;
			byte file_byteArray[] = new byte[1024];
			BufferedOutputStream file_bos = null;
			IdsDAO ids = new IdsDAOImpl();
			List values = new ArrayList();
			ids.updateCurrentLod(profName,Constants.R3);
			values = ids.getCurrLodAndMaxTh(profName,Constants.R3);
			
			SupportCalculation.checkCriteria(values,profName,Constants.R3);
			
			Socket dos_client1 = null; 
			nxtHopNode = ids.getType1IP(Constants.FILE_REC_DEST); 
			nxtHopNode_port = Integer.parseInt(nxtHopNode[1]);
		    dos_client1 = new Socket(nxtHopNode[0], nxtHopNode_port);
		    String fl_path = Constants.R3_STORAGE+fileName; 
		    File f=new File(fl_path.trim());  
		    if(f.exists())
		    { 
		    	BufferedInputStream d=new BufferedInputStream(new FileInputStream(fl_path.trim())); 
		    	file_bos = new BufferedOutputStream(dos_client1.getOutputStream());
		    	int read;
		    	while((read = d.read(file_byteArray))!=-1) 
		    	{
		    		file_bos.write(file_byteArray, 0, read);
		    		file_bos.flush();
		    	}
		    	d.close();
		    	dos_client1.close();
		    }
		}
		catch (Exception e)
		{
			System.out.println(e); 
		}
		
	}
	
	

	/**
	 * @Returns void
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		new R3().setVisible(true);

	}

	public static void sendRecHeader(String header) 
	{
		profName = header;
		IdsDAO ids = new IdsDAOImpl();
		int totProfile = 0;
		boolean flag = ids.checkProfileExistence(header,Constants.R3);
		if(!flag)
		{
			ids.insertProfile(header,Constants.R3_LOAD,Constants.MIN_THRESHOLD,Constants.R3,Constants.PROFILE_OPEN); 
			totProfile = ids.getTotalProfiles(Constants.R3);
			int maxThreshold = SupportCalculation.getMaxTh(totProfile,Constants.R3_LOAD); 
			ids.updateMaxThreshold(maxThreshold,Constants.R3);
		}
		 
		
	}

	public static void sendHeaderToNxtHop(String profileName) 
	{
		
		profNametoNxtHop =profileName;
	}

}
