package cn.itbaizhan.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cn.itbaizhan.common.ShowTimeTask;
import cn.itbaizhan.common.UserBean;
import cn.itbaizhan.common.UserInfo;

/* Server control interface */
public class ServerFrame extends JFrame {
	JLabel jshowList = new JLabel("Online User List<Refresh every 10 seconds>");
	JLabel jshowServerLog = new JLabel("Server Log");
	JLabel jUserCount = new JLabel("Online Users:");
	JLabel jCount = new JLabel("0");
	JLabel jLtime = new JLabel();
	JButton jBgetInfo = new JButton("View Info");
	JButton jBkickOut = new JButton("Kick Out");
	JButton jBpauseServer = new JButton("Pause Server");
	JButton jBexit = new JButton("Exit");
	DefaultListModel listModel = new DefaultListModel();
	JList userList = new JList(listModel);
	JScrollPane jSuserList = new JScrollPane(userList);
	JTextArea jTServerLog = new JTextArea();
	JScrollPane jServerLog = new JScrollPane(jTServerLog);
	private Connection con = null;
	ServerThread serverThread = null;
	private Hashtable userTable = new Hashtable(); // Store UserBean objects in HashTable

	public ServerFrame() {
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Server Control Interface");
		// Establish database connection
		con = ConnectionDao.getConnection();
		init();
		this.add(jshowList);
		this.add(jSuserList);
		this.add(jBgetInfo);
		this.add(jBkickOut);
		this.add(jUserCount);
		this.add(jCount);
		this.add(jshowServerLog);
		this.add(jServerLog);
		this.add(jBpauseServer);
		this.add(jBexit);
		this.add(jLtime);
		serverThread = new ServerThread(jTServerLog); // Create ServerSocket socket, prepare to wait for client connections
		serverThread.start();
		// Make jLtime dynamically display time
		java.util.Timer myTime = new java.util.Timer();
		java.util.TimerTask task_showtime = new ShowTimeTask(jLtime);
		myTime.schedule(task_showtime, 0, 1000);
		java.util.Timer time = new java.util.Timer();
		java.util.TimerTask task_time = new LoginUser(listModel, userList, jCount, userTable, con);
		time.schedule(task_time, 0, 10000); // Refresh every 10 seconds
		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	// Set up server interface components
	public void init() {
		jshowList.setBounds(20, 10, 150, 25);
		jshowList.setFont(new Font("SimSun", Font.PLAIN, 11));
		jSuserList.setBounds(10, 40, 190, 500);
		jBgetInfo.setBounds(20, 550, 75, 25);
		jBgetInfo.setFont(new Font("SimSun", Font.PLAIN, 10));
		// View Info button action
		jBgetInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedUser = null;
				String userNum = null;
				selectedUser = (String) userList.getSelectedValue();
				if (selectedUser == null) {
					JOptionPane.showMessageDialog(jBgetInfo, "Please select a user");
				} else {
					System.out.println(selectedUser);
					userNum = selectedUser.substring(selectedUser.indexOf("<") + 1, selectedUser.indexOf(">"));
					UserBean user = (UserBean) userTable.get(userNum);
					UserInfo userInfo = new UserInfo(ServerFrame.this, "View Information", true, user);
					userInfo.setVisible(true);
				}
			}
		});

		jBkickOut.setBounds(110, 550, 60, 25);
		jBkickOut.setFont(new Font("SimSun", Font.PLAIN, 10));
		// Kick Out button action
		jBkickOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = userList.getSelectedIndex();
				String userNum = null;
				if (index == -1) {
					JOptionPane.showMessageDialog(jBkickOut, "Please select a user");
				} else {
					String userInfo = (String) listModel.getElementAt(index);
					userNum = userInfo.substring(userInfo.indexOf("<") + 1, userInfo.indexOf(">"));
					System.out.println(userNum);
					removeUser(userNum);
					listModel.remove(index);
					int num = Integer.parseInt(jCount.getText()) - 1;
					jCount.setText(new Integer(num).toString());
				}
			}
		});

		jUserCount.setBounds(25, 595, 100, 30);
		jUserCount.setFont(new Font("SimSun", Font.PLAIN, 12));
		jCount.setBounds(125, 595, 20, 30);
		jCount.setFont(new Font("SimSun", Font.PLAIN, 12));
		jshowServerLog.setBounds(220, 10, 150, 25);
		jshowServerLog.setFont(new Font("SimSun", Font.PLAIN, 11));
		jServerLog.setBounds(210, 40, 575, 550);
		jBpauseServer.setBounds(340, 595, 90, 25);
		jBpauseServer.setFont(new Font("SimSun", Font.PLAIN, 11));
		// Pause Server button action
		jBpauseServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if (command.equals("Pause Server")) {
					serverThread.pauseThread();
					jBpauseServer.setText("Resume Server");
				} else if (command.equals("Resume Server")) {
					serverThread.reStartThread();
					jBpauseServer.setText("Pause Server");
				}
			}
		});

		jBexit.setBounds(470, 595, 60, 25);
		jBexit.setFont(new Font("SimSun", Font.PLAIN, 11));
		// Exit button action
		jBexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(jBexit, "Are you sure you want to exit?");
				if (option == JOptionPane.YES_OPTION) {
					try {
						con.close(); // Close database connection
						// Close database connection of server thread
						System.exit(0);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		jLtime.setBounds(600, 615, 250, 50);
		jLtime.setFont(new Font("SimSun", Font.PLAIN, 12));
	}

	// Kick out user, set user status to 0 in database
	public void removeUser(String userNum) {
		String sql = "UPDATE UserInformation SET Status = 0 where UserNum= '" + userNum + "'";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ServerFrame f = new ServerFrame();
		f.setVisible(true);
	}
}
