package cn.itbaizhan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextArea;

/* Server thread class */
public class ServerThread extends Thread {
	JTextArea jTServerLog = null;
	Boolean flag = true;
	String line_separator = System.getProperty("line.separator");
	ServerSocket server;
	/*------------------- File transfer ----------------------------*/
	Vector clients = new Vector();
	/*-----------------------------------------------------------*/

	public ServerThread(JTextArea jTServerLog) {
		this.jTServerLog = jTServerLog;
	}

	// Resume server
	public void reStartThread() {
		this.flag = true;
	}

	// Pause server
	public void pauseThread() {
		this.flag = false;
	}

	// Thread's run method
	public void run() {
		try {
			server = new ServerSocket(6544);
			jTServerLog.append("Server system initialization completed" + line_separator);
			jTServerLog.append(line_separator);
		} catch (IOException e) {
			e.printStackTrace();
			jTServerLog.append("Server port opening failed" + line_separator);
			jTServerLog.append(line_separator);
		}
		// Server system initialization completed, start thread program
		if (server != null) {
			while (flag) {
				try {
					System.out.println("Server status: " + flag);
					// Accept client's connection request
					Socket socket = server.accept();
					jTServerLog.append("****************************" + line_separator);
					jTServerLog.append("Connection accept : " + socket + line_separator);
					Date time = new java.util.Date();
					SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd kk:mm:ss");
					String timeInfo = format.format(time);
					jTServerLog.append("Connection time : " + timeInfo + line_separator);
					jTServerLog.append("****************************" + line_separator);
					// Create socket to communicate with client
					new Thread(new Server(socket)).start();
				} catch (IOException e) {
					e.printStackTrace();
					jTServerLog.append("Client connection failed" + line_separator);
					jTServerLog.append(line_separator);
				}
			}
		}
	}
}
