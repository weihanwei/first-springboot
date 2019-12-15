package com.example.demo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JavaSwingTest {
	
	public static void main(String[] args) {
		// JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("解压zip");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400,400));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel inLabel = new JLabel("输入地址");
		inLabel.setBounds(10,20,80,25);
		panel.add(inLabel);
		JTextField inText = new JTextField(20);
			inText.setBounds(100,20,165,25);
	        panel.add(inText);
		JLabel outLabel = new JLabel("输出地址");
			outLabel.setBounds(10,20,80,25);
			panel.add(outLabel);
		JTextField outText = new JTextField(20);
			outText.setBounds(100,20,165,25);
	        panel.add(outText);
		frame.add(panel);
		
		JButton loginButton = new JButton("开始解压");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String inStr = inText.getText();
        		String outStr = outText.getText();
        		
        		JOptionPane.showConfirmDialog(null, "开始解决", "开始解决", JOptionPane.YES_NO_OPTION);  
        		System.out.println(inStr);
        		System.out.println(outStr);
        		String returnStr = ZipTest.doZip(inStr, outStr);
        			if(returnStr.equals("success")) {
        				JOptionPane.showConfirmDialog(null, "解压完成", "解压完成", JOptionPane.YES_NO_OPTION);  
        			}else {
        				JOptionPane.showConfirmDialog(null, "解压失败", "解压失败", JOptionPane.YES_NO_OPTION);  
        				
        			}
        		}
        });
        panel.add(loginButton);
		
		
		// show window
		frame.pack();
		frame.setVisible(true);
	}

}
