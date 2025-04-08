package cn.itbaizhan.client;
// Get QQ number
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckQQ extends JFrame {
	HomePage enter;
	String userNum;
	JLabel jLtop = new JLabel(new ImageIcon("src/file/checkQQ1.jpg"));
	JLabel jLeft = new JLabel(new ImageIcon("src/file/checkQQ2.jpg"));
	JLabel jLgetqq = new JLabel(new ImageIcon("src/file/checkGet.jpg"));
	JLabel jLtext1 = new JLabel("Registration Successful");
	JLabel jLtext2 = new JLabel("Your QQ number:");
	JLabel jLqq = new JLabel();
	JButton jBenter = new JButton("Login We_Talk");

	public CheckQQ(String userNum) {
		this.userNum = userNum;
		this.setSize(600, 389);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("We_Talk Registration");
		init();
		this.add(jLeft);
		this.add(jLtop);
		this.add(jLgetqq);
		this.add(jLtext1);
		this.add(jLtext2);
		this.add(jLqq);
		this.add(jBenter);
	}

	public void init() {
		jLtop.setBounds(0, 0, 600, 59);
		jLeft.setBounds(0, 56, 201, 330);
		jLgetqq.setBounds(220, 85, 45, 43);
		jLtext1.setForeground(Color.BLACK);
		jLtext1.setFont(new Font("SimSun", Font.BOLD, 24));
		jLtext1.setBounds(285, 60, 220, 50);
		jLtext2.setForeground(Color.BLACK);
		jLtext2.setFont(new Font("SimSun", Font.BOLD, 24));
		jLtext2.setBounds(285, 100, 150, 50);
		jLqq.setText(userNum);
		jLqq.setForeground(Color.RED);
		jLqq.setFont(new Font("SimSun", Font.BOLD, 24));
		jLqq.setBounds(435, 100, 165, 50);
		jBenter.setForeground(Color.WHITE);
		jBenter.setBackground(Color.GREEN);
		jBenter.setFont(new Font("SimSun", Font.BOLD, 24));
		jBenter.setBounds(285, 200, 120, 50);
		jBenter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckQQ.this.setVisible(false);
				enter = new HomePage();
				enter.setVisible(true);
			}
		});
	}
}
