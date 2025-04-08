package cn.itbaizhan.client;

/*Has
 * HashMap<String,String> map=new HashMap<String,String>;
 * map.put("1","student");
 *Iterator<String> mit=map.keySet().iterator();
 *while(mit.hasNext()){
 *String str=mit.next();
 *System.out.println(map.get(str));
 *} */
//dengluh
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.OptionPaneUI;

import cn.itbaizhan.common.FriendLabel;
import cn.itbaizhan.common.UserBean;
import cn.itbaizhan.common.UserInfo;

/* Main Interface */
public class PersonelView extends JFrame implements Runnable {
	/*------------------------------------- Interface Components ---------------------------------------------------*/
	JPanel jPtop = new JPanel();// Top panel
	JPanel jPcentre = new JPanel();// Center panel with tabs and list
	// Title
	JLabel jLtitle = new JLabel("We_Talk");
	// QQ avatar size 60*60
	JLabel jLportrait = new JLabel(new ImageIcon("src/file/personelView1.jpg"));
	JLabel jLmyName = new JLabel("My Name");
	JLabel jLmySign = new JLabel("Personal Signature");
	JTextField jTfind = new JTextField("Search We_Talk account to add contact");
	ImageIcon imageFind = new ImageIcon("src/file/personelViewFind.jpg");// Size 19*21
	JButton jBfind = new JButton(imageFind);// Search contact button
	// Center
	JLabel jLcenter1 = new JLabel(new ImageIcon("src/file/personelViewCenter1.jpg"));// Size 305*7
	// Middle
	JTabbedPane jTPchoose = new JTabbedPane();// Tab selection
	JPanel jPcentrefriend = new JPanel();// Center panel with tabs and list
	JLabel j1 = new JLabel(new ImageIcon("src/file/tab1.jpg"));
	JLabel j2 = new JLabel(new ImageIcon("src/file/tab2.jpg"));
	JLabel j3 = new JLabel(new ImageIcon("src/file/tab1.jpg"));
	JLabel j4 = new JLabel(new ImageIcon("src/file/tab2.jpg"));
	DefaultListModel listModel = new DefaultListModel();
	JList userList = new JList(listModel);
	JScrollPane jSuserList = new JScrollPane(userList);
	// User list label with right-click menu
	JMenuItem jM11 = new JMenuItem("List Display");
	JMenuItem jM12 = new JMenuItem("Refresh Friend List");
	JMenuItem jM13 = new JMenuItem("Show All Contacts");
	JMenuItem jM14 = new JMenuItem("Add Contact");
	JMenuItem jM15 = new JMenuItem("Find Friend");
	JMenuItem jM16 = new JMenuItem("Friend Management");
	JMenuItem jM17 = new JMenuItem("Settings");
	JMenuItem jM18 = new JMenuItem("About");
	JPopupMenu jPmenuser = new JPopupMenu();
	// Friend list right-click menu options
	JMenuItem jM1 = new JMenuItem("Send Instant Message");
	JMenuItem jM2 = new JMenuItem("Send Email");
	JMenuItem jM3 = new JMenuItem("Send File");
	JMenuItem jM4 = new JMenuItem("Delete Friend");
	JMenuItem jM5 = new JMenuItem("Report User");
	JMenuItem jM6 = new JMenuItem("Modify Remark Name");
	JMenuItem jM7 = new JMenuItem("Message History");
	JMenuItem jM8 = new JMenuItem("View Information");
	JPopupMenu jPmenufriend = new JPopupMenu();
	// Bottom
	JLabel jLbase = new JLabel(new ImageIcon("src/file/personelView2.jpg"));// Size 304*61

	/*------------------------------------- Instance Variables ---------------------------------------------------*/
	private Hashtable friendInfoTable = new Hashtable();// Store friend list
	Socket socket;// Client socket
	BufferedReader in;// Input stream
	PrintStream out;// Output stream
	InetAddress ip = null;// Server IP
	int port = 0;// Server port
	String userNum;// Logged in user's QQ number
	String userPass;// Logged in user's password
	HomePage login;
	private int currentIndex = 0;// Current selected list index
	private String currentInfo = "";// Current selected list value
	private String currentUserNum = null;// Current friend's QQ number
	private UserBean currentFriend = null;// Current friend's information
	private UserBean myInfo = new UserBean();// Store own information
	UserBean findUserBean = new UserBean();// Store found user's basic information
	/* UDP Protocol Communication */
	private DatagramSocket receiveSocket = null;// Receive message datagram socket
	private DatagramPacket receivePacket = null;// Receive message datagram
	int udpPort = getUdpPort("udp.Port");// UDP initial port
	InetAddress userIp = null;
	int usePort = getNextPort(udpPort);// User's port
	public static final int BUFFER_SIZE = 5120;// Buffer size
	private byte inBuf[];// Input buffer
	// Message history
	BufferedReader bufr;// Message history reader
	String path = null;

	/*------------------------------------- Constructor ---------------------------------------------------*/
	public PersonelView(String userNum, String userPass, HomePage login, InetAddress ip, int port) {
		// TODO Auto-generated constructor stub
		this.userNum = userNum;
		this.userPass = userPass;
		this.login = login;
		this.ip = ip;
		this.port = port;
		this.setSize(313, 590);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		init();
		this.add(jPtop);
		this.add(jLcenter1);
		this.add(jLbase);
		this.add(jTPchoose);
		/*------------------------ TCP Protocol Connection, Complete Login and Friend List -----------------------*/
		try {
			socket = new Socket(ip, port);
			System.out.println("Server connection started");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Server port opening error");
		}
		if (socket != null) {
			System.out.println("Server connection successful");
			try {
				userIp = InetAddress.getLocalHost();
			} catch (UnknownHostException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// udpPort=socket.getLocalPort();
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/*-------------------------------------- UDP Protocol, Chat Module -----------------------------------------*/
		try {
			// Receive message datagram socket
			// System.out.println("1ҽݵĶ˿ں:"+usePort);
			receiveSocket = new DatagramSocket(usePort);
			// System.out.println("2ҽݵĶ˿ں:"+usePort);
			inBuf = new byte[BUFFER_SIZE];
			// Receive message datagram
			receivePacket = new DatagramPacket(inBuf, BUFFER_SIZE);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Port opening error");
		}
		loadUserInfo();
		// Start thread to refresh friend information
		new Thread(this).start();
		this.setVisible(true);
	}

	// Get user information
	private void loadUserInfo() {
		if (login()) {
			getFriendInfo();// Get friend information list
			userList.setCellRenderer(new FriendLabel());
			jLportrait.setIcon(new ImageIcon(myInfo.getPortrait()));
			jLmyName.setText(myInfo.getUserName());
			jLmySign.setText(myInfo.getSign());
		} else {
			login.loginFail();
			System.out.println("Failed to get information");
			return;
		}
	}

	// Refresh friend information every 10 seconds
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Refresh user information
			loadUserInfo();
		}

	}

	public void init() {
		// Top panel
		jPtop.setBounds(0, 0, 313, 110);
		jPtop.setLayout(null);
		jPtop.setBackground(Color.PINK);
		jLtitle.setForeground(Color.WHITE);
		jLtitle.setFont(new Font("SimSun", Font.BOLD, 15));
		jLtitle.setBounds(0, 0, 313, 25);
		jLportrait.setBounds(5, 25, 60, 55);

		// Add mouse listener to own avatar to view own information
		jLportrait.addMouseListener(new PersonelView_jLportrait_mouseMotionAdapter());
		jLmyName.setForeground(Color.WHITE);
		jLmyName.setFont(new Font("SimSun", Font.BOLD, 17));
		jLmyName.setBounds(80, 27, 180, 25);
		// Add right-click menu to name
		JMenuItem jMa = new JMenuItem("Modify Personal Information");
		JMenuItem jMb = new JMenuItem("Change Avatar");
		// JMenuItem jMc=new JMenuItem("޸");
		jMa.setFont(new Font("SimSun", Font.PLAIN, 14));
		jMa.setForeground(Color.BLACK);
		jMa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ChangeMyInfo changemyInfo = new ChangeMyInfo(PersonelView.this, "Modify Information", true, in, out,
						myInfo, PersonelView.this);
				changemyInfo.setVisible(true);
				PersonelView.this.refreshMyInfo();
			}

		});
		jMb.setFont(new Font("SimSun", Font.PLAIN, 14));
		jMb.setForeground(Color.BLACK);
		jMb.addActionListener(new ActionListener() {// Change avatar

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changHead();

			}

		});
		// jMc.setFont(new Font("",Font.PLAIN,14));
		// jMc.setForeground(Color.BLACK);
		JPopupMenu jPmenuMy = new JPopupMenu();
		jPmenuMy.add(jMa);
		jPmenuMy.add(jMb);
		// jPmenuMy.add(jMc);
		jLmyName.setComponentPopupMenu(jPmenuMy);
		jLmySign.setForeground(Color.WHITE);
		jLmySign.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLmySign.setBounds(70, 55, 235, 25);
		jLmySign.setComponentPopupMenu(jPmenuMy);
		jTfind.setFont(new Font("SimSun", Font.BOLD, 12));
		jTfind.setForeground(Color.GRAY);
		jTfind.setBounds(3, 85, 260, 25);
		jBfind.setBounds(267, 87, 19, 21);
		jBfind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String num = jTfind.getText().trim();
				findUser(num);
			}
		});
		jPtop.add(jBfind);
		jPtop.add(jTfind);
		jPtop.add(jLmySign);
		jPtop.add(jLmyName);
		jPtop.add(jLportrait);
		jPtop.add(jLtitle);
		jPtop.add(jLtitle);
		// Center
		jLcenter1.setBounds(0, 110, 313, 7);
		// Middle
		/*-------------------------- Add right-click menu ---------------------*/
		jPmenufriend.add(jM1);
		jPmenufriend.add(jM2);
		jPmenufriend.add(jM3);
		jPmenufriend.add(jM4);
		jPmenufriend.add(jM5);
		jPmenufriend.add(jM6);
		jPmenufriend.add(jM7);
		jM7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				path = "src/chat history/" + myInfo.getUserNum() + "-" + currentFriend.getUserNum() + ".txt";
				try {
					bufr = new BufferedReader(
							new InputStreamReader(new FileInputStream(
									path)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SaveChat save = new SaveChat(PersonelView.this, "Chat History", true, bufr);
				save.setVisible(true);
			}
		});
		jPmenufriend.add(jM8);
		jM1.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM1.setForeground(Color.BLUE);
		jM1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Thread(new ChatView(myInfo, currentFriend, PersonelView.this, usePort, receiveSocket, receivePacket,
						friendInfoTable, in, out)).start();
			}

		});
		jM2.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM2.setForeground(Color.BLUE);
		jM3.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM3.setForeground(Color.BLUE);
		jM4.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM4.setForeground(Color.BLUE);
		jM4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(PersonelView.this,
						"Are you sure you want to delete this friend?");
				if (option == JOptionPane.YES_OPTION) {
					int index = userList.getSelectedIndex();
					String friendNum = null;
					if (index == -1) {
						JOptionPane.showMessageDialog(PersonelView.this, "Please select a user");
					} else {
						try {
							String friendInfo = (String) listModel.getElementAt(index);
							friendNum = friendInfo.substring(friendInfo.indexOf("<") + 1, friendInfo.indexOf(">"));
							UserBean deleteFriend = (UserBean) friendInfoTable.get(friendNum);
							String myUserNum = myInfo.getUserNum();
							// Send request to server to delete friend
							out.println("deleteFriend");
							out.flush();
							// Send own QQ
							out.println(myUserNum);
							out.flush();
							// Send friend QQ
							out.println(friendNum);
							out.flush();
							String judge_delete = in.readLine();
							if (judge_delete.equals("deleteFriendOver")) {
								JOptionPane.showMessageDialog(PersonelView.this,
										"Friend <" + deleteFriend.getUserName() + "> has been successfully deleted!");
								listModel.remove(index);
							} else if (judge_delete.equals("deleteFriendFail")) {
								JOptionPane.showMessageDialog(PersonelView.this, "System busy, please try again later");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(PersonelView.this, "System is under maintenance");
						}
					}
				}
			}

		});
		jM5.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM5.setForeground(Color.BLUE);
		jM6.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM6.setForeground(Color.BLUE);
		jM7.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM7.setForeground(Color.BLUE);
		jM8.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM8.setForeground(Color.BLUE);
		jM8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserInfo friendInfo = new UserInfo(PersonelView.this, "Friend Information", true, currentFriend);
				friendInfo.setVisible(true);
			}

		});
		userList.setComponentPopupMenu(jPmenufriend);
		jTPchoose.setForeground(Color.DARK_GRAY);
		jTPchoose.setBackground(Color.WHITE);
		jTPchoose.setFont(new Font("SimSun", Font.PLAIN, 11));
		jTPchoose.addTab("Contacts", jPcentre);
		jTPchoose.addTab("Group/Chat", j1);
		jTPchoose.addTab("Recent Contacts", j2);
		jTPchoose.addTab("Settings", j3);
		jTPchoose.addTab("WeChat", j4);
		jTPchoose.setBounds(0, 117, 313, 382);
		jPcentre.setLayout(new BorderLayout());
		JLabel test = new JLabel("Friend List");
		test.setFont(new Font("SimSun", Font.PLAIN, 14));
		test.setSize(313, 30);
		test.setForeground(Color.BLACK);
		jPmenuser.add(jM11);
		jPmenuser.add(jM12);
		jPmenuser.add(jM13);
		jPmenuser.add(jM14);
		jPmenuser.add(jM15);
		jPmenuser.add(jM16);
		jPmenuser.add(jM17);
		jPmenuser.add(jM18);
		jM11.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM11.setForeground(Color.DARK_GRAY);
		jM12.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM12.setForeground(Color.DARK_GRAY);
		jM13.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM13.setForeground(Color.DARK_GRAY);
		jM14.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM14.setForeground(Color.DARK_GRAY);
		jM14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PrintNumFindUser printNum = new PrintNumFindUser(PersonelView.this, "Add Contact", false,
						PersonelView.this);
				printNum.setVisible(true);
			}
		});
		jM15.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM15.setForeground(Color.DARK_GRAY);
		jM15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PrintNumFindUser printNum = new PrintNumFindUser(PersonelView.this, "Find User", false,
						PersonelView.this);
				printNum.setVisible(true);
			}

		});
		jM16.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM16.setForeground(Color.DARK_GRAY);
		jM17.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM17.setForeground(Color.DARK_GRAY);
		jM18.setFont(new Font("SimSun", Font.PLAIN, 14));
		jM18.setForeground(Color.DARK_GRAY);
		jM18.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AboutMy a = new AboutMy(PersonelView.this, "About QQ", true);
				a.setVisible(true);
			}

		});
		test.setComponentPopupMenu(jPmenuser);
		jPcentre.setBackground(Color.WHITE);
		jPcentre.setBounds(0, 117, 313, 382);
		userList.addMouseListener(new PersonelView_userList_mouseAdapter());
		userList.addMouseMotionListener(new PersonelView_userList_mouseMotionAdapter());
		jPcentre.add(BorderLayout.NORTH, test);
		jPcentre.add(jSuserList);
		// Bottom
		jLbase.setBounds(0, 499, 304, 51);
	}

	// Login and get own and friend information
	private Boolean login() {
		String judge = null;

		try {
			out.println("login");
			out.flush();
			out.println(userNum);
			out.flush();
			out.println(userPass);
			out.flush();
			out.println(userIp);
			out.flush();
			out.println(usePort);
			// System.out.println("͸ݿҵĶ˿ں"+usePort);
			out.flush();
			// Get own information
			judge = in.readLine();
			if (judge.equals("loginFail")) {
				return false;
			} else if (judge.equals("sendUserInfo")) {
				String flag_1 = in.readLine();
				if (flag_1.equals("queryUserFail")) {
					return false;
				} else {
					myInfo.setUserNum(flag_1);
					myInfo.setUserName(in.readLine());
					myInfo.setSex(in.readLine());
					myInfo.setBirth(in.readLine());
					myInfo.setAddress(in.readLine());
					myInfo.setSign(in.readLine());
					myInfo.setPortrait(in.readLine());
					myInfo.setStatus(Integer.valueOf(in.readLine()));
					myInfo.setPort(Integer.valueOf(in.readLine()));
					myInfo.setIp(in.readLine());
					// Own information obtained, send loginSuccess to start getting friend
					// information
				}
			}
			// Own information obtained, send loginSuccess to start getting friend
			// information
			String flag_3 = in.readLine();
			if (flag_3.equals("loginSuccess")) {
				/*----------------- Start reading friend information from server --------------------------*/
				friendInfoTable.clear();
				String flag2 = "";
				do {
					flag2 = in.readLine().trim();
					System.out.println("flag2:" + flag2);
					if (flag2.equals("queryFriendOver")) {
						System.out.println("BREAK");
						break;

					} else {
						UserBean friendBean = new UserBean();
						System.out.println(friendBean);
						friendBean.setUserNum(flag2);
						friendBean.setUserName(in.readLine());
						friendBean.setSex(in.readLine());
						friendBean.setBirth(in.readLine());
						friendBean.setAddress(in.readLine());
						friendBean.setSign(in.readLine());
						friendBean.setPortrait(in.readLine());
						friendBean.setStatus(Integer.valueOf(in.readLine()));
						friendBean.setPort(Integer.valueOf(in.readLine()));
						friendBean.setIp(in.readLine());
						friendInfoTable.put(flag2, friendBean);
					}
				} while (!(flag2.equals("queryFriendOver")));
			} else if (flag_3.equals("queryUserFail")) {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// Get friend information for list display
	private void getFriendInfo() {
		listModel.removeAllElements();
		// Implement Enumeration interface to iterate through elements
		Enumeration it = friendInfoTable.elements();
		String name = "";
		String num = "";
		String portrait = "";
		String friendinfo = "";// Each friend's display information
		int status = 0;
		while (it.hasMoreElements()) {
			UserBean user = (UserBean) it.nextElement();
			name = user.getUserName().trim();
			num = user.getUserNum().trim();
			portrait = user.getPortrait();
			status = user.getStatus();
			friendinfo = status + name + "<" + num + ">" + "*" + portrait + "^";
			listModel.addElement(friendinfo);
		}

	}

	// Get port
	private int getUdpPort(String key) {
		int myport = 0;
		Properties p = new Properties();
		try {
			FileInputStream in = new FileInputStream("src/file/udp.txt");
			FileOutputStream out = new FileOutputStream("src/file/udp.txt", true);
			p.load(in);// Read properties from file
			myport = Integer.parseInt(p.getProperty(key));
			myport = myport + 1;
			p.setProperty("udp.Port", new Integer(myport).toString());
			p.store(out, "new udp.Port");
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myport;
	}

	private int getNextPort(int port) {
		int nextport = port;
		Boolean flag = true;
		DatagramSocket testsocket = null;
		// Check if port is occupied
		while (true) {
			flag = true;
			try {
				testsocket = new DatagramSocket(++nextport);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				flag = false;
			}
			if (flag == true) {
				break;
			}
			System.out.println(nextport);

		}
		testsocket.close();
		return nextport;

	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			exit();
		}
	}

	public void exit()// Handle user logout
	{
		int option = JOptionPane.showConfirmDialog(PersonelView.this, "Are you sure you want to exit?");
		if (option == JOptionPane.YES_OPTION) {
			try {
				out.println("logout");
				out.flush();
				out.println(myInfo.getUserNum());
				out.flush();
				String msg = in.readLine();
				if (msg.equals("logOut")) {
					out.println("end");
					out.flush();
					in.close();
					out.close();
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.dispose();
				System.exit(0);
			}
		}
	}

	// Handle double-click event on friend avatar
	class PersonelView_userList_mouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				new Thread(new ChatView(myInfo, currentFriend, PersonelView.this, usePort, receiveSocket, receivePacket,
						friendInfoTable, in, out)).start();

			}
		}
	}

	// Handle mouse movement over friend avatar
	class PersonelView_userList_mouseMotionAdapter extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			if (!(listModel.isEmpty())) {
				currentIndex = userList.locationToIndex(e.getPoint());// Get current list index
				currentInfo = listModel.getElementAt(currentIndex).toString();// Get current list value
				currentUserNum = currentInfo.substring(currentInfo.indexOf("<") + 1, currentInfo.indexOf(">"));
				// Find friend information based on QQ number
				currentFriend = (UserBean) friendInfoTable.get(currentUserNum);
				String friendSign = currentFriend.getSign();
				userList.setToolTipText(friendSign);// Set tooltip to show friend's personal signature
			}
		}
	}

	class PersonelView_jLportrait_mouseMotionAdapter extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			jLportrait.setCursor(new Cursor(Cursor.HAND_CURSOR));// Change cursor to hand when hovering over own avatar
		}

		public void mousePressed(MouseEvent e) {

			UserInfo userInfo = new UserInfo(PersonelView.this, "My Information", true, myInfo);
			userInfo.setVisible(true);
		}
	}

	public void findUser(String num) {
		out.println("queryUser");
		out.flush();
		out.println(num);
		out.flush();
		try {
			String judge_find = in.readLine();
			if (judge_find.equals("noUser")) {
				JOptionPane.showMessageDialog(PersonelView.this, "Sorry, this user does not exist");
			} else if (judge_find.equals("queryUserFail")) {
				JOptionPane.showMessageDialog(PersonelView.this, "Failed to find user");
			} else {
				findUserBean.setUserNum(judge_find);
				findUserBean.setUserName(in.readLine());
				findUserBean.setSex(in.readLine());
				String birthDate = in.readLine();
				findUserBean.setBirth(birthDate);
				findUserBean.setAddress(in.readLine());
				findUserBean.setSign(in.readLine());
				findUserBean.setPortrait(in.readLine());
				findUserBean.setStatus(Integer.valueOf(in.readLine()));
				findUserBean.setPort(Integer.valueOf(in.readLine()));
				findUserBean.setIp(in.readLine());
				FindUser find = new FindUser(PersonelView.this, "User Information", true, findUserBean,
						PersonelView.this);
				find.setVisible(true);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(PersonelView.this, "System is under maintenance");
		}
	}

	public void addUser() {
		try {
			String name = findUserBean.getUserName().trim();
			String num = findUserBean.getUserNum().trim();
			String portrait = findUserBean.getPortrait();
			int status = findUserBean.getStatus();
			String friendinfo = status + name + "<" + num + ">" + "*" + portrait + "^";
			if (listModel.contains(friendinfo)) {
				JOptionPane.showMessageDialog(PersonelView.this, "This user is already in your friend list");
			} else {
				// Send request to server to add friend
				out.println("addFriend");
				out.flush();
				out.println(myInfo.getUserNum());
				out.flush();
				out.println(findUserBean.getUserNum());
				out.flush();
				String judge_add = in.readLine();
				if (judge_add.equals("addFriendOver")) {
					// Add friend information to hash table
					friendInfoTable.put(findUserBean.getUserNum().trim(), findUserBean);
					// Display added friend in list
					listModel.addElement(friendinfo);
					JOptionPane.showMessageDialog(PersonelView.this, "Added successfully");
				} else if (judge_add.equals("addFriendFail")) {
					JOptionPane.showMessageDialog(PersonelView.this, "Failed to add friend");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Refresh own avatar
	public void refreshMyProtrait() {
		jLportrait.setIcon(new ImageIcon(myInfo.getPortrait()));
	}

	public void refreshMyInfo() {
		jLmyName.setText(myInfo.getUserName());
		jLmySign.setText(myInfo.getSign());
	}

	public void changHead() {
		ChangeHead changeHead = new ChangeHead(PersonelView.this, "Change Avatar", true, myInfo, in, out,
				PersonelView.this);
		changeHead.setVisible(true);
		PersonelView.this.refreshMyProtrait();
	}
}
