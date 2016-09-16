/**
 * 
 */
package com.ids.intruders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.ids.constants.Constants;
import com.ids.dao.IdsDAO;
import com.ids.daoimplementation.IdsDAOImpl;
import com.ids.source.Source;
import com.ids.source.listener.Source_Listener;
import com.ids.support.Utility;



/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName Intruder_1.java
 * @DateofCreation Oct 18, 2012
 * @CreatedTime 11:07:54 AM
 */
public class Intruder_1 extends JFrame
{
	javax.swing.Timer initial_timer = new javax.swing.Timer(2000, new ClockListener());
	
	public static String type1_route_IP[];
	public static int type1_route_port;
	
	public static String attack;
	public static String r_name;
	public static String attack_h;
	
	private JPanel i_jp;
	private JPanel o_jp;
	
	private JButton i_intrude_btn;
	private JButton i_send_btn;
	private JButton i_stop_btn;
	
	private JTextArea i_content_txtarea;
	private JScrollPane i_sc;
	
	private static JLabel background_img_lbl;
	private static JLabel i_background_img_lbl;
	private static JLabel o_ack_lbl;
	private static JLabel atch_img_lbl; 
	private JLabel title_img_lbl;
	
	URL background_img_url,title_img_url,i_background_img_url,atch_img_url;
	Image i1,i2,i3,i4;
	Image background_img,title_img,i_background_img,atch_img;
	ImageIcon title_img_icon,background_img_icon,i_background_img_icon,atch_img_icon;
	
	
	public Intruder_1() throws IOException
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
		
		/*Thread t = new Thread(new Source_Listener(Constants.SOURCE_SERVER_PORT_NUM)); 
		t.setName("Listener-" + Constants.SOURCE_SERVER_PORT_NUM);
		t.start();*/
		
	}
	
	private void initialze() throws IOException 
	{
		this.setSize(600,400);
		this.setLocation(200,100);
		this.setContentPane(getOuterJPanel());
		this.setTitle("Intruder 1");
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
			title_img = i1.getScaledInstance(600,70, java.awt.Image.SCALE_SMOOTH);
			title_img_icon = new ImageIcon(title_img);
			title_img_lbl.setBounds(-1,-155,1000,400);
			title_img_lbl.setIcon(title_img_icon);
			title_img_lbl.setVisible(true);
			
			
			o_ack_lbl = new JLabel();
			o_ack_lbl.setBounds(160, 455, 500,30);
			o_ack_lbl.setFont(new java.awt.Font("verdana",java.awt.Font.BOLD,14)); 
//			o_ack_lbl.setText("Data Receive Ack from : Node1");
			o_ack_lbl.setVisible(false);
			
			
			
			background_img_lbl = new JLabel();
			background_img_url = Intruder_1.class.getResource("main_background.png");
			i1 = ImageIO.read(background_img_url);  
			background_img = i1.getScaledInstance(900,850, java.awt.Image.SCALE_SMOOTH);
			background_img_icon = new ImageIcon(background_img);
			background_img_lbl.setBounds(1,-20,950,900);
			background_img_lbl.setIcon(background_img_icon);
			background_img_lbl.setVisible(true);
			
			
			o_jp = new JPanel();
			o_jp.setLayout(null);
			o_jp.add(getInnerJPanel());
			o_jp.add(o_ack_lbl);
		
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
			i_background_img_url = Intruder_1.class.getResource("backgrnd.png");
			i2 = ImageIO.read(i_background_img_url); 
			i_background_img = i2.getScaledInstance(400,350, java.awt.Image.SCALE_SMOOTH);
			i_background_img_icon = new ImageIcon(i_background_img);
			i_background_img_lbl.setBounds(-55,-100,450,400);
			i_background_img_lbl.setIcon(i_background_img_icon);
			i_background_img_lbl.setVisible(true);
			
			
			atch_img_lbl = new JLabel();
			atch_img_url = Source.class.getResource("attach.png");
			i3 = ImageIO.read(atch_img_url); 
			atch_img = i3.getScaledInstance(32,40, java.awt.Image.SCALE_SMOOTH);
			atch_img_icon = new ImageIcon(atch_img);
			atch_img_lbl.setBounds(140,10,600,250);
			atch_img_lbl.setIcon(atch_img_icon);
			atch_img_lbl.setVisible(false);
			
			
			
			i_content_txtarea = new JTextArea();
			i_sc = new JScrollPane();
			i_sc.setBounds(350,45,200,220);
			i_content_txtarea.setColumns(20);
			i_content_txtarea.setRows(10);
			i_content_txtarea.setBackground(new Color(89,153,235));
			i_content_txtarea.setForeground(new Color(0,0,0));
			i_content_txtarea.setEditable(false);
			i_sc.setViewportView(i_content_txtarea);
			i_sc.setVisible(true);
			
			i_jp =new JPanel();
			i_jp.setBounds(new java.awt.Rectangle(150,140,300,200));
			i_jp.setBorder(new TitledBorder(""));
			i_jp.setLayout(null);
			i_jp.add(getIntrudeJButton());
			i_jp.add(getStopJButton());
//			i_jp.add(getSendJbutton()); 
			i_jp.add(i_sc);
			i_jp.add(atch_img_lbl);
			i_jp.add(i_background_img_lbl);


		}
		return i_jp;
	}
	
	private Component getIntrudeJButton() 
	{
		i_intrude_btn = new JButton();
		i_intrude_btn.setBounds(35,150,85,30);
		i_intrude_btn.setFont(new java.awt.Font("verdana",java.awt.Font.PLAIN,9)); 
		i_intrude_btn.setText("Intrude");
		
		i_intrude_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				initial_timer.start();
				try 
	    		{
					IdsDAO ids = new IdsDAOImpl(); 
					String rName = ids.getRname2Attack();
					if(rName.trim().equals(Constants.R1))
					{
						System.out.println("r1");
						attack = Constants.INTRUDE_REC_R1;
						attack_h = Constants.INTRUDE_H_REC_R1; 
						r_name = Constants.R1;
					}
					else if(rName.trim().equals(Constants.R2))
					{
						System.out.println("r2");
						attack = Constants.INTRUDE_REC_R2;
						attack_h = Constants.INTRUDE_H_REC_R2; 
						r_name = Constants.R2;
					}
					
					else if(rName.trim().equals(Constants.R3))
					{
						System.out.println("r3");
						attack = Constants.INTRUDE_REC_R3;
						attack_h = Constants.INTRUDE_H_REC_R3; 
						r_name = Constants.R3;
					}
					else
					{
						System.out.println("hi");
					}
					
					
	    			sendHeaderInfo(Constants.PROFILE_NAME_4,attack_h);  
	    			Thread.sleep(500);
	    			
	    			
	    			Socket user;
	    			String server_IP[];
	    			int server_port;
	    			BufferedOutputStream server_bos = null;
	    			byte server_byteArray[] = new byte[1024];
	    			
	    			server_IP = ids.getType1IP(attack);
	    			server_port = Integer.parseInt(server_IP[1]); 
	    			try
	    			{
	    				String status = ids.getStatus(r_name,Constants.PROFILE_NAME_4);
	    				System.out.println("-------------------------- "+status); 
	    				if(status.trim().equals(Constants.PROFILE_OPEN))
	    				{
	    					String hmac=Utility.MD1(Constants.GARBAGE);
	    					String hmac1=Utility.MD1(Constants.GARBAGE1);
	    					
	    					Random r=new Random();
	    					
	    					int msg=r.nextInt(2)+1;
	    					
	    					int count = 0;
	    					InetAddress ipaddress=InetAddress.getLocalHost();
	    					String ipaddress1=ipaddress.getHostAddress();
	    					boolean flag=ids.checkAttackDDOSStatus1(server_IP[0]);
	    					System.out.println("checkAttackDDOSStatus1 "+flag);
	    					if(flag)
	    					{
	    					if(msg==1)
	    					{
	    						
	    						ids.setAttackDDOSStatus2(Constants.PROFILE_NAME_4, hmac, count,ipaddress1);
	    						System.out.println("  Update status    "+true);
	    					}
	    					}
	    					else
	    					{
	    					
	    					ids.setAttackDDOSStatus1(Constants.PROFILE_NAME_4,hmac1,count,ipaddress1);
	    					}
	    					
	    					user = new Socket(server_IP[0], server_port);
	        				server_bos = new BufferedOutputStream(user.getOutputStream());
	        				String data1 = Constants.GARBAGE.trim(); 
	        				 
	        				server_byteArray = (data1).getBytes();
	        				server_bos.write(server_byteArray, 0, server_byteArray.length);
	        				server_bos.flush();
	        				server_bos.close();
	        				user.close();
//	        				initial_timer.restart();
	    				}
	    				else
	    				{
	    					JOptionPane.showMessageDialog(null,"closed");
	    				}
	    				
	    			}
	    			catch(Exception e1)
	    			{
	    				e1.printStackTrace();
	    			}
	    		} 
	    		catch (Exception e1) 
				{
					
					e1.printStackTrace();
				}
				
				
			}
		}
		);
		return i_intrude_btn;
	}
	
	private Component getStopJButton() 
	{
		i_stop_btn = new JButton();
		i_stop_btn.setBounds(150,150,85,30);
		i_stop_btn.setFont(new java.awt.Font("verdana",java.awt.Font.PLAIN,9)); 
		i_stop_btn.setText("Stop");
		
		i_stop_btn.addActionListener(new ActionListener()
		{
			IdsDAO tarf = new IdsDAOImpl();
			
			
			public void actionPerformed(ActionEvent e)
			{
				
			}
		}
		);
		return i_stop_btn;
	}

	class ClockListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		     
    		try 
    		{
    			
    			sendHeaderInfo(Constants.PROFILE_NAME_4,attack_h);
    			Thread.sleep(500);
    			
    			IdsDAO ids = new IdsDAOImpl(); 
    			Socket user;
    			String server_IP[];
    			int server_port;
    			BufferedOutputStream server_bos = null;
    			byte server_byteArray[] = new byte[1024];
    			
    			server_IP = ids.getType1IP(attack);
    			server_port = Integer.parseInt(server_IP[1]); 
    			try
    			{
    				String status = ids.getStatus(r_name,Constants.PROFILE_NAME_4);
    				System.out.println("-------------------------- "+status); 
    				if(status.trim().equals(Constants.PROFILE_OPEN))
    				{
    					user = new Socket(server_IP[0], server_port);
        				server_bos = new BufferedOutputStream(user.getOutputStream());
        				String data1 = Constants.GARBAGE.trim(); 
        				 
        				server_byteArray = (data1).getBytes();
        				server_bos.write(server_byteArray, 0, server_byteArray.length);
        				server_bos.flush();
        				server_bos.close();
        				user.close();
        				initial_timer.restart();
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(null,"closed");
    				}
    				
    			    			}
    			catch(Exception e1)
    			{
    				e1.printStackTrace();
    			}
    		} 
    		catch (Exception e1) 
			{
				
				e1.printStackTrace();
			}
           
            
    	}

    }

	public static void main(String[] args) throws IOException
	{
		new Intruder_1().setVisible(true);

	}

	public void sendHeaderInfo(String profileName, String nxtHop) 
	{
		
		IdsDAO ids = new IdsDAOImpl(); 
		Socket user;
		String server_IP[];
		int server_port;
		BufferedOutputStream server_bos = null;
		byte server_byteArray[] = new byte[1024];
		
		server_IP = ids.getType1IP(nxtHop);
		server_port = Integer.parseInt(server_IP[1]);  
		try
		{
			System.out.println(" server_IP[1]   "+server_IP[1]);
			user = new Socket(server_IP[0], server_port);
			server_bos = new BufferedOutputStream(user.getOutputStream());
			String data1 = profileName; 
			 
			server_byteArray = (data1).getBytes();
			server_bos.write(server_byteArray, 0, server_byteArray.length);
			server_bos.flush();
			server_bos.close();
			user.close();
			
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}

}
