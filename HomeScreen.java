import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class HomeScreen implements ActionListener{
	// Properties
	JFrame theFrame = new JFrame("Guess Who");
	JPanel homePanel = new JPanel();
	JButton hostButton = new JButton("HOST");
	JButton joinButton = new JButton("JOIN");
	JTextField testField = new JTextField();
	JTextArea testArea = new JTextArea();
	JScrollPane testScroll = new JScrollPane(testArea);
	SuperSocketMaster ssm = null;
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == hostButton){
			ssm = new SuperSocketMaster(1234,this);
			ssm.connect();
			
			// Maybe print to screen or give random code for users to join
			System.out.println("The host's IP is: " +ssm.getMyAddress());
			
		}else if(evt.getSource() == joinButton){
			
			// Just testing for now
			String strIPJoin = JOptionPane.showInputDialog(theFrame, "Enter the host's IP", "JOINING", JOptionPane.PLAIN_MESSAGE);
			ssm = new SuperSocketMaster(strIPJoin, 1234, this);
			ssm.connect();
			System.out.println("Input the host's ip or hostname");
		}else if(evt.getSource() == testField){
			ssm.sendText(testField.getText());
			testField.setText("");
		}else if(evt.getSource() == ssm){
			String strLine = ssm.readText();
			testArea.append(strLine + "\n");
		}
	}
	
	// Constructor
	public HomeScreen(){
		homePanel.setLayout(null);
		homePanel.setPreferredSize(new Dimension(1280,720));
		theFrame.setContentPane(homePanel);
		
		// Can change coordinates later
		hostButton.setBounds(300, 100, 330, 100);
		hostButton.addActionListener(this);
		homePanel.add(hostButton);
		
		joinButton.setBounds(650, 100, 330, 100);
		joinButton.addActionListener(this);
		homePanel.add(joinButton);
		
		testField.setBounds(650, 300, 330, 100);
		testField.addActionListener(this);
		homePanel.add(testField);
		
		testArea.setBounds(650, 500, 330, 100);
		homePanel.add(testArea);
		
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setResizable(false);
		theFrame.pack();
		theFrame.setVisible(true);
		
	}
	
	// Main Method
	public static void main(String[] args){
		new HomeScreen();
	}
	
}
