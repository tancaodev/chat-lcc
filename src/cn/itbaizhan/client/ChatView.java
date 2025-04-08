package cn.itbaizhan.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cn.itbaizhan.common.*;

public class ChatView extends JFrame implements Runnable, ActionListener, KeyListener {
	JPanel jPfriend = new JPanel();
	JLabel jLfriendPortrait = new JLabel(new ImageIcon("src/file/personelView1.jpg"));
	JLabel jLfriendName = new JLabel("Always Love You ");
	JLabel jLfriendSign = new JLabel("The best time in life is when you meet someone in a strange place, feeling a kind of inexplicable emotion");
	JLabel image1 = new JLabel(new ImageIcon("src/file/CVimage1.jpg")); // 26*30
	JLabel image2 = new JLabel(new ImageIcon("src/file/CVimage2.jpg")); // 28*28
	JLabel image3 = new JLabel(new ImageIcon("src/file/CVimage3.jpg")); // 28*28
	JLabel image4 = new JLabel(new ImageIcon("src/file/CVimage4.jpg")); // 30*32
	JLabel image5 = new JLabel(new ImageIcon("src/file/CVimage5.jpg")); // 32*29
	JLabel image6 = new JLabel(new ImageIcon("src/file/CVimage6.jpg")); // 31*32
	
	/*------------ Display friend's basic information ------------------------------*/
	JLabel point = new JLabel(new ImageIcon("src/file/CVpoint.jpg")); // 17*19
	JLabel jLpoint = new JLabel(" Chat with friends, send files, receive messages, answer calls from strangers, use these features");
	JTextArea jTAshowChat = new JTextArea();
	JScrollPane jSshowChat = new JScrollPane(jTAshowChat);
	/*------------------------------------------------------*/
	
	/*------------- Middle toolbar ------------------------------*/
	JPanel jPinfo = new JPanel();
	JButton jBshowinfo = new JButton(new ImageIcon("src/file/CVshowInfo.jpg")); // 81*22
	JLabel jLinfo = new JLabel(new ImageIcon("src/file/CVInfo.jpg")); // 302*24
	JLabel jLinfo1 = new JLabel(new ImageIcon("src/file/CVinfo1.jpg")); // 40*25
	/*------------------------------------------------------*/
	
	JTextArea jTAshowsend = new JTextArea();
	JScrollPane jSshowsend = new JScrollPane(jTAshowsend);
	
	/*------------- Side QQ avatar ------------------------------*/
	JPanel jPqq = new JPanel();
	JLabel friend = new JLabel(new ImageIcon("src/file/friend.jpg")); // 140*224
	JLabel me = new JLabel(new ImageIcon("src/file/me.jpg")); // 140*161
	
	/*------------ Bottom buttons ---------------------------------*/
	ImageIcon close = new ImageIcon("src/file/CVclose.jpg");
	ImageIcon send = new ImageIcon("src/file/CVsend.jpg");
	JButton jBclose = new JButton(close); // Close button 69*23
	JButton jBsend = new JButton(send); // Send message button 80*23
	
	private UserBean myInfo = null;
	private UserBean currentFriend = null;
	JFrame owner = null;
	int usePort = 0; // Port for sending/receiving data
	private String friendIp = null; // Friend's IP address
	private int friendPort = 0; // Friend's port number
	public static final int BUFFER_SIZE = 5120; // Buffer size for sending data
	private byte outBuf[] = null; // Buffer for sending data
	private DatagramSocket sendSocket; // Socket for sending messages
	private DatagramPacket sendPacket; // Packet for sending messages
	private DatagramSocket receiveSocket; // Socket for receiving messages
	private DatagramPacket receivePacket; // Packet for receiving messages
	Hashtable friendInfoTable; // Hash table for storing friend information
	String line_separator = System.getProperty("line.separator"); // Get system line separator
	
	/*-------------------- File transfer related variables ---------------------------------------------*/
	JFileChooser fc = new JFileChooser();
	String str; // File content
	String title = null;
	 BufferedReader in; // Input stream
	 PrintStream out; // Output stream
	
	/*--------------------- Chat record related ---------------------------------------------------------*/
	 BufferedWriter bw; // Writer for chat records
	 BufferedReader bufr; // Reader for chat records
	 String path = null;

	public ChatView(UserBean myInfo, UserBean currentFriend,
			JFrame owner, int usePort,
			DatagramSocket receiveSocket, DatagramPacket receivePacket,
			Hashtable friendInfoTable, BufferedReader in, PrintStream out) {
		this.myInfo = myInfo;
		this.currentFriend = currentFriend;
		this.owner = owner;
		this.usePort = usePort;
		this.receiveSocket = receiveSocket;
		this.receivePacket = receivePacket;
		this.friendInfoTable = friendInfoTable;
		this.in = in;
		this.out = out;
		friendIp = currentFriend.getIp();
		friendPort = currentFriend.getPort();
		System.out.println("Friend IP: " + friendIp);
		friendIp = friendIp.substring(friendIp.indexOf("/") + 1);
		System.out.println("Extracted friend IP: " + friendIp);
		System.out.println("Friend port: " + friendPort);
		System.out.println("My port number: " + usePort);
		init();
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.add(jPfriend);
		this.add(jPqq);
		this.add(point);
		this.add(jLpoint);
		this.add(jSshowChat);
		this.add(jPinfo);
		this.add(jBshowinfo);
		this.add(jSshowsend);
		this.add(jBclose);
		this.add(jBsend);
		this.setSize(420, 545);
		this.setVisible(true);
		try {
			// Create socket for sending messages
			sendSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Socket exception: " + e.getMessage());
		}
		
		// Create chat record file
		try {
			path = "src/Chat Records/" + myInfo.getUserNum() + "-" + currentFriend.getUserNum() + ".txt";
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(path, true)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
		}
	}

	public void init() {
		// Set friend panel
		jPfriend.setBounds(0, 0, 540, 82);
		jPfriend.setLayout(null);
		jPfriend.setBackground(Color.PINK);
		jLfriendPortrait.setIcon(new ImageIcon(currentFriend.getPortrait()));
		jLfriendPortrait.setBounds(5, 5, 60, 60);
		jPfriend.add(jLfriendPortrait);
		jLfriendName.setText(currentFriend.getUserName());
		jLfriendName.setFont(new Font("SimSun", Font.BOLD, 15));
		jLfriendName.setForeground(Color.WHITE);
		jLfriendName.setBounds(70, 4, 200, 20);
		jPfriend.add(jLfriendName);
		jLfriendSign.setText(currentFriend.getSign());
		jLfriendSign.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLfriendSign.setForeground(Color.WHITE);
		jLfriendSign.setBounds(75, 26, 400, 20);
		jPfriend.add(jLfriendSign);
		
		// Set toolbar buttons
		image1.setBounds(70, 48, 26, 30);
		image1.setBackground(Color.PINK);
		jPfriend.add(image1);
		image2.setBounds(111, 48, 28, 28);
		image2.setBackground(Color.PINK);
		jPfriend.add(image2);
		image3.setBounds(154, 48, 28, 28);
		image3.setBackground(Color.PINK);
		image3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String temp = null;
				int returnVal = fc.showOpenDialog(ChatView.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						BufferedReader bufr = new BufferedReader(
								new InputStreamReader(new FileInputStream(
										file)));
						title = file.getName();
						while ((temp = bufr.readLine()) != null) {
							str += temp + "\n";
						}
						System.out.println("File name: " + title + " File content: " + str);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		jPfriend.add(image3);
		image4.setBounds(197, 48, 30, 32);
		image4.setBackground(Color.PINK);
		jPfriend.add(image4);
		image5.setBounds(242, 48, 32, 29);
		image5.setBackground(Color.PINK);
		jPfriend.add(image5);
		image6.setBounds(289, 48, 31, 32);
		image6.setBackground(Color.PINK);
		jPfriend.add(image6);
		
		// Display friend's basic information
		point.setBounds(5, 85, 17, 19);
		point.setBackground(Color.WHITE);
		jLpoint.setBounds(25, 85, 375, 25);
		jLpoint.setFont(new Font("SimSun", Font.PLAIN, 12));
		jLpoint.setForeground(Color.GRAY);
		jLpoint.setBackground(Color.WHITE);
		jTAshowChat.setBackground(Color.WHITE);
		jTAshowChat.setLineWrap(true); // Auto wrap
		jTAshowChat.setFont(new Font("SimSun", Font.PLAIN, 17));
		jSshowChat.setBounds(0, 110, 400, 255);
		
		// Set QQ avatars
		if (currentFriend.getSex().equals("Female")) {
			friend.setIcon(new ImageIcon("src/file/friend.jpg"));
		}
		if (currentFriend.getSex().equals("Male")) {
			friend.setIcon(new ImageIcon("src/file/CVboy.jpg"));
		}
		if (myInfo.getSex().equals("Male")) {
			me.setIcon(new ImageIcon("src/file/me.jpg"));
		}
		if (myInfo.getSex().equals("Female")) {
			me.setIcon(new ImageIcon("src/file/CVgirl.jpg"));
		}
		jPqq.setBackground(Color.WHITE);
		jPqq.setLayout(new BorderLayout());
		jPqq.setBounds(400, 82, 140, 428);
		friend.setSize(0, 0);
		jPqq.add(BorderLayout.NORTH, friend);
		me.setSize(0, 0);
		jPqq.add(BorderLayout.SOUTH, me);
		
		// Middle toolbar
		jPinfo.setBounds(0, 365, 319, 22);
		jPinfo.setLayout(new BorderLayout());
		jPinfo.setBackground(Color.WHITE);
		jBshowinfo.setSize(81, 22);
		jLinfo.setSize(302, 22);
		jLinfo1.setSize(17, 22);
		jLinfo.setLocation(0, 370);
		jLinfo1.setLocation(302, 370);
		jBshowinfo.setLocation(319, 365);
		jBshowinfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					bufr = new BufferedReader(
							new InputStreamReader(new FileInputStream(
									path)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				SaveChat save = new SaveChat(ChatView.this, "Chat Records", true, bufr);
				save.setVisible(true);
			}
		});
		jPinfo.add(BorderLayout.WEST, jLinfo);
		jPinfo.add(BorderLayout.EAST, jLinfo1);
		
		// Message input
		jTAshowsend.setBackground(Color.WHITE);
		jTAshowsend.setFont(new Font("SimSun", Font.PLAIN, 15));
		jSshowsend.setBounds(0, 387, 400, 100);
		
		// Bottom buttons
		jBclose.setSize(69, 23);
		jBsend.setSize(80, 23);
		jBsend.setLocation(310, 487);
		jBclose.setLocation(231, 487);
		jBclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChatView.this.setVisible(false);
			}
		});
		jBsend.addActionListener(this);
	}

	@Override
	public void run() {
		String receiveInfo = "";
		while (true) {
			Date time = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String timeInfo = format.format(time);
			try {
				receiveSocket.receive(receivePacket);
				receiveInfo = new String(receivePacket.getData(), 0, receivePacket.getLength());
				int num_index = receiveInfo.indexOf("*"); // Get position of *
				int name_index = receiveInfo.indexOf("/");
				String friendNum = receiveInfo.substring(0, num_index).trim(); // Get sender's QQ number
				String friendName = receiveInfo.substring(num_index + 1, name_index); // Get sender's username
				String friendInfo = receiveInfo.substring(name_index + 1); // Get sender's message
				if (!friendInfoTable.containsKey(friendNum)) {
					ChatStrange strange = new ChatStrange(null, "Received Message from Stranger", true, friendNum, friendName, friendInfo);
					strange.setVisible(true);
				} else {
					System.out.println("Received message: " + friendInfo);
					jTAshowChat.append(" " + friendName + "  " + timeInfo + line_separator);
					jTAshowChat.append("  " + friendInfo + line_separator + line_separator);
					jTAshowChat.append(line_separator);
					bw.write(" " + friendName + "  " + timeInfo);
					bw.newLine();
					bw.write("  " + friendInfo);
					bw.newLine();
					bw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to receive message");
			}
		}
	}

	public void jBsend_actionPerformed() {
		String myNum = myInfo.getUserNum(); // Get my QQ number
		String myName = myInfo.getUserName(); // Get my username
		String initInfo = jTAshowsend.getText().trim(); // Get message to send
		String sendInfo = myNum + "*" + myName + "/" + initInfo; // Package message to send (3 parts: my QQ number, my username, message to send)
		outBuf = sendInfo.getBytes(); // Convert message to byte array
		if (initInfo.length() != 0) {
			try {
				Date time = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
				String timeInfo = format.format(time);
				sendPacket = new DatagramPacket(outBuf, outBuf.length, InetAddress.getByName(friendIp), friendPort);
				System.out.println("Sending message to friend: " + friendIp + " port " + friendPort + "  " + sendInfo);
				sendSocket.send(sendPacket);
				jTAshowChat.append(myInfo.getUserName() + "  " + timeInfo + line_separator);
				jTAshowChat.append("  " + initInfo + line_separator);
				jTAshowChat.append(line_separator + line_separator);
				jTAshowsend.setText(null);
				bw.write(myInfo.getUserName() + "  " + timeInfo);
				bw.newLine();
				bw.write("  " + initInfo);
				bw.newLine();
				bw.flush();
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(jBsend, "Friend is offline, cannot connect to specified address");
				e.printStackTrace();
			} catch (SocketException e) {
				JOptionPane.showMessageDialog(jBsend, "Cannot connect to specified port");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(jBsend, "Message sending failed");
				e.printStackTrace();
			}
		} else
			JOptionPane.showMessageDialog(jBsend, "Message cannot be empty, please enter content");
	}

	// User presses Alt+Enter to send
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.isAltDown() && (e.getKeyChar() == '\n'))
			jBsend_actionPerformed();
	}

	// Handle send button event
	@Override
	public void actionPerformed(ActionEvent e) {
		jBsend_actionPerformed();
	}

	// Empty implementations for interface methods
	@Override
	public void keyPressed(KeyEvent arg0) {
		// Empty
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Empty
	}
}
