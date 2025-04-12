package cn.itbaizhan.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/* User information display module */
public class UserInfo extends JDialog {
	JLabel jLtitlepic = new JLabel(new ImageIcon("src/file/showInfo1.jpg"));
	JLabel jLtitlename = new JLabel("User Information");
	JLabel jLhead = new JLabel(new ImageIcon("src/file/1-1.jpg")); // Display avatar
	JLabel jLname_num = new JLabel();
	JLabel jLqqage = new JLabel("Account Age: ");
	JLabel jLrank = new JLabel("Level: ");
	JLabel jLrankPic = new JLabel(new ImageIcon("src/file/rank.jpg"));
	JLabel jLqq_age = new JLabel("---");
	JLabel jLrealName = new JLabel("Real Name: -");
	JLabel jLengName = new JLabel("English Name: -");
	JLabel jLcomment = new JLabel("Remark: -");
	JLabel jLsign = new JLabel("Personal Signature: ");
	JLabel jLsex = new JLabel("Gender: ");
	JLabel jLage = new JLabel("Age: ");
	JLabel jLbirth = new JLabel("Birthday: ");
	JLabel jLconstellation = new JLabel("Constellation: Leo");
	JLabel jLlocation = new JLabel("Address: ");
	JLabel jLaddress = new JLabel("Current Location: ");
	JLabel jLmail = new JLabel("Email: -");
	UserBean user = null;

	public UserInfo(Frame info, String title, boolean b, UserBean user) {
		super(info, title, b);
		this.setSize(650, 500);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.user = user;
		init();
		this.add(jLtitlename);
		this.add(jLtitlepic);
		this.add(jLhead);
		this.add(jLname_num);
		this.add(jLqqage);
		this.add(jLqq_age);
		this.add(jLrank);
		this.add(jLrankPic);
		this.add(jLrealName);
		this.add(jLengName);
		this.add(jLcomment);
		this.add(jLsign);
		this.add(jLsex);
		this.add(jLage);
		this.add(jLbirth);
		this.add(jLconstellation);
		this.add(jLlocation);
		this.add(jLaddress);
		this.add(jLmail);
		getInfo();
	}

	public void init() {
		jLtitlepic.setBounds(5, 5, 20, 18);
		jLtitlename.setFont(new Font("Arial", Font.BOLD, 14));
		jLtitlename.setBounds(30, 2, 300, 25);
		jLhead.setBounds(15, 40, 96, 93);
		jLname_num.setFont(new Font("Arial", Font.PLAIN, 13));
		jLname_num.setForeground(Color.BLACK);
		jLname_num.setBounds(150, 50, 300, 25);
		jLqqage.setFont(new Font("Arial", Font.PLAIN, 13));
		jLqqage.setForeground(Color.BLACK);
		jLqqage.setBounds(150, 80, 100, 25);
		jLqq_age.setFont(new Font("Arial", Font.PLAIN, 13));
		jLqq_age.setForeground(Color.BLACK);
		jLqq_age.setBounds(250, 80, 50, 25);
		jLrank.setFont(new Font("Arial", Font.PLAIN, 13));
		jLrank.setForeground(Color.BLACK);
		jLrank.setBounds(320, 80, 100, 25);
		jLrankPic.setBounds(420, 84, 15, 17);
		jLrealName.setFont(new Font("Arial", Font.PLAIN, 13));
		jLrealName.setForeground(Color.BLACK);
		jLrealName.setBounds(150, 110, 200, 25);
		jLengName.setFont(new Font("Arial", Font.PLAIN, 13));
		jLengName.setForeground(Color.BLACK);
		jLengName.setBounds(360, 110, 200, 25);
		jLcomment.setFont(new Font("Arial", Font.PLAIN, 13));
		jLcomment.setForeground(Color.BLACK);
		jLcomment.setBounds(150, 140, 200, 25);
		jLsign.setFont(new Font("Arial", Font.PLAIN, 13));
		jLsign.setForeground(Color.BLACK);
		jLsign.setBounds(150, 170, 450, 25);
		jLsex.setFont(new Font("Arial", Font.PLAIN, 13));
		jLsex.setForeground(Color.BLACK);
		jLsex.setBounds(150, 230, 100, 25);
		jLage.setFont(new Font("Arial", Font.PLAIN, 13));
		jLage.setForeground(Color.BLACK);
		jLage.setBounds(260, 230, 100, 25);
		jLbirth.setFont(new Font("Arial", Font.PLAIN, 13));
		jLbirth.setForeground(Color.BLACK);
		jLbirth.setBounds(370, 230, 250, 25);
		jLconstellation.setFont(new Font("Arial", Font.PLAIN, 13));
		jLconstellation.setForeground(Color.BLACK);
		jLconstellation.setBounds(150, 260, 250, 25);
		jLlocation.setFont(new Font("Arial", Font.PLAIN, 13));
		jLlocation.setForeground(Color.BLACK);
		jLlocation.setBounds(150, 290, 450, 25);
		jLaddress.setFont(new Font("Arial", Font.PLAIN, 13));
		jLaddress.setForeground(Color.BLACK);
		jLaddress.setBounds(150, 320, 450, 25);
		jLmail.setFont(new Font("Arial", Font.PLAIN, 13));
		jLmail.setForeground(Color.BLACK);
		jLmail.setBounds(150, 350, 450, 25);
	}

	public void getInfo() {
		String age;
		int month;
		String constellation = null;
		jLtitlename.setText(user.getUserName().trim() + "'s Information");
		jLname_num.setText(user.getUserName() + " " + user.getUserNum());
		jLsign.setText("Personal Signature: " + user.getSign());
		jLsex.setText("Gender: " + user.getSex());
		jLbirth.setText("Birthday: " + user.getBirth());
		jLaddress.setText("Current Location: " + user.getAddress());
		jLlocation.setText("Address: " + user.getAddress());
		jLhead.setIcon(new ImageIcon(user.getPortrait()));

		// Lấy năm hiện tại
		int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

		// Lấy năm sinh từ chuỗi ngày sinh (định dạng: 2001-03-18)
		String birthDate = user.getBirth();
		int birthYear = Integer.parseInt(birthDate.substring(0, 4));
		age = String.valueOf(currentYear - birthYear);

		// Lấy tháng sinh
		month = Integer.parseInt(birthDate.substring(5, 7));

		switch (month) {
			case 1:
				constellation = "Capricorn";
				break;
			case 2:
				constellation = "Aquarius";
				break;
			case 3:
				constellation = "Pisces";
				break;
			case 4:
				constellation = "Aries";
				break;
			case 5:
				constellation = "Taurus";
				break;
			case 6:
				constellation = "Gemini";
				break;
			case 7:
				constellation = "Cancer";
				break;
			case 8:
				constellation = "Leo";
				break;
			case 9:
				constellation = "Virgo";
				break;
			case 10:
				constellation = "Libra";
				break;
			case 11:
				constellation = "Scorpio";
				break;
			case 12:
				constellation = "Sagittarius";
				break;
		}
		jLconstellation.setText("Constellation: " + constellation);
		jLage.setText("Age: " + age);
	}
}
