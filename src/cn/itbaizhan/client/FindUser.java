package cn.itbaizhan.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import cn.itbaizhan.common.*;

/*--------------- Client side user search and information display --------------------*/
public class FindUser extends JDialog {
	JLabel jLtitlepic = new JLabel(new ImageIcon("src/file/showInfo1.jpg"));
	JLabel jLtitlename = new JLabel("My Information");
	JLabel jLhead = new JLabel(new ImageIcon("src/file/1-1.jpg"));// ʾͷ��
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
	JLabel jLcomment = new JLabel("Comment: -");
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
	ImageIcon add = new ImageIcon("src/file/FUaddUser.jpg");// 64*23
	ImageIcon close = new ImageIcon("src/file/FUclose.jpg");// 65*21
	JButton jBclose = new JButton(close);
	JButton jBaddUser = new JButton(add);
	UserBean user = null;
	PersonelView father = null;

	public FindUser(Frame info, String title, boolean b,
			UserBean user, PersonelView father) {
		super(info, title, b);
		this.setSize(650, 500); // Tăng kích thước cửa sổ
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.user = user;
		this.father = father;
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
		this.add(jBaddUser);
		this.add(jBclose);
		getInfo();

	}

	public void init() {
		jLtitlepic.setBounds(5, 5, 20, 18);
		jLtitlename.setFont(new Font("Arial", Font.BOLD, 14));
		jLtitlename.setBounds(30, 2, 300, 25);
		jLhead.setBounds(15, 40, 96, 93);
		jLinfo5.setBounds(15, 40, 96, 93);
		jLchageHead.setBounds(0, 133, 134, 284);
		info1.setBounds(111, 0, 23, 133);
		info2.setBounds(0, 40, 15, 93);
		info3.setBounds(0, 0, 111, 40);
		info4.setBounds(133, 0, 432, 40);
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
		jLshowInfo5.setBounds(111, 200, 454, 20);
		jLsex.setFont(new Font("Arial", Font.PLAIN, 13));
		jLsex.setForeground(Color.BLACK);
		jLsex.setBounds(150, 230, 100, 25);
		jLage.setFont(new Font("Arial", Font.PLAIN, 13));
		jLage.setForeground(Color.BLACK);
		jLage.setBounds(260, 230, 100, 25);
		jLbirth.setFont(new Font("Arial", Font.PLAIN, 13));
		jLbirth.setForeground(Color.BLACK);
		jLbirth.setBounds(370, 230, 250, 25);
		jLblood.setFont(new Font("Arial", Font.PLAIN, 13));
		jLblood.setForeground(Color.BLACK);
		jLblood.setBounds(150, 260, 100, 25);
		jLanimal.setFont(new Font("Arial", Font.PLAIN, 13));
		jLanimal.setForeground(Color.BLACK);
		jLanimal.setBounds(260, 260, 100, 25);
		jLconstellation.setFont(new Font("Arial", Font.PLAIN, 13));
		jLconstellation.setForeground(Color.BLACK);
		jLconstellation.setBounds(370, 260, 250, 25);
		jLlocation.setFont(new Font("Arial", Font.PLAIN, 13));
		jLlocation.setForeground(Color.BLACK);
		jLlocation.setBounds(150, 290, 450, 25);
		jLaddress.setFont(new Font("Arial", Font.PLAIN, 13));
		jLaddress.setForeground(Color.BLACK);
		jLaddress.setBounds(150, 320, 450, 25);
		jLmail.setFont(new Font("Arial", Font.PLAIN, 13));
		jLmail.setForeground(Color.BLACK);
		jLmail.setBounds(150, 350, 450, 25);
		jBaddUser.setBounds(470, 420, 64, 23);
		jBclose.setBounds(550, 420, 65, 21);
		jBaddUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				father.addUser();
				FindUser.this.setVisible(false);
			}

		});
		jBclose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FindUser.this.setVisible(false);
			}

		});
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

		// Calculate age from YYYY-MM-DD format
		String birthDate = user.getBirth();
		if (birthDate != null && !birthDate.isEmpty()) {
			try {
				int birthYear = Integer.parseInt(birthDate.substring(0, 4));
				// Get current year dynamically
				int currentYear = java.time.Year.now().getValue();
				age = String.valueOf(currentYear - birthYear);
				jLage.setText("Age: " + age);

				// Get month for constellation
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
			} catch (Exception e) {
				jLage.setText("Age: Unknown");
				jLconstellation.setText("Constellation: Unknown");
			}
		} else {
			jLage.setText("Age: Unknown");
			jLconstellation.setText("Constellation: Unknown");
		}
	}
}
