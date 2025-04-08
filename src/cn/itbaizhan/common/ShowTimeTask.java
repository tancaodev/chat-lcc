package cn.itbaizhan.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

// Functionality for displaying time
public class ShowTimeTask extends java.util.TimerTask {
    private JLabel showTime = null;

    public ShowTimeTask(JLabel showTime) {
        this.showTime = showTime;
    }

    public void run() {
        Date time = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String timeInfo = format.format(time);
        showTime.setText("Current time: " + timeInfo + "    ");
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}