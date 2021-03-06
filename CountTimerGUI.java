/*
 * Copyright (c) 2010-2011 Brigham Young University
 * 
 * This file is part of the BYU RapidSmith Tools.
 * 
 * BYU RapidSmith Tools is free software: you may redistribute it 
 * and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * BYU RapidSmith Tools is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License is included with the BYU 
 * RapidSmith Tools. It can be found at doc/gpl2.txt. You may also 
 * get a copy of the license at <http://www.gnu.org/licenses/>.
 * 
 */
package essential;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CountTimerGUI implements ActionListener {
	
	public boolean run = true;

    private JFrame frame;
    private JPanel panel;

    private JLabel timeLabel = new JLabel();
    private JLabel elapsedTime = new JLabel();
    private JLabel console = new JLabel();
    private JLabel injectedLabel = new JLabel();
    private JLabel faultLabel = new JLabel();
    private JLabel percLabel = new JLabel();
    private JLabel progressLabel = new JLabel();
    private JButton startBtn = new JButton("Start");
    private JButton pauseBtn = new JButton("Pause");
    private JButton stopBtn = new JButton("Stop");
    
    private String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
    
    private Object[][] data = {
    	    {"Kathy", "Smith",
    	     "Snowboarding", new Integer(5), new Boolean(false)},
    	    {"John", "Doe",
    	     "Rowing", new Integer(3), new Boolean(true)},
    	    {"Sue", "Black",
    	     "Knitting", new Integer(2), new Boolean(false)},
    	    {"Jane", "White",
    	     "Speed reading", new Integer(20), new Boolean(true)},
    	    {"Joe", "Brown",
    	     "Pool", new Integer(10), new Boolean(false)}
    };
    
    private JTable table = new JTable(data, columnNames);

    private CountTimer cntd;


    public CountTimerGUI() {
        //setTimerText("         ");
        GUI();
    }

    private void GUI() {
        frame = new JFrame();
        panel = new JPanel();
        
        //JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        panel.setLayout(new BorderLayout());
        timeLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        elapsedTime.setBorder(BorderFactory.createRaisedBevelBorder());
        injectedLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        faultLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        percLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        progressLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        console.setBorder(BorderFactory.createRaisedBevelBorder());
     
        Font font = new Font("Verdana", Font.BOLD, 20);
        
        JPanel injectionPanel = new JPanel();
        injectionPanel.setLayout(new GridLayout(4,1));
        injectedLabel.setForeground(Color.GREEN.darker());
        faultLabel.setForeground(Color.RED.darker());
        percLabel.setForeground(Color.BLUE.darker());
        progressLabel.setForeground(Color.BLUE.darker());
        console.setForeground(Color.BLUE.darker());
        
        elapsedTime.setFont(font);
        injectedLabel.setFont(font);
        faultLabel.setFont(font);
        percLabel.setFont(font);
        progressLabel.setFont(font);
        console.setFont(font);
        
        injectionPanel.add(elapsedTime);
        injectionPanel.add(injectedLabel);
        injectionPanel.add(faultLabel);
        injectionPanel.add(percLabel);
        injectionPanel.add(progressLabel);
        injectionPanel.add(console);
        //injectionPanel.add(table);
        
        panel.add(injectionPanel,  BorderLayout.CENTER);
         

/*        startBtn.addActionListener(this);
        pauseBtn.addActionListener(this);

        stopBtn.addActionListener(this);*/


       /* JPanel cmdPanel = new JPanel();
        cmdPanel.setLayout(new GridLayout());

        cmdPanel.add(startBtn);
        cmdPanel.add(pauseBtn);
        cmdPanel.add(stopBtn);

        panel.add(cmdPanel, BorderLayout.SOUTH);

        JPanel clrPanel = new JPanel();
        clrPanel.setLayout(new GridLayout(0,1));


        panel.add(clrPanel, BorderLayout.EAST);*/

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        cntd = new CountTimer();

    }

    private void setTimerText(String sTime) {
        timeLabel.setText(sTime);
    }


/*    private void setTimerColor(Color sColor) {
        timeLabel.setForeground(sColor);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        JButton btn = (JButton) e.getSource();

        if (btn.equals(startBtn))        { cntd.start(); } 
        else if (btn.equals(pauseBtn))   { cntd.pause(); }
        else if (btn.equals(stopBtn))    { cntd.stop(); }
    }


    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
               new CountTimerGUI();
             }
          });

    }

    private class CountTimer implements ActionListener {

        //private static final int ONE_SECOND = 1000;
        private int count = 0;
        private boolean isTimerActive = false;
       // private Timer tmr = new Timer(ONE_SECOND, this);

        public CountTimer() {
            count=0;
            setTimerText(TimeFormat(count));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isTimerActive) {
                count++;
                setTimerText(TimeFormat(count));
            }
        }

        public void start() {
        	run = true;
        }

        public void pause() {
            run = !run;
        }

        public void stop() {
        	run = false;
        }



    }
    
    public void setInjected(long injected) {
		injectedLabel.setText(String.format("Injected Errors: %15d", injected));
	}
    
    public void setFault(long faults) {
		faultLabel.setText(String.format("Functional Errors: %13d", faults));
	}
    
    public void setBenchmarkLabel(String name) {
    	frame.setTitle(name);
		//benchmarkLabel.setText("Benchmark: " + name);
	}
    
    public void setElapsedTime(String name) {
		elapsedTime.setText(name);
	}
    
    public void setPercentageLabel(double perc) {
    	percLabel.setText(String.format( "Functional / Injected: %.10f", perc));
	}
    
    public void setProgressLabel(String string) {
    	progressLabel.setText(string);
	}
    
    public void setConsole(String string) {
    	console.setText(string);
	}
    
    public String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count-hours*3600)/60;
        int seconds = count-minutes*60;

        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
    
    public void close() {
    	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
