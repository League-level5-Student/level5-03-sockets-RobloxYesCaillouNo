package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	
	JButton button = new JButton("Send message");
	JLabel msg = new JLabel();
	JTextField textBox = new JTextField(35);
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	
	ChatAppServer server;
	ChatAppClient client;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		String username = JOptionPane.showInputDialog("Enter your name");
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "say yes or no", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new ChatAppServer(8080);
			frame.add(panel);
			panel.add(button);
			frame.setTitle("SERVER");
			
			panel.add(textBox);
			frame.setVisible(true);
			frame.setSize(566,1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			
			button.addActionListener((e)->{
				panel.add(msg);
				msg.setText(username + ": " + textBox.getText() + "\n");
				
				server.sendClick();
			});
			server.start();
			
		}else{
			frame.add(panel);
			panel.add(button);
			frame.setTitle("CLIENT");
			frame.setSize(566,1000);
			panel.add(textBox);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number (8080 probably)");
			int port = Integer.parseInt(prtStr);
			client = new ChatAppClient(ipStr, port);
			button.addActionListener((e)->{
				panel.add(msg);
				msg.setText(username + ": " + textBox.getText() + "\n");
			
				client.sendClick();
			});
			client.start();
		}
	}
}
