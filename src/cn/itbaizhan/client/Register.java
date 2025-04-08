package cn.itbaizhan.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/* Registration module */
public class Register extends JFrame {
	String userName;
	String userPass;
	String sex;
	String birth;
	String address;
	String type;
	String y;
	String m;
	String d;
	JLabel jLtop = new JLabel(new ImageIcon("src/file/regist1.jpg"));
	JLabel jLeft = new JLabel(new ImageIcon("src/file/regist2.jpg"));
	JLabel jLname = new JLabel("Nickname");
	JLabel alertName = new JLabel("Please enter nickname");
	JLabel alertPass = new JLabel("Password must be 6-16 characters, no spaces");
	JLabel alertRePass = new JLabel("Please confirm password");
	JTextField jTname = new JTextField();
	JLabel jLpass = new JLabel("Password");
	JPasswordField jPass = new JPasswordField();
	JLabel jLRepass = new JLabel("Confirm Password");
	JPasswordField jRepass = new JPasswordField();
	JLabel jLsex = new JLabel("Gender");
	ButtonGroup radioGroup = new ButtonGroup();
	JRadioButton jRboy = new JRadioButton("Male", false);
	JRadioButton jRgirl = new JRadioButton("Female", false);
	JLabel jLbirth = new JLabel("Birthday");
	JLabel jyear = new JLabel("Year");
	JLabel jmonth = new JLabel("Month");
	JLabel jday = new JLabel("Day");
	DefaultComboBoxModel yearModel = new DefaultComboBoxModel();
	DefaultComboBoxModel monthModel = new DefaultComboBoxModel();
	DefaultComboBoxModel dayModel = new DefaultComboBoxModel();
	JComboBox year = new JComboBox();
	JComboBox month = new JComboBox();
	JComboBox day = new JComboBox();
	JLabel jLplace = new JLabel("Location");
	JTextField jTplace = new JTextField();
	JCheckBox jService = new JCheckBox("I agree to the terms of service", true);
	JButton jBregist = new JButton("Register");
	Socket socket;
	private BufferedReader in = null; // Input stream
	private PrintStream out = null; // Output stream

	public Register() {
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("We_Talk Registration");
		init();
		this.add(jLtop);
		this.add(jLeft);
		this.add(jLname);
		this.add(jTname);
		this.add(jLpass);
		this.add(jPass);
		this.add(jLRepass);
		this.add(jRepass);
		this.add(jLsex);
		this.add(jRboy);
		this.add(jRgirl);
		this.add(jLbirth);
		this.add(jyear);
		this.add(jmonth);
		this.add(jday);
		this.add(year);
		this.add(month);
		this.add(day);
		this.add(jLplace);
		this.add(jTplace);
		this.add(jService);
		this.add(jBregist);
		this.add(alertName);
		this.add(alertPass);
		this.add(alertRePass);
	}

	public void init() {
		for (int i = 1950; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
			yearModel.addElement(i);
		}
		for (int j = 1; j <= 12; j++) {
			monthModel.addElement(j);
		}
		for (int k = 1; k <= 31; k++) {
			dayModel.addElement(k);
		}
		year.setModel((ComboBoxModel) yearModel);
		month.setModel((ComboBoxModel) monthModel);
		day.setModel((ComboBoxModel) dayModel);

		// Calculate center positions
		int windowWidth = 800;
		int windowHeight = 700;
		int contentWidth = 600; // Increased content width
		int startX = (windowWidth - contentWidth) / 2; // Center horizontally
		int startY = 100; // Start from top
		int labelWidth = 150; // Increased label width
		int inputWidth = 220; // Input field width
		int spacing = 20; // Spacing between label and input

		// Set bounds with centered positions
		jLtop.setBounds(0, 0, windowWidth, 100);
		jLeft.setBounds(0, 100, 227, 600);

		// Labels and fields
		jLname.setBounds(startX, startY, labelWidth, 50);
		jTname.setBounds(startX + labelWidth + spacing, startY, inputWidth, 45);
		alertName.setBounds(startX + labelWidth + spacing + inputWidth + 10, startY + 5, 200, 20);

		jLpass.setBounds(startX, startY + 70, labelWidth, 50);
		jPass.setBounds(startX + labelWidth + spacing, startY + 70, inputWidth, 45);
		alertPass.setBounds(startX + labelWidth + spacing + inputWidth + 10, startY + 75, 200, 20);

		jLRepass.setBounds(startX, startY + 140, labelWidth, 50);
		jRepass.setBounds(startX + labelWidth + spacing, startY + 140, inputWidth, 45);
		alertRePass.setBounds(startX + labelWidth + spacing + inputWidth + 10, startY + 145, 200, 20);

		jLsex.setBounds(startX, startY + 210, labelWidth, 50);
		jRboy.setBounds(startX + labelWidth + spacing, startY + 210, 70, 50);
		jRgirl.setBounds(startX + labelWidth + spacing + 80, startY + 210, 70, 50);

		// Birthday section
		jLbirth.setBounds(startX, startY + 280, labelWidth, 50);
		jyear.setBounds(startX + labelWidth + spacing, startY + 280, 30, 35);
		year.setBounds(startX + labelWidth + spacing + 35, startY + 280, 70, 35);
		jmonth.setBounds(startX + labelWidth + spacing + 110, startY + 280, 30, 35);
		month.setBounds(startX + labelWidth + spacing + 145, startY + 280, 60, 35);
		jday.setBounds(startX + labelWidth + spacing + 210, startY + 280, 30, 35);
		day.setBounds(startX + labelWidth + spacing + 245, startY + 280, 60, 35);

		jLplace.setBounds(startX, startY + 350, labelWidth, 50);
		jTplace.setBounds(startX + labelWidth + spacing, startY + 350, inputWidth, 45);

		jService.setBounds(startX + labelWidth + spacing, startY + 420, 250, 25);
		jBregist.setBounds(startX + labelWidth + spacing, startY + 450, 200, 60);

		// Set fonts with adjusted sizes
		Font arial18 = new Font("Arial", Font.PLAIN, 18);
		Font arial16 = new Font("Arial", Font.PLAIN, 16);
		Font arial14 = new Font("Arial", Font.PLAIN, 14);
		Font arial12 = new Font("Arial", Font.PLAIN, 12);
		Font arialBold24 = new Font("Arial", Font.BOLD, 24);

		jLname.setFont(arial18);
		alertName.setFont(arial12);
		alertPass.setFont(arial12);
		alertRePass.setFont(arial12);
		jLpass.setFont(arial18);
		jLRepass.setFont(arial18);
		jLsex.setFont(arial18);
		jRboy.setFont(arial14);
		jRgirl.setFont(arial14);
		jLbirth.setFont(arial18);
		year.setFont(arial14);
		jyear.setFont(arial14);
		month.setFont(arial14);
		jmonth.setFont(arial14);
		day.setFont(arial14);
		jday.setFont(arial14);
		jLplace.setFont(arial18);
		jService.setFont(arial14);
		jBregist.setFont(arialBold24);

		// Set colors
		jRboy.setForeground(Color.BLUE);
		jRgirl.setForeground(Color.BLUE);
		year.setForeground(Color.BLUE);
		month.setForeground(Color.BLUE);
		day.setForeground(Color.BLUE);
		alertName.setForeground(Color.BLACK);
		alertPass.setForeground(Color.BLACK);
		alertRePass.setForeground(Color.BLACK);
		jBregist.setBackground(Color.green);
		jBregist.setForeground(Color.white);
		year.setBackground(Color.WHITE);
		month.setBackground(Color.WHITE);
		day.setBackground(Color.WHITE);

		// Add action listeners
		jRboy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jRboy) {
					sex = "Male";
				}
			}
		});

		jRgirl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jRgirl) {
					sex = "Female";
				}
			}
		});

		year.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				y = year.getSelectedItem().toString();
			}
		});

		month.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				m = month.getSelectedItem().toString();
			}
		});

		day.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				d = day.getSelectedItem().toString();
			}
		});

		radioGroup.add(jRboy);
		radioGroup.add(jRgirl);

		// Add registration button listener
		jBregist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jTname.getText().trim().length() > 10 || jTname.getText().trim().length() == 0) {
					alertName.setForeground(Color.RED);
				} else if ((String.valueOf(jPass.getPassword()).trim().length()) < 6) {
					alertName.setText("");
					alertPass.setText("Password length must be 6-16 characters");
					alertPass.setForeground(Color.RED);
				} else if ((String.valueOf(jPass.getPassword()).trim().length()) > 16) {
					alertName.setText("");
					alertPass.setForeground(Color.RED);
					alertPass.setText("Password length must be 6-16 characters");
				} else if (!((String.valueOf(jPass.getPassword()).trim())
						.equals(String.valueOf(jRepass.getPassword()).trim()))) {
					alertName.setText("");
					alertPass.setText("");
					alertRePass.setText("Passwords do not match");
					alertRePass.setForeground(Color.RED);
				} else if (sex == null) {
					alertName.setText("");
					alertPass.setText("");
					alertRePass.setText("");
					JOptionPane.showMessageDialog(jBregist, "Please select gender");
				} else if (y == null || m == null || d == null) {
					alertName.setText("");
					alertPass.setText("");
					alertRePass.setText("");
					JOptionPane.showMessageDialog(jBregist, "Please complete all birthday information");
				} else if (jTplace.getText().trim().length() == 0) {
					alertName.setText("");
					alertPass.setText("");
					alertRePass.setText("");
					JOptionPane.showMessageDialog(jBregist, "Location cannot be empty");
				} else {
					userName = jTname.getText().trim();
					userPass = String.valueOf(jPass.getPassword()).trim();
					address = jTplace.getText();
					// Format date as YYYY-MM-DD
					birth = String.format("%s-%02d-%02d", y, Integer.parseInt(m), Integer.parseInt(d));
					try {
						InetAddress ip = InetAddress.getByName("127.0.0.1");
						int port = (6544);
						socket = new Socket(ip, port);
						System.out.println("Starting connection...");
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Port opening error");
					}
					if (socket != null) {
						System.out.println("Connection successful");
						InetAddress userIp = null;
						int userPort;
						try {
							userIp = InetAddress.getLocalHost();
						} catch (UnknownHostException e2) {
							e2.printStackTrace();
						}
						userPort = socket.getLocalPort();
						try {
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							out = new PrintStream(socket.getOutputStream());
							System.out.println(birth + sex + address + userName + userPass);
							out.println("registNewUser");
							out.flush();
							out.println(userName);
							out.flush();
							out.println(userPass);
							out.flush();
							out.println(sex);
							out.flush();
							out.println(birth);
							out.flush();
							out.println(address);
							out.flush();
							String judge = in.readLine();
							if (judge.equals("registerOver")) {
								String userNum = in.readLine();
								CheckQQ checkQQ = new CheckQQ(userNum);
								checkQQ.setVisible(true);
								Register.this.setVisible(false);
							}
							if (judge.equals("registerFail")) {
								System.out.println("Registration failed");
								JOptionPane.showMessageDialog(jBregist, "Registration failed, please try again");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}

	public static void main(String args[]) {
		Register r = new Register();
		r.setVisible(true);
	}

}
