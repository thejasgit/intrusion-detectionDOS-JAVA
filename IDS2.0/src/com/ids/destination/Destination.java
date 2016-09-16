/**
 * 
 */
package com.ids.destination;

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
import com.ids.destination.listener.Dest_FileContentListener;
import com.ids.destination.listener.Dest_MsgListener;
import com.ids.routers.R1;
import com.ids.routers.listeners.R1_FileContentListener;
import com.ids.routers.listeners.R1_MsgListener;
import com.ids.source.Source;

/**
 * @author Prasoon Kumar Mishra
 * @ProjectName IDS
 * @FileName Destination.java
 * @DateofCreation Oct 15, 2012
 * @CreatedTime 3:27:52 PM
 */
public class Destination extends JFrame
{
	
	public static String type1_route_IP[];
	public static int type1_route_port;
	public Socket nodes;
	
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
	
	
	public Destination() throws IOException
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
		
		Thread t = new Thread(new Dest_MsgListener(Constants.DEST_SERVER_PORT_NUM)); 
		t.setName("Listener-" + Constants.DEST_SERVER_PORT_NUM);
		t.start(); 
		
		Thread t1 = new Thread(new Dest_FileContentListener(Constants.FILE_REC_DEST_SERVER_PORT_NUM)); 
		t1.setName("Listener-" + Constants.FILE_REC_DEST_SERVER_PORT_NUM);
		t1.start();
		
	}
	
	private void initialze() throws IOException 
	{
		this.setSize(750,585);
		this.setLocation(200,100);
		this.setContentPane(getOuterJPanel());
		this.setTitle("Destination");
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
			background_img_url = Destination.class.getResource("main_background.png");
			i1 = ImageIO.read(background_img_url);  
			background_img = i1.getScaledInstance(750,800, java.awt.Image.SCALE_SMOOTH);
			background_img_icon = new ImageIcon(background_img);
			background_img_lbl.setBounds(1,-50,1100,900);
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
			i_background_img_url = Source.class.getResource("backgrnd.png");
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
			atch_img_lbl.setBounds(190,175,50,50);
			atch_img_lbl.setIcon(atch_img_icon);
			atch_img_lbl.setVisible(false);
			
			
			file_lbl = new JLabel();
			file_lbl.setBounds(225, 195, 200,16);
			file_lbl.setFont(new Font("verdana",java.awt.Font.BOLD,12));
			file_lbl.setVisible(false);
//			file_lbl.setText("12");
			
			
			
			i_content_txtarea = new JTextArea();
			i_sc = new JScrollPane();
			i_sc.setBounds(125,45,330,100);
			i_content_txtarea.setColumns(20);
			i_content_txtarea.setRows(10);
			i_content_txtarea.setBackground(new Color(89,153,235));
			i_content_txtarea.setForeground(new Color(0,0,0));
			i_content_txtarea.setEditable(false);
			i_sc.setViewportView(i_content_txtarea);
			i_sc.setVisible(true);
			
			i_jp =new JPanel();
			i_jp.setBounds(new java.awt.Rectangle(75,170,600,285));
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
		
		
		try
		{
			i_content_txtarea.setText(rec_data);
			
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
		
	}
	
	public static void main(String[] args) throws IOException 
	{
		new Destination().setVisible(true);

	}

}
