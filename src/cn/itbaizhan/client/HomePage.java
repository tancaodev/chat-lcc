package cn.itbaizhan.client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/* Login Module */
public class HomePage extends JFrame {
	JLabel jLtitle = new JLabel(new ImageIcon("src/file/title.jpg"));
	JLabel jLhead = new JLabel(new ImageIcon("src/file/1-1.jpg"));
	JTextField jTusernumber = new JTextField(); // Account input
	JPasswordField jPassword = new JPasswordField(); // Password input
	JCheckBox rememberPass = new JCheckBox("Remember Password");
	JCheckBox Autologon = new JCheckBox("Auto Login", true);
	JButton jBmore = new JButton(">More");
	JButton jBset = new JButton("Settings");
	JButton jBenter = new JButton("Login");
	Register register;
	PersonelView personelView;
	HomePage h;
	Boolean pass = true; // Used to identify whether login is successful
	HomePage enter;

	public HomePage() {
		this.setSize(380, 290);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("We_Talk");
		this.add(jLtitle);
		this.add(jLhead);
		this.add(jTusernumber);
		this.add(jPassword);
		this.add(Autologon);
		this.add(rememberPass);
		this.add(jBmore);
		this.add(jBset);
		this.add(jBenter);
		init();
	}

	public void loginSuccess() {
		pass = true;
	}

	public void loginFail() {
		pass = false;
	}

	public void init() {
		jLtitle.setBounds(0, 0, 380, 105);
		jTusernumber.setBounds(120, 110, 160, 28);
		jPassword.setBounds(120, 145, 160, 28);
		rememberPass.setFont(new Font("SimSun", Font.PLAIN, 10));
		rememberPass.setForeground(Color.BLACK);
		rememberPass.setBounds(148, 175, 70, 25);
		Autologon.setFont(new Font("SimSun", Font.PLAIN, 10));
		Autologon.setForeground(Color.BLACK);
		Autologon.setBounds(220, 175, 70, 25);
		jLhead.setBounds(20, 105, 87, 90);
		jBmore.setFont(new Font("SimSun", Font.PLAIN, 10));
		jBmore.setForeground(Color.BLACK);
		jBmore.setBounds(10, 225, 70, 23);
		jBset.setFont(new Font("SimSun", Font.PLAIN, 10));
		jBset.setBounds(95, 225, 55, 23);
		jBenter.setFont(new Font("SimSun", Font.PLAIN, 10));
		jBenter.setBounds(300, 225, 55, 23);
		jBenter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = jTusernumber.getText().trim();
				String userPass = String.valueOf(jPassword.getPassword()).trim();
				InetAddress ip = null;
				int port = 0;
				if (userName.equals("")) {
					JOptionPane.showMessageDialog(jBenter, "Please enter your account number and login again");
					jTusernumber.requestFocus();
				} else if (userPass.equals("")) {
					JOptionPane.showMessageDialog(jBenter, "Please enter your password and login again");
					jPassword.requestFocus();
				} else {
					HomePage.this.setVisible(false);
					try {
						ip = InetAddress.getByName("127.0.0.1");
						port = (6544);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					personelView = new PersonelView(userName, userPass, HomePage.this, ip, port);
				}
				if (!pass) {
					personelView.setVisible(false);
					JOptionPane.showMessageDialog(null, "The account number and password do not match, please re-enter");
					if (enter == null) {
						enter = new HomePage();
					}
					enter.setVisible(true);
				}
			}
		});

		// Register account label as a hyperlink
		class JlRegist extends JLabel {
			private boolean isSupported;
			private String regist;

			public JlRegist(String regist) {
				this.regist = regist;
				try {
					this.isSupported = Desktop.isDesktopSupported()
							&& Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
				} catch (Exception e) {
					this.isSupported = false;
				}
				setText(false);
				addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						setText(isSupported);
						if (isSupported)
							setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					public void mouseExited(MouseEvent e) {
						setText(false);
					}

					public void mouseClicked(MouseEvent e) {
						if (register == null) {
							register = new Register();
							register.setVisible(true);
							HomePage.this.setVisible(false);
						}
					}
				});
			}

			private void setText(boolean b) {
				if (!b)
					setText(regist);
				else
					setText("<html><font color=blue><u>" + regist);
			}
		}
		JlRegist jLRegist = new JlRegist("Register Account");
		jLRegist.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLRegist.setForeground(Color.BLUE);
		jLRegist.setBounds(283, 110, 60, 28);
		this.add(jLRegist);

		// Find password label as a hyperlink
		class JlFindPass extends JLabel {
			private boolean isSupported;
			private String findPass;

			public JlFindPass(String findPass) {
				this.findPass = findPass;
				try {
					this.isSupported = Desktop.isDesktopSupported()
							&& Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
				} catch (Exception e) {
					this.isSupported = false;
				}
				setText(false);
				addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						setText(isSupported);
						if (isSupported)
							setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					public void mouseExited(MouseEvent e) {
						setText(false);
					}

					public void mouseClicked(MouseEvent e) {
						try {
							Desktop.getDesktop().browse(
									new java.net.URI("https://aq.qq.com/cn2/findpsw/pc/pc_find_pwd_input_account?source_id=1003&ptlang=2052&aquin=593253716"));
						} catch (Exception ex) {
						}
					}
				});
			}

			private void setText(boolean b) {
				if (!b)
					setText(findPass);
				else
					setText("<html><font color=blue><u>" + findPass);
			}
		}
		JlFindPass jLfindPass = new JlFindPass("Find Password");
		jLfindPass.setFont(new Font("SimSun", Font.PLAIN, 13));
		jLfindPass.setForeground(Color.BLUE);
		jLfindPass.setBounds(283, 145, 60, 28);
		this.add(jLfindPass);
	}

	public static void main(String args[]) {
		HomePage enter = new HomePage();
		enter.setVisible(true);
	}
}
