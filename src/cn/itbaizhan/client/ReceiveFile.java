package cn.itbaizhan.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.itbaizhan.common.UserBean;

public class ReceiveFile {
	ChatView father; 
	BufferedReader in;
	PrintStream out;
	UserBean myInfo;
	UserBean currentFriend;
	String line_separator = System.getProperty("line.separator"); // Get system line separator
	public ReceiveFile(ChatView father, BufferedReader in, PrintStream out, UserBean myInfo, UserBean currentFriend)
	{
		this.father = father;
		this.in = in;
		this.out = out;
		this.myInfo = myInfo;
		this.currentFriend = currentFriend;
	}
	public void receive()
	{
		String receiveQQ = null; // Receiver's QQ number
		String sendQQ = null;
		String fileName = null;
		String judge = null;
		//String fileContent=null;
		//------------------------------------- Receive File ----------------------------------//
		Date time = new java.util.Date();
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	      String timeInfo = format.format(time);
		try {
			judge = in.readLine();
			System.out.println("Client: Receiving file " + judge);
			if(judge.equals("FILE"))
			{
			System.out.println("Client: File receiving has started");
			receiveQQ = in.readLine();
			System.out.println("Receiver's We_Talk number: " + receiveQQ);
			System.out.println("My We_Talk number: " + myInfo.getUserNum());
			System.out.println("Friend's We_Talk number: " + currentFriend.getUserNum());
			if(receiveQQ.equals(myInfo.getUserNum()))
			{
				sendQQ = in.readLine();
				System.out.println("Sender's We_Talk number: " + sendQQ);
				fileName = in.readLine();
				System.out.println("File name: " + fileName);
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(
						new FileOutputStream("src/received files/"+fileName)));
				String j;
				do
				{
					j = in.readLine();
					if(j.equals("All"))
					{
						break;
					}
					else
					{
						
						bw.write(j);
						bw.newLine();
					//	fileContent+=j+"\n";
					}
					
				}while(!j.equals("All"));
				bw.flush();
				bw.close();
				father.jTAshowChat.append(" " + sendQQ + "  " + timeInfo + line_separator);
				father.jTAshowChat.append("File has been successfully received" + line_separator + line_separator);
				father.jTAshowChat.append(line_separator);
			}
		} 
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//---------------------------------------------------------------------------------//	
	}

}
