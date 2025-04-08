package cn.itbaizhan.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/* User information display module */
public class UserInfo extends JDialog {
	JLabel jLtitlepic = new JLabel(new ImageIcon("src/file/showInfo1.jpg"));
	JLabel jLtitlename = new JLabel("My Information");
	JLabel jLhead = new JLabel(new ImageIcon("src/file/1-1.jpg")); // Display avatar
	JLabel jLinfo5 = new JLabel(new ImageIcon("src/file/info5.jpg"));
	JLabel jLchageHead = new JLabel(new ImageIcon("src/file/head.jpg"));
	JLabel info1 = new JLabel(new ImageIcon("src/file/info1.jpg"));
	JLabel info2 = new JLabel(new ImageIcon("src/file/info1.jpg"));
	JLabel info3 = new JLabel(new ImageIcon("src/file/info3.jpg"));
	JLabel info4 = new JLabel(new ImageIcon("src/file/info4.jpg"));
	JLabel jLname_num = new JLabel();
	JLabel jLqqage = new JLabel("We_Talk Age: ");
	JLabel jLrank = new JLabel("Level: ");
	JLabel jLrankPic = new JLabel(new ImageIcon("src/file/rank.jpg"));
	JLabel jLqq_age = new JLabel("---");
	JLabel jLrealName = new JLabel("Real Name: -");
	JLabel jLengName = new JLabel("English Name: -");
	JLabel jLcomment = new JLabel("Remark: -");
	JLabel jLsign = new JLabel("Personal Signature: ");
	JLabel jLshowInfo5 = new JLabel(new ImageIcon("src/file/1.jpg"));
	JLabel jLsex = new JLabel("Gender: ");
	JLabel jLage = new JLabel("Age: ");
	JLabel jLbirth = new JLabel("Birthday: ");
	JLabel jLblood = new JLabel("Blood Type: O");
	JLabel jLanimal = new JLabel("Zodiac: Dragon");
	JLabel jLconstellation = new JLabel("Constellation: Leo");
	JLabel jLlocation = new JLabel("Address: ");
	JLabel jLaddress = new JLabel("Current Location: ");
	JLabel jLmail = new JLabel("Email: -");
	UserBean user = null;

	public UserInfo(Frame info, String title, boolean b, UserBean user) {
		super(info, title, b);
		this.setSize(555, 417);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.user = user;
		init();
		this.add(jLtitlename);
		this.add(jLtitlepic);
		this.add(jLhead);
		this.add(jLhead);
		this.add(jLchageHead);
		this.add(info1);
		this.add(info2);
		this.add(info3);
		this.add(info4);
		this.add(jLname_num);
		this.add(jLqqage);
		this.add(jLqq_age);
		this.add(jLrank);
		this.add(jLrankPic);
		this.add(jLrealName);
		this.add(jLengName);
		this.add(jLcomment);
		this.add(jLsign);
		this.add(jLshowInfo5);
		this.add(jLsex);
		this.add(jLage);
		this.add(jLbirth);
		this.add(jLblood);
		this.add(jLanimal);
		this.add(jLconstellation);
		this.add(jLlocation);
		this.add(jLaddress);
		this.add(jLmail);
		this.add(jLinfo5);
		getInfo();
	}

	public void init() {
		jLtitlepic.setBounds(5, 5, 20, 18);
		jLtitlename.setFont(new Font("SimSun", Font.BOLD, 11));
		jLtitlename.setBounds(30, 2, 200, 25);
		jLhead.setBounds(15, 40, 96, 93);
		jLinfo5.setBounds(15, 40, 96, 93);
		jLchageHead.setBounds(0, 133, 134, 284);
		info1.setBounds(111, 0, 23, 133);
		info2.setBounds(0, 40, 15, 93);
		info3.setBounds(0, 0, 111, 40);
		info4.setBounds(133, 0, 432, 40);
		jLname_num.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLname_num.setForeground(Color.BLACK);
		jLname_num.setBounds(150, 50, 200, 25);
		jLqqage.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLqqage.setForeground(Color.BLACK);
		jLqqage.setBounds(150, 80, 60, 25);
		jLqq_age.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLqq_age.setForeground(Color.BLACK);
		jLqq_age.setBounds(210, 80, 30, 25);
		jLrank.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLrank.setForeground(Color.BLACK);
		jLrank.setBounds(280, 80, 65, 25);
		jLrankPic.setBounds(350, 84, 15, 17);
		jLrealName.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLrealName.setForeground(Color.BLACK);
		jLrealName.setBounds(150, 110, 130, 25);
		jLengName.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLengName.setForeground(Color.BLACK);
		jLengName.setBounds(280, 110, 130, 25);
		jLcomment.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLcomment.setForeground(Color.BLACK);
		jLcomment.setBounds(150, 140, 130, 25);
		jLsign.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLsign.setForeground(Color.BLACK);
		jLsign.setBounds(150, 170, 405, 25);
		jLshowInfo5.setBounds(111, 200, 454, 20);
		jLsex.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLsex.setForeground(Color.BLACK);
		jLsex.setBounds(150, 230, 80, 25);
		jLage.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLage.setForeground(Color.BLACK);
		jLage.setBounds(250, 230, 70, 25);
		jLbirth.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLbirth.setForeground(Color.BLACK);
		jLbirth.setBounds(340, 230, 215, 25);
		jLblood.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLblood.setForeground(Color.BLACK);
		jLblood.setBounds(150, 260, 80, 25);
		jLanimal.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLanimal.setForeground(Color.BLACK);
		jLanimal.setBounds(250, 260, 70, 25);
		jLconstellation.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLconstellation.setForeground(Color.BLACK);
		jLconstellation.setBounds(340, 260, 215, 25);
		jLlocation.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLlocation.setForeground(Color.BLACK);
		jLlocation.setBounds(150, 290, 405, 25);
		jLaddress.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLaddress.setForeground(Color.BLACK);
		jLaddress.setBounds(150, 320, 405, 25);
		jLmail.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLmail.setForeground(Color.BLACK);
		jLmail.setBounds(150, 350, 405, 25);
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
		age = String.valueOf((2012 - Integer.valueOf(user.getBirth().substring(user.getBirth().indexOf("-") + 1, user.getBirth().indexOf("Year")))));
		System.out.println(age);
		jLage.setText("Age: " + age);
		month = Integer.valueOf(user.getBirth().substring(user.getBirth().indexOf("Year") + 1, user.getBirth().indexOf("Month")));
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
	}
}
