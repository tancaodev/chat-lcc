package cn.itbaizhan.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.itbaizhan.common.*;

// Modify personal information
public class ChangeMyInfo extends JDialog {
	JPanel jp1 = new JPanel(); // Top panel for information display
	JPanel jp2 = new JPanel(); // Middle panel
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	JPanel jp5 = new JPanel();
	JPanel jp6 = new JPanel();
	JLabel jLmyPortrait = new JLabel(new ImageIcon("src/file/personelView1.jpg"));
	JButton jBchange = new JButton(new ImageIcon("src/file/CMchange.jpg")); // 77*18
	JLabel jLmyName = new JLabel("Sweety");
	JLabel jLmyNum = new JLabel("593253716");
	JLabel jLmySign = new JLabel("Hello, I'm a cute girl who loves to learn. Please call me Sweety~~~");
	JLabel jLmyother = new JLabel("Female 20 years old Chengdu");
	JButton jBsave = new JButton(new ImageIcon("src/file/CMsave.jpg")); // 63*19
	JButton jBclose = new JButton(new ImageIcon("src/file/CMclose.jpg")); // 62*19
	JLabel jLsign = new JLabel("Personal Signature:");
	JTextArea jTsign = new JTextArea("Hello, I'm a cute girl who loves to learn. Please call me Sweety~~~");
	JLabel jLName = new JLabel("Nickname:");
	JTextField jTname = new JTextField("Sweety");
	JLabel jLenglishName = new JLabel("English Name:");
	JTextField jTenglishName = new JTextField("Sweety");
	JLabel jLsex = new JLabel("Gender:");
	String sex[] = { "Male", "Female" };
	JComboBox jTsex = new JComboBox(sex);
	JLabel jLblood = new JLabel("Blood Type:");
	String blood[] = { "A", "B", "AB", "O", "Unknown" };
	JComboBox jTblood = new JComboBox(blood);
	JLabel jLbirth = new JLabel("Birthday:");
	String timeT[] = { "Gregorian", "Lunar" };
	JLabel jyear = new JLabel("Year");
	JLabel jmonth = new JLabel("Month");
	JLabel jday = new JLabel("Day");
	JComboBox timeType = new JComboBox(timeT);
	DefaultComboBoxModel yearModel = new DefaultComboBoxModel();
	DefaultComboBoxModel monthModel = new DefaultComboBoxModel();
	DefaultComboBoxModel dayModel = new DefaultComboBoxModel();
	JComboBox year = new JComboBox();
	JComboBox month = new JComboBox();
	JComboBox day = new JComboBox();
	JLabel jLaddress = new JLabel("Current Location:");
	JTextField jTaddress = new JTextField("Sichuan Chengdu");
	JLabel jLplace = new JLabel("Address:");
	JTextField jTplace = new JTextField("Sichuan Chengdu");
	BufferedReader in; // Input stream
	PrintStream out; // Output stream
	UserBean myInfo = null;
	PersonelView father = null;
	String age = null;
	String sign = null;
	String name = null;
	String mySex = null;
	String birth = null;
	String address = null;
	String type, myear, mymonth, myday;

	public ChangeMyInfo(JFrame owner, String title, Boolean b, BufferedReader in, PrintStream out, UserBean myInfo,
			PersonelView father) {
		super(owner, title, b);
		this.in = in;
		this.out = out;
		this.myInfo = myInfo;
		this.father = father;
		sign = myInfo.getSign();
		name = myInfo.getUserName();
		mySex = myInfo.getSex();
		birth = myInfo.getBirth();
		address = myInfo.getAddress();
		init();
		this.setSize(455, 540);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jBsave);
		this.add(jBclose);
		this.add(jLsign);
		this.add(jTsign);
		this.add(jLName);
		this.add(jTname);
		this.add(jLenglishName);
		this.add(jTenglishName);
		this.add(jLsex);
		this.add(jTsex);
		this.add(jLblood);
		this.add(jTblood);
		this.add(jLbirth);
		this.add(timeType);
		this.add(year);
		this.add(jyear);
		this.add(month);
		this.add(jmonth);
		this.add(day);
		this.add(jday);
		this.add(jLaddress);
		this.add(jTaddress);
		this.add(jLplace);
		this.add(jTplace);
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
		jp1.setLayout(null);
		jp1.setBackground(Color.PINK);
		jp1.setBounds(0, 0, 455, 120);
		jLmyPortrait.setBounds(10, 30, 60, 60);
		jBchange.setBounds(5, 100, 77, 18);
		jBchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeMyInfo.this.setVisible(false);
				father.changHead();
			}
		});
		jLmyName.setForeground(Color.WHITE);
		jLmyName.setFont(new Font("SimSun", Font.BOLD, 24));
		jLmyName.setBounds(80, 25, 150, 25);
		jLmyNum.setForeground(Color.WHITE);
		jLmyNum.setFont(new Font("SimSun", Font.PLAIN, 18));
		jLmyNum.setBounds(230, 27, 150, 23);
		jLmySign.setForeground(Color.WHITE);
		jLmySign.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLmySign.setBounds(80, 60, 370, 25);
		jLmyother.setForeground(Color.WHITE);
		jLmyother.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLmyother.setBounds(100, 90, 150, 25);
		jp1.add(jLmyPortrait);
		jp1.add(jBchange);
		jp1.add(jLmyName);
		jp1.add(jLmyNum);
		jp1.add(jLmySign);
		jp1.add(jLmyother);

		// Middle panel buttons
		jp2.setBounds(0, 126, 250, 19);
		jp2.setBackground(Color.WHITE);
		jp3.setBackground(Color.WHITE);
		jp4.setBackground(Color.WHITE);
		jp5.setBackground(Color.WHITE);
		jp6.setBackground(Color.WHITE);
		jp3.setBounds(0, 120, 455, 6);
		jp4.setBounds(313, 126, 20, 19);
		jp5.setBounds(0, 145, 455, 10);
		jp6.setBounds(395, 126, 60, 19);
		jBsave.setSize(63, 19);
		jBsave.setLocation(250, 126);
		jBsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (type != null && myear != null && mymonth != null && myday != null) {
					birth = type + "-" + myear + "Year" + mymonth + "Month" + myday + "Day";
				}
				sign = jTsign.getText();
				name = jTname.getText();
				address = jTaddress.getText();
				out.println("updateOwnInformation");
				out.flush();
				out.println(myInfo.getUserNum());
				out.flush();
				out.println(name);
				out.flush();
				out.println(mySex);
				out.flush();
				out.println(birth);
				out.flush();
				out.println(address);
				out.flush();
				out.println(sign);
				out.flush();
				try {
					String judge = in.readLine();
					if (judge.equals("updateOver")) {
						ChangeMyInfo.this.setVisible(false);
						System.out.println("Update successful");
					} else if (judge.equals("updateFail")) {
						ChangeMyInfo.this.setVisible(false);
						JOptionPane.showConfirmDialog(father, "System busy, please try again later");
						System.out.println("System busy");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		jBclose.setSize(62, 19);
		jBclose.setLocation(333, 126);
		jBclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeMyInfo.this.setVisible(false);
			}
		});

		// Modify information section
		jLsign.setForeground(Color.BLACK);
		jLsign.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLsign.setBounds(10, 165, 100, 25);
		jTsign.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTsign.setForeground(Color.BLACK);
		jTsign.setBackground(Color.WHITE);
		jTsign.setBounds(10, 200, 415, 40);
		jTsign.setLineWrap(true); // Auto wrap
		jLName.setForeground(Color.BLACK);
		jLName.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLName.setBounds(10, 260, 45, 25);
		jTname.setForeground(Color.BLACK);
		jTname.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTname.setBackground(Color.WHITE);
		jTname.setBounds(55, 261, 130, 22);
		jLenglishName.setForeground(Color.BLACK);
		jLenglishName.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLenglishName.setBounds(215, 260, 65, 25);
		jTenglishName.setForeground(Color.BLACK);
		jTenglishName.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTenglishName.setBackground(Color.WHITE);
		jTenglishName.setBounds(280, 261, 130, 22);
		jLsex.setForeground(Color.BLACK);
		jLsex.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLsex.setBounds(10, 300, 45, 25);
		jTsex.setForeground(Color.BLACK);
		jTsex.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTsex.setBackground(Color.WHITE);
		jTsex.setBounds(55, 301, 130, 22);
		jTsex.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				mySex = jTsex.getSelectedItem().toString();
			}
		});
		jLblood.setForeground(Color.BLACK);
		jLblood.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLblood.setBounds(215, 300, 65, 25);
		jTblood.setForeground(Color.BLACK);
		jTblood.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTblood.setBackground(Color.WHITE);
		jTblood.setBounds(280, 301, 130, 22);
		jLbirth.setForeground(Color.BLACK);
		jLbirth.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLbirth.setBounds(10, 340, 45, 25);
		timeType.setForeground(Color.BLACK);
		timeType.setFont(new Font("SimSun", Font.PLAIN, 14));
		timeType.setBackground(Color.WHITE);
		timeType.setBounds(55, 341, 60, 22);
		timeType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				type = timeType.getSelectedItem().toString();
			}
		});
		year.setForeground(Color.BLACK);
		year.setFont(new Font("SimSun", Font.PLAIN, 14));
		year.setBackground(Color.WHITE);
		year.setBounds(120, 341, 60, 22);
		year.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				myear = year.getSelectedItem().toString();
			}
		});
		jyear.setForeground(Color.BLACK);
		jyear.setFont(new Font("SimSun", Font.PLAIN, 14));
		jyear.setBounds(182, 341, 20, 22);
		month.setForeground(Color.BLACK);
		month.setFont(new Font("SimSun", Font.PLAIN, 14));
		month.setBackground(Color.WHITE);
		month.setBounds(205, 341, 60, 22);
		month.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				mymonth = month.getSelectedItem().toString();
			}
		});
		jmonth.setForeground(Color.BLACK);
		jmonth.setFont(new Font("SimSun", Font.PLAIN, 14));
		jmonth.setBounds(267, 341, 20, 22);
		day.setForeground(Color.BLACK);
		day.setFont(new Font("SimSun", Font.PLAIN, 14));
		day.setBackground(Color.WHITE);
		day.setBounds(290, 341, 60, 22);
		day.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				myday = day.getSelectedItem().toString();
			}
		});
		jday.setForeground(Color.BLACK);
		jday.setFont(new Font("SimSun", Font.PLAIN, 14));
		jday.setBounds(352, 341, 20, 22);
		jLaddress.setForeground(Color.BLACK);
		jLaddress.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLaddress.setBounds(10, 380, 65, 25);
		jTaddress.setForeground(Color.BLACK);
		jTaddress.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTaddress.setBackground(Color.WHITE);
		jTaddress.setBounds(75, 381, 340, 22);
		jLplace.setForeground(Color.BLACK);
		jLplace.setFont(new Font("SimSun", Font.PLAIN, 14));
		jLplace.setBounds(10, 420, 65, 25);
		jTplace.setForeground(Color.BLACK);
		jTplace.setFont(new Font("SimSun", Font.PLAIN, 14));
		jTplace.setBackground(Color.WHITE);
		jTplace.setBounds(75, 421, 340, 22);
		jLmyPortrait.setIcon(new ImageIcon(myInfo.getPortrait()));
		jLmyName.setText(myInfo.getUserName());
		jLmyNum.setText(myInfo.getUserNum());
		jLmySign.setText(myInfo.getSign());
		try {
			String birthStr = myInfo.getBirth();
			if (birthStr != null && !birthStr.isEmpty()) {
				int yearIndex = birthStr.indexOf("Year");
				int dashIndex = birthStr.indexOf("-");
				if (yearIndex != -1 && dashIndex != -1) {
					String yearStr = birthStr.substring(dashIndex + 1, yearIndex);
					age = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(yearStr.trim()));
				} else {
					age = "Unknown";
				}
			} else {
				age = "Unknown";
			}
		} catch (Exception e) {
			age = "Unknown";
		}
		jLmyother.setText(myInfo.getSex() + " " + age + " years old " + myInfo.getAddress());
		jTsign.setText(myInfo.getSign());
		jTname.setText(myInfo.getUserName());
		jTaddress.setText(myInfo.getAddress());
		jTplace.setText(myInfo.getAddress());
	}
}
