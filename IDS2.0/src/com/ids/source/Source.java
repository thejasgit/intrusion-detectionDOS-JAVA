/**
 * 
 */
package com.ids.source;

import java.awt.Color;



import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;



import com.ids.constants.Constants;
import com.ids.dao.IdsDAO;
import com.ids.daoimplementation.IdsDAOImpl;
import com.ids.source.listener.Source_Listener;


/**
 * @author Prasoon Kumar Mishra
 *
 */
public class Source extends JFrame
{
	public static String type1_route_IP[];
	public static int type1_route_port;
	public static String profName;
	public static String nxtHopNode[];
	public static int nxtHopNode_port;
	String nxtHopMsgNodeName;
	String nxtHopFileNodeName;
	
	public File curFile;
	
	public Socket nodes;
	
	BufferedOutputStream bos = null;
	BufferedOutputStream file_bos = null;
	
	byte file_byteArray[] = new byte[1024];
	byte[] byteArray=new byte[512];
	
	
	private JPanel i_jp;
	private JPanel o_jp;
	
	private JButton i_browse_btn;
	private JButton i_send_btn;
	private JButton i_calc_btn;
	
	private JTextArea i_content_txtarea;
	private JScrollPane i_sc;
	
	private static JLabel background_img_lbl;
	private static JLabel i_background_img_lbl;
	private static JLabel o_ack_lbl;
	private static JLabel atch_img_lbl; 
	private JLabel title_img_lbl;
	
	private JLabel file_lbl;
	
	
	URL background_img_url,title_img_url,i_background_img_url,atch_img_url;
	Image i1,i2,i3,i4;
	Image background_img,title_img,i_background_img,atch_img;
	ImageIcon title_img_icon,background_img_icon,i_background_img_icon,atch_img_icon;
	
	
	public Source() throws IOException
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
		
//		Thread t = new Thread(new Source_Listener(Constants.SOURCE_SERVER_PORT_NUM)); 
//		t.setName("Listener-" + Constants.SOURCE_SERVER_PORT_NUM);
//		t.start();
		
	}

	private void initialze() throws IOException 
	{
		this.setSize(750,585);
		this.setLocation(200,100);
		this.setContentPane(getOuterJPanel());
		this.setTitle("Source");
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
			title_img = i1.getScaledInstance(750,90, java.awt.Image.SCALE_SMOOTH);
			title_img_icon = new ImageIcon(title_img);
			title_img_lbl.setBounds(3,-155,1000,400);
			title_img_lbl.setIcon(title_img_icon);
			title_img_lbl.setVisible(true);
			
			
			o_ack_lbl = new JLabel();
			o_ack_lbl.setBounds(160, 455, 500,30);
			o_ack_lbl.setFont(new java.awt.Font("verdana",java.awt.Font.BOLD,14)); 
//			o_ack_lbl.setText("Data Receive Ack from : Node1");
			o_ack_lbl.setVisible(false);
			
			
			
			background_img_lbl = new JLabel();
			background_img_url = Source.class.getResource("main_background.png");
			i1 = ImageIO.read(background_img_url);  
			background_img = i1.getScaledInstance(1000,850, java.awt.Image.SCALE_SMOOTH);
			background_img_icon = new ImageIcon(background_img);
			background_img_lbl.setBounds(1,-20,1100,900);
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
			atch_img_lbl.setBounds(140,10,600,250);
			atch_img_lbl.setIcon(atch_img_icon);
			atch_img_lbl.setVisible(false);
			
			
			file_lbl = new JLabel();
			file_lbl.setBounds(178, 125, 200,16);
			file_lbl.setFont(new Font("verdana",java.awt.Font.BOLD,12));
			
			file_lbl.setVisible(true);
			
			
			
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
			i_jp.setBounds(new java.awt.Rectangle(75,185,600,285));
			i_jp.setBorder(new TitledBorder(""));
			i_jp.setLayout(null);
			i_jp.add(getTextJButton());
			i_jp.add(getBrowseJButton());
			i_jp.add(getSendJbutton()); 
			i_jp.add(i_sc);
			i_jp.add(atch_img_lbl);
			i_jp.add(file_lbl);
			i_jp.add(i_background_img_lbl);


		}
		return i_jp;
	}
	
	private Component getTextJButton() 
	{
		i_calc_btn = new JButton();
		i_calc_btn.setBounds(35,150,85,30);
		i_calc_btn.setFont(new java.awt.Font("verdana",java.awt.Font.PLAIN,9)); 
		i_calc_btn.setText("Text");
		
		i_calc_btn.addActionListener(new ActionListener()
		{
			IdsDAO tarf = new IdsDAOImpl();
			
			
			public void actionPerformed(ActionEvent e)
			{
				i_content_txtarea.setEditable(true);
				i_browse_btn.setEnabled(false);
			}
		}
		);
		return i_calc_btn;
	}

	private Component getBrowseJButton() 
	{
		i_browse_btn = new JButton();
		i_browse_btn.setBounds(35,85,85,30);
		i_browse_btn.setFont(new java.awt.Font("verdana",java.awt.Font.PLAIN,9)); 
		i_browse_btn.setText("Browse");
		
		i_browse_btn.setEnabled(true);
		i_browse_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser();
			    try 
			    {
			    	File f = new File(new File("filename.txt").getCanonicalPath());
			        chooser.setSelectedFile(f);
			    }
			    catch (IOException e1) 
			    {
			    	
			    }
			    int retval = chooser.showOpenDialog(i_browse_btn);
			    if (retval == JFileChooser.APPROVE_OPTION)
			    {
                  File field = chooser.getSelectedFile();
                }		    
			    curFile = chooser.getSelectedFile();
			    file_lbl.setText(curFile.getName().toString());
			    atch_img_lbl.setVisible(true);
			    try
			    {
			    	FileInputStream fstream = new FileInputStream(curFile);
			    	DataInputStream ins = new DataInputStream(fstream);
			    	BufferedReader br = new BufferedReader(new InputStreamReader(ins));
			    	StringBuffer buffer = new StringBuffer();
			    	    
			    }
			    catch (Exception e1)
			    {
			    	System.err.println("Error: " + e1.getMessage());
			    }
			}
		}
		);
		
		return i_browse_btn;
	}

	private Component getSendJbutton() 
	{
		i_send_btn = new JButton();
		i_send_btn.setBounds(new java.awt.Rectangle(200,230,70,35));
		i_send_btn.setFont(new java.awt.Font("verdana",java.awt.Font.PLAIN,10)); 
		i_send_btn.setText("Send");
		
		i_send_btn.setEnabled(true);
		i_send_btn.addActionListener(new ActionListener()
		{
			IdsDAO ids = new IdsDAOImpl();
			Random r = new Random();
			 
			public void actionPerformed(ActionEvent e)
			{ 
				try
		    	{
					int r1 = (Math.abs(r.nextInt()) % 20) + 1;
					int r2 = (Math.abs(r.nextInt()) % 20) + 1;
//					int r2=1;
					
					System.out.println("  r1  "+r1+"    r2   "+r2+" It will take any path depends on r1 and r2 values");
					
					if(r1>r2)
					{
						nxtHopMsgNodeName = Constants.R1;
						nxtHopFileNodeName = Constants.FILE_REC_R1;
						sendHeaderToNxtHopNode(Constants.PROFILE_NAME_1,Constants.R1);
						profName=Constants.PROFILE_NAME_1;
						Thread.sleep(2000);
					}
					else
					{
						nxtHopMsgNodeName = Constants.R2;
						nxtHopFileNodeName = Constants.FILE_REC_R2; 
						sendHeaderToNxtHopNode(Constants.PROFILE_NAME_1,Constants.R2);
						profName=Constants.PROFILE_NAME_1;
						Thread.sleep(2000);
					}
					
					String status = ids.getStatus(nxtHopMsgNodeName,profName);
					
					System.out.println("--------- " +status); 
					
					if(status.equals(Constants.PROFILE_OPEN))
					{
						if(i_content_txtarea.getText().equals("")) 
						{
							ids.deletePreviousFname();
							ids.storeFileName(curFile.getName());
							
							Socket dos_client1 = null; 
				    		nxtHopNode = ids.getType1IP(nxtHopFileNodeName); 
				    		nxtHopNode_port = Integer.parseInt(nxtHopNode[1]);
						    dos_client1 = new Socket(nxtHopNode[0], nxtHopNode_port);
						    String fl_path = curFile.toString().replace("\\","/");
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
						else
						{
							sendMsgToNxtHop(nxtHopMsgNodeName);
						}
			
					}
					else
					{
						JOptionPane.showMessageDialog(null,"closed");
					}
					
					
		    		
			}
				
				catch (Exception e1) 
		    	{ 
					System.out.println("--------- "+e1);
					JOptionPane.showMessageDialog(null,"Problem occured while Data Transfer to Next Hop ", "File Transfer Status",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		);
	
		return i_send_btn;
	}

	protected void sendHeaderToNxtHopNode(String profileName1, String nxtHop)  
	{
		
		IdsDAO ids = new IdsDAOImpl(); 
		Socket user;
		String router;
		String server_IP[];
		int server_port;
		BufferedOutputStream server_bos = null;
		byte server_byteArray[] = new byte[1024];
		
		if(nxtHop.equals(Constants.R1))
		{
			router = Constants.HEADER_REC_R1;
		}
		else
		{
			router = Constants.HEADER_REC_R2;
		}
		
		server_IP = ids.getType1IP(router); 
		server_port = Integer.parseInt(server_IP[1]); 
		try
		{
			user = new Socket(server_IP[0], server_port);
			server_bos = new BufferedOutputStream(user.getOutputStream());
			String data1 = profileName1.trim(); 
			 
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

	protected void sendMsgToNxtHop(String nodeName) 
	{
		IdsDAO ids = new IdsDAOImpl(); 
		Socket user;
		String server_IP[];
		int server_port;
		BufferedOutputStream server_bos = null;
		byte server_byteArray[] = new byte[1024];
		
		server_IP = ids.getType1IP(nodeName);
		server_port = Integer.parseInt(server_IP[1]); 
		try
		{
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

	public static void getReceivedData(String ack) 
	{
		IdsDAO tarf = new IdsDAOImpl();
		
		o_ack_lbl.setVisible(true);
		
		
		
	}
	
	public static void main (String args[]) throws IOException
	{
		new Source().setVisible(true);
	}

}
