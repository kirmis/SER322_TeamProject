package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} 
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		init();
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 618);
	}
}
