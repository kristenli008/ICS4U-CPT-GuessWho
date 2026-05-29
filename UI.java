import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Font;
import java.awt.FontMetrics;


public class UI implements ActionListener{
	// Properties
	JFrame theFrame = new JFrame("Guess Who");
	//JPanels
	JHomePanel homePanel = new JHomePanel();
	JGamePlay gameplayPanel = new JGamePlay();
	JGridChoosing gridPanel = new JGridChoosing();
	JSelectionScreen selectPanel = new JSelectionScreen();
	
	// JButtons
	JButton hostButton = new JButton("");
	JButton joinButton = new JButton("");
	JButton testbutton = new JButton("Gameplay Screen");
	
	// testing
	JTextField testField = new JTextField();
	JLabel testLabel = new JLabel("");
	
	//JScrollPane testScroll = new JScrollPane(testArea);
	//Home Screen
	JLabel IPLabel = new JLabel("",SwingConstants.CENTER);
	JLabel WaitingText = new JLabel("",SwingConstants.CENTER);
	JLabel Send = new JLabel("SEND");
	JLabel Area = new JLabel("AREA");
	JLabel SelectedUmaPreview = new JLabel("");
	SuperSocketMaster ssm = null;
	
	//Grid Buttons
	JButton CellA1 = new JButton("");
	JButton CellA2 = new JButton("");
	JButton CellA3 = new JButton("");
	JButton CellA4 = new JButton("");
	JButton CellA5 = new JButton("");
	JButton CellA6 = new JButton("");
	JButton CellA7 = new JButton("");
	JButton CellA8 = new JButton("");
	
	JButton CellB1 = new JButton("");
	JButton CellB2 = new JButton("");
	JButton CellB3 = new JButton("");
	JButton CellB4 = new JButton("");
	JButton CellB5 = new JButton("");
	JButton CellB6 = new JButton("");
	JButton CellB7 = new JButton("");
	JButton CellB8 = new JButton("");
	
	JButton CellC1 = new JButton("");
	JButton CellC2 = new JButton("");
	JButton CellC3 = new JButton("");
	JButton CellC4 = new JButton("");
	JButton CellC5 = new JButton("");
	JButton CellC6 = new JButton("");
	JButton CellC7 = new JButton("");
	JButton CellC8 = new JButton("");
	
	JButton SelectionConfirm = new JButton("");

	JButton Grid1 = new JButton("");
	JButton Grid2 = new JButton("");
	
	int intCellMarginX=227;
	int intCellMarginY=148;
	boolean Gameplay = false;
	String strGridSelection;
	
	boolean blnConnected = false;
	boolean blnSelectReady = false;
	JTextField Readyfield = new JTextField("0/2 players ready!", SwingConstants.CENTER);
	
	//Chat Boxes
	JTextArea GuessingChat = new JTextArea("guessing will begin below");
	JTextArea RegularChat = new JTextArea("chat will begin below.");
	JTextField ChatInputBox = new JTextField("");
	JButton SendMessageButton = new JButton("");
	String strNetworkMessage;
	boolean blnHost = false;
	int intMessageType = 0;
	
	//Images
	
	//Methods
	
	//Grid
	int intGrid = 0;
	
	//Timer
	javax.swing.Timer Timer = new javax.swing.Timer(1000/60,this);
	
	
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == Timer){
			//SelectedUmaPreview.setIcon(new ImageIcon(DatabaseAccess.imageloading(gameplayPanel.strUma)));
			//selectPanel.repaint();
		}else if(evt.getSource() == ssm){
			try{
				strNetworkMessage = ssm.readText();
				System.out.println(strNetworkMessage);
				intMessageType = SocketNetwork.sendingcheck(strNetworkMessage);
				strNetworkMessage = SocketNetwork.parsemsg(strNetworkMessage);
				
				if(theFrame.getContentPane() == homePanel){
					// checking if host received client test message
					if(intMessageType == 6){
						WaitingText.setText("Connected!");
						
						// switching panels for client
						theFrame.setContentPane(gridPanel);
						theFrame.pack();
					}else if(intMessageType == 3 && blnHost == false){
						// checking if grid type was sent to client
						try{
							intGrid = Integer.parseInt(strNetworkMessage);
						}catch(NumberFormatException e){
							System.out.println("not a grid type");
						}
						
						// choosing grid type
						selectPanel.strSelectedGrid = "grid"+intGrid;
						gameplayPanel.strSelectedGrid = "grid"+intGrid;
						
						// switching panel
						theFrame.setContentPane(selectPanel);
						theFrame.pack();
					}
				}else if(theFrame.getContentPane() == selectPanel){	
					if(intMessageType == 7){
						blnSelectReady = true;
						Readyfield.setText("1/2 players ready!");
					}
				}else if(theFrame.getContentPane() == gameplayPanel){
					}if(intMessageType == 1){
						RegularChat.append("\n\nOpponent: "+strNetworkMessage);
					}
			}catch(NullPointerException e){
				System.out.println("null pointer exception");
			}
			
		}else if(evt.getSource() == hostButton){
			// hosting game
			blnHost = true;
			Timer.start();
			
			ssm = new SuperSocketMaster(1234,this);
			ssm.connect();
			
			IPLabel.setText("IP: " +ssm.getMyAddress());
			WaitingText.setText("waiting for opponent...");
			
			// Maybe print to screen or give random code for users to join
			System.out.println("The host's IP is: " +ssm.getMyAddress());

		}else if(evt.getSource() == joinButton){
			blnHost = false;
			
			if(blnConnected == true){
				ssm.disconnect();
			}
			
			String strIPJoin = null;
			blnConnected = false;
			
			
			while(blnConnected == false){
				strIPJoin = JOptionPane.showInputDialog(theFrame, "Enter the host's IP", "JOINING", JOptionPane.PLAIN_MESSAGE);
				
				IPLabel.setText("Connecting...");
				
				ssm = new SuperSocketMaster(strIPJoin, 1234, this);
				ssm.connect();
				
				blnConnected = ssm.sendText("test/test");
				if(blnConnected == false){
					strIPJoin = null;
					ssm.disconnect();
					//System.out.println("Disconnected");
					IPLabel.setText("IP not found; try again");
					break;
				}else{
					//System.out.println("Connected");
					IPLabel.setText("Connected!");
					WaitingText.setText("waiting for host to select grid...");
					
					
				}
			
				System.out.println(blnConnected);
			}
			
			//theFrame.setContentPane(selectPanel);
		}else if(evt.getSource() == testField){
			ssm.sendText(testField.getText());
			testField.setText("");
		}else if(evt.getSource() == testbutton){
			theFrame.setContentPane(gridPanel);
			theFrame.pack();
		}else if(evt.getSource() == Grid1){
			// choosing grid 1
			selectPanel.strSelectedGrid = "grid1";
			gameplayPanel.strSelectedGrid = "grid1";
			theFrame.setContentPane(selectPanel);
			theFrame.pack();
			
			ssm.sendText("grid/1");
			
		}else if(evt.getSource() == Grid2){
			// choosing grid 2
			selectPanel.strSelectedGrid = "grid2";
			gameplayPanel.strSelectedGrid = "grid2";
			theFrame.setContentPane(selectPanel);
			theFrame.pack();
			
			ssm.sendText("grid/2");
		
		}else if(evt.getSource() == SendMessageButton || evt.getSource() == ChatInputBox){
			// sending chat message
			ssm.sendText("chat/" + ChatInputBox.getText());
			RegularChat.append("\n\nYou:"+ChatInputBox.getText());
			ChatInputBox.setText("");
		}else if(theFrame.getContentPane() == selectPanel){
			// painting gameplay panel buttons
			if(evt.getSource() == SelectionConfirm){
				ssm.sendText("redy/.");
				
				if(blnSelectReady == true){
					Readyfield.setText("2/2 players ready!");
					
					System.out.println("GAME START");
					theFrame.setContentPane(gameplayPanel);
					theFrame.pack();
					intCellMarginX = 45;
					intCellMarginY = 205;
					
					//Cell buttons
					CellA1.setBounds(intCellMarginX,intCellMarginY,103,155);		
					CellA2.setBounds(intCellMarginX+103,intCellMarginY,103,155);		
					CellA3.setBounds(intCellMarginX+206,intCellMarginY,103,155);		
					CellA4.setBounds(intCellMarginX+309,intCellMarginY,103,155);		
					CellA5.setBounds(intCellMarginX+412,intCellMarginY,103,155);		
					CellA6.setBounds(intCellMarginX+515,intCellMarginY,103,155);		
					CellA7.setBounds(intCellMarginX+618,intCellMarginY,103,155);		
					CellA8.setBounds(intCellMarginX+721,intCellMarginY,103,155);
					//ROW 2
					CellB1.setBounds(intCellMarginX,intCellMarginY+155,103,155);
					CellB2.setBounds(intCellMarginX+103,intCellMarginY+155,103,155);		
					CellB3.setBounds(intCellMarginX+206,intCellMarginY+155,103,155);		
					CellB4.setBounds(intCellMarginX+309,intCellMarginY+155,103,155);		
					CellB5.setBounds(intCellMarginX+412,intCellMarginY+155,103,155);		
					CellB6.setBounds(intCellMarginX+515,intCellMarginY+155,103,155);		
					CellB7.setBounds(intCellMarginX+618,intCellMarginY+155,103,155);		
					CellB8.setBounds(intCellMarginX+721,intCellMarginY+155,103,155);
					//ROW 3
					CellC1.setBounds(intCellMarginX,intCellMarginY+310,103,155);
					CellC2.setBounds(intCellMarginX+103,intCellMarginY+310,103,155);		
					CellC3.setBounds(intCellMarginX+206,intCellMarginY+310,103,155);		
					CellC4.setBounds(intCellMarginX+309,intCellMarginY+310,103,155);		
					CellC5.setBounds(intCellMarginX+412,intCellMarginY+310,103,155);		
					CellC6.setBounds(intCellMarginX+515,intCellMarginY+310,103,155);		
					CellC7.setBounds(intCellMarginX+618,intCellMarginY+310,103,155);		
					CellC8.setBounds(intCellMarginX+721,intCellMarginY+310,103,155);
					
					gameplayPanel.add(CellA1);
					gameplayPanel.add(CellA2);
					gameplayPanel.add(CellA3);
					gameplayPanel.add(CellA4);
					gameplayPanel.add(CellA5);
					gameplayPanel.add(CellA6);
					gameplayPanel.add(CellA7);
					gameplayPanel.add(CellA8);
					gameplayPanel.add(CellB1);
					gameplayPanel.add(CellB2);
					gameplayPanel.add(CellB3);
					gameplayPanel.add(CellB4);
					gameplayPanel.add(CellB5);
					gameplayPanel.add(CellB6);
					gameplayPanel.add(CellB7);
					gameplayPanel.add(CellB8);
					gameplayPanel.add(CellC1);
					gameplayPanel.add(CellC2);
					gameplayPanel.add(CellC3);
					gameplayPanel.add(CellC4);
					gameplayPanel.add(CellC5);
					gameplayPanel.add(CellC6);
					gameplayPanel.add(CellC7);
					gameplayPanel.add(CellC8);
				}
			}else if(evt.getSource() == CellA1){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 0;
		
			}else if(evt.getSource() == CellA2){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 1;
		
			}else if(evt.getSource() == CellA3){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 2;

			}else if(evt.getSource() == CellA4){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 3;
		
			}else if(evt.getSource() == CellA5){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 4;
	
			}else if(evt.getSource() == CellA6){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 5;
		
			}else if(evt.getSource() == CellA7){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 6;
			
			}else if(evt.getSource() == CellA8){
				gameplayPanel.umarow = 0;
				gameplayPanel.umacol = 7;
			
			}else if(evt.getSource() == CellB1){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 0;
		
			}else if(evt.getSource() == CellB2){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 1;
		
			}else if(evt.getSource() == CellB3){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 2;
	
			}else if(evt.getSource() == CellB4){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 3;
	
			}else if(evt.getSource() == CellB5){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 4;

			}else if(evt.getSource() == CellB6){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 5;
			
			}else if(evt.getSource() == CellB7){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 6;
		
			}else if(evt.getSource() == CellB8){
				gameplayPanel.umarow = 1;
				gameplayPanel.umacol = 7;
		
			}else if(evt.getSource() == CellC1){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 0;
		
			}else if(evt.getSource() == CellC2){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 1;
	
			}else if(evt.getSource() == CellC3){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 2;
	
			}else if(evt.getSource() == CellC4){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 3;

			}else if(evt.getSource() == CellC5){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 4;

			}else if(evt.getSource() == CellC6){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 5;

			}else if(evt.getSource() == CellC7){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 6;
			}else if(evt.getSource() == CellC8){
				gameplayPanel.umarow = 2;
				gameplayPanel.umacol = 7;
				
			}
		//	System.out.println(gameplayPanel.umarow+ " "+ gameplayPanel.umacol+" "+gameplayPanel.strGrid[gameplayPanel.umarow][gameplayPanel.umacol]);
		//	SelectedUmaPreview.setIcon(new ImageIcon(DatabaseAccess.imageloading(gameplayPanel.strUma)));


		
		}else if(theFrame.getContentPane() == gameplayPanel){
			if(evt.getSource() == CellA1){
				if(gameplayPanel.CellA1OPEN ==true){
					CellA1.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA1OPEN = false;
				}else{
					CellA1.setIcon(null);
					gameplayPanel.CellA1OPEN= true;
				}

			}else if(evt.getSource() == CellA2){
				if(gameplayPanel.CellA2OPEN ==true){
					CellA2.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA2OPEN = false;
				}else{
					CellA2.setIcon(null);
					gameplayPanel.CellA2OPEN= true;
				}

			}else if(evt.getSource() == CellA3){
				if(gameplayPanel.CellA3OPEN ==true){
					CellA3.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA3OPEN = false;
				}else{
					CellA3.setIcon(null);
					gameplayPanel.CellA3OPEN= true;
				}

			}else if(evt.getSource() == CellA4){
				if(gameplayPanel.CellA4OPEN ==true){
					CellA4.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA4OPEN = false;
				}else{
					CellA4.setIcon(null);
					gameplayPanel.CellA4OPEN= true;
				}

			}else if(evt.getSource() == CellA5){
				if(gameplayPanel.CellA5OPEN ==true){
					CellA5.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA5OPEN = false;
				}else{
					CellA5.setIcon(null);
					gameplayPanel.CellA5OPEN= true;
				}

			}else if(evt.getSource() == CellA6){
				if(gameplayPanel.CellA6OPEN ==true){
					CellA6.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA6OPEN = false;
				}else{
					CellA6.setIcon(null);
					gameplayPanel.CellA6OPEN= true;
				}

			}else if(evt.getSource() == CellA7){
				if(gameplayPanel.CellA7OPEN ==true){
					CellA7.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA7OPEN = false;
				}else{
					CellA7.setIcon(null);
					gameplayPanel.CellA7OPEN= true;
				}

			}else if(evt.getSource() == CellA8){
				if(gameplayPanel.CellA8OPEN ==true){
					CellA8.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellA8OPEN = false;
				}else{
					CellA8.setIcon(null);
					gameplayPanel.CellA8OPEN= true;
				}
	//ROW 2 BUTTONS
			}else if(evt.getSource() == CellB1){
				if(gameplayPanel.CellB1OPEN ==true){
					CellB1.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB1OPEN = false;
				}else{
					CellB1.setIcon(null);
					gameplayPanel.CellB1OPEN= true;
				}

			}else if(evt.getSource() == CellB2){
				if(gameplayPanel.CellB2OPEN ==true){
					CellB2.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB2OPEN = false;
				}else{
					CellB2.setIcon(null);
					gameplayPanel.CellB2OPEN= true;
				}

			}else if(evt.getSource() == CellB3){
				if(gameplayPanel.CellB3OPEN ==true){
					CellB3.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB3OPEN = false;
				}else{
					CellB3.setIcon(null);
					gameplayPanel.CellB3OPEN= true;
				}

			}else if(evt.getSource() == CellB4){
				if(gameplayPanel.CellB4OPEN ==true){
					CellB4.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB4OPEN = false;
				}else{
					CellB4.setIcon(null);
					gameplayPanel.CellB4OPEN= true;
				}

			}else if(evt.getSource() == CellB5){
				if(gameplayPanel.CellB5OPEN ==true){
					CellB5.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB5OPEN = false;
				}else{
					CellB5.setIcon(null);
					gameplayPanel.CellB5OPEN= true;
				}

			}else if(evt.getSource() == CellB6){
				if(gameplayPanel.CellB6OPEN ==true){
					CellB6.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB6OPEN = false;
				}else{
					CellB6.setIcon(null);
					gameplayPanel.CellB6OPEN= true;
				}

			}else if(evt.getSource() == CellB7){
				if(gameplayPanel.CellB7OPEN ==true){
					CellB7.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB7OPEN = false;
				}else{
					CellB7.setIcon(null);
					gameplayPanel.CellB7OPEN= true;
				}

			}else if(evt.getSource() == CellB8){
				if(gameplayPanel.CellB8OPEN ==true){
					CellB8.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellB8OPEN = false;
				}else{
					CellB8.setIcon(null);
					gameplayPanel.CellB8OPEN= true;
				}
	//ROW 3 BUTTONS
			}else if(evt.getSource() == CellC1){
				if(gameplayPanel.CellC1OPEN ==true){
					CellC1.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC1OPEN = false;
				}else{
					CellC1.setIcon(null);
					gameplayPanel.CellC1OPEN= true;
				}

			}else if(evt.getSource() == CellC2){
				if(gameplayPanel.CellC2OPEN ==true){
					CellC2.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC2OPEN = false;
				}else{
					CellC2.setIcon(null);
					gameplayPanel.CellC2OPEN= true;
				}

			}else if(evt.getSource() == CellC3){
				if(gameplayPanel.CellC3OPEN ==true){
					CellC3.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC3OPEN = false;
				}else{
					CellC3.setIcon(null);
					gameplayPanel.CellC3OPEN= true;
				}

			}else if(evt.getSource() == CellC4){
				if(gameplayPanel.CellC4OPEN ==true){
					CellC4.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC4OPEN = false;
				}else{
					CellC4.setIcon(null);
					gameplayPanel.CellC4OPEN= true;
				}

			}else if(evt.getSource() == CellC5){
				if(gameplayPanel.CellC5OPEN ==true){
					CellC5.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC5OPEN = false;
				}else{
					CellC5.setIcon(null);
					gameplayPanel.CellC5OPEN= true;
				}

			}else if(evt.getSource() == CellC6){
				if(gameplayPanel.CellC6OPEN ==true){
					CellC6.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC6OPEN = false;
				}else{
					CellC6.setIcon(null);
					gameplayPanel.CellC6OPEN= true;
				}

			}else if(evt.getSource() == CellC7){
				if(gameplayPanel.CellC7OPEN ==true){
					CellC7.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC7OPEN = false;
				}else{
					CellC7.setIcon(null);
					gameplayPanel.CellC7OPEN= true;
				}

			}else if(evt.getSource() == CellC8){
				if(gameplayPanel.CellC8OPEN ==true){
					CellC8.setIcon(new ImageIcon(DatabaseAccess.imageloading("closedcell")));
					gameplayPanel.CellC8OPEN = false;
				}else{
					CellC8.setIcon(null);
					gameplayPanel.CellC8OPEN= true;
				}

			}
		}
	}
	
	// Constructor
	public UI(){
		// homePanel
		homePanel.setLayout(null);
		homePanel.setPreferredSize(new Dimension(1280,720));
		theFrame.setContentPane(homePanel);
		
		// Can change coordinates later
		hostButton.setBounds(438, 462, 195, 75);
		hostButton.addActionListener(this);
		homePanel.add(hostButton);
		
		joinButton.setBounds(647, 462, 195, 75);
		joinButton.addActionListener(this);
		homePanel.add(joinButton);
		
		testbutton.setBounds(0,0,150,50);
		testbutton.addActionListener(this);
		homePanel.add(testbutton);
		
		testField.setBounds(650, 300, 330, 100);
		testField.addActionListener(this);
		//homePanel.add(testField);
		
		testLabel.setBounds(650,500,330,100);
		//homePanel.add(testLabel);
		
		//testScroll.setBounds(650, 500, 330, 100);
		//homePanel.add(testScroll);
		
		IPLabel.setBounds(437,584,405,50);
		IPLabel.setForeground(new Color(80,73,255));
		IPLabel.setFont(DatabaseAccess.fontloading("pixelmix.ttf",30));
		homePanel.add(IPLabel);
		
		WaitingText.setBounds(437,630,405,50);
		WaitingText.setForeground(new Color(69,171,242));
		WaitingText.setFont(DatabaseAccess.fontloading("pixelmix.ttf",20));
		homePanel.add(WaitingText);
		
		Send.setBounds(500, 300, 100, 100);
		//homePanel.add(Send);
		
		Area.setBounds(500, 500, 100, 100);
		//homePanel.add(Area);
		
		SelectedUmaPreview.setBounds(1080,375,103,155);
		selectPanel.add(SelectedUmaPreview);
		
		
		SelectionConfirm.setBounds(1078,556,130,62);
		SelectionConfirm.addActionListener(this);
		selectPanel.add(SelectionConfirm);
			
		
		// gameplayPanel
		gameplayPanel.setLayout(null);
		gameplayPanel.setPreferredSize(new Dimension(1280,720));
		
		//Chat Boxes
		RegularChat.setBounds(925,317,321,324);
		RegularChat.setEditable(false);
		RegularChat.setFont(DatabaseAccess.fontloading("pixelmix.ttf",10));
		RegularChat.setForeground(new Color(69,65,186));
		gameplayPanel.add(RegularChat);
		
		GuessingChat.setBounds(925,31,324,232);
		GuessingChat.setEditable(false);
		GuessingChat.setFont(DatabaseAccess.fontloading("pixelmix.ttf",10));
		GuessingChat.setForeground(new Color(69,65,186));
		gameplayPanel.add(GuessingChat);
		
		ChatInputBox.setBounds(936,667,245,34);
		gameplayPanel.add(ChatInputBox);
		ChatInputBox.addActionListener(this);
		
		SendMessageButton.setBounds(1206,665,38,38);
		gameplayPanel.add(SendMessageButton);
		SendMessageButton.addActionListener(this);
		
		//Grid Selection Panel
		gridPanel.setLayout(null);
		gridPanel.setPreferredSize(new Dimension(1280,720));
		
		Grid1.setBounds(482,200,294,135);
		Grid1.addActionListener(this);
		gridPanel.add(Grid1);
		
		Grid2.setBounds(482,371,294,135);
		Grid2.addActionListener(this);
		gridPanel.add(Grid2);
		
		
		//Character Selection Panel
		Readyfield.setBounds(440,637,400,28);
		Readyfield.setEditable(false);
		Readyfield.setFont(DatabaseAccess.fontloading("pixelmix.ttf",10));
		Readyfield.setForeground(new Color(69,171,242));
		selectPanel.add(Readyfield);
		
		selectPanel.setLayout(null);
		selectPanel.setPreferredSize(new Dimension(1280,720));
		
		//Cell buttons
		CellA1.setBounds(intCellMarginX,intCellMarginY,103,155);		
		CellA2.setBounds(intCellMarginX+103,intCellMarginY,103,155);		
		CellA3.setBounds(intCellMarginX+206,intCellMarginY,103,155);		
		CellA4.setBounds(intCellMarginX+309,intCellMarginY,103,155);		
		CellA5.setBounds(intCellMarginX+412,intCellMarginY,103,155);		
		CellA6.setBounds(intCellMarginX+515,intCellMarginY,103,155);		
		CellA7.setBounds(intCellMarginX+618,intCellMarginY,103,155);		
		CellA8.setBounds(intCellMarginX+721,intCellMarginY,103,155);
		//ROW 2
		CellB1.setBounds(intCellMarginX,intCellMarginY+155,103,155);
		CellB2.setBounds(intCellMarginX+103,intCellMarginY+155,103,155);		
		CellB3.setBounds(intCellMarginX+206,intCellMarginY+155,103,155);		
		CellB4.setBounds(intCellMarginX+309,intCellMarginY+155,103,155);		
		CellB5.setBounds(intCellMarginX+412,intCellMarginY+155,103,155);		
		CellB6.setBounds(intCellMarginX+515,intCellMarginY+155,103,155);		
		CellB7.setBounds(intCellMarginX+618,intCellMarginY+155,103,155);		
		CellB8.setBounds(intCellMarginX+721,intCellMarginY+155,103,155);
		//ROW 3
		CellC1.setBounds(intCellMarginX,intCellMarginY+310,103,155);
		CellC2.setBounds(intCellMarginX+103,intCellMarginY+310,103,155);		
		CellC3.setBounds(intCellMarginX+206,intCellMarginY+310,103,155);		
		CellC4.setBounds(intCellMarginX+309,intCellMarginY+310,103,155);		
		CellC5.setBounds(intCellMarginX+412,intCellMarginY+310,103,155);		
		CellC6.setBounds(intCellMarginX+515,intCellMarginY+310,103,155);		
		CellC7.setBounds(intCellMarginX+618,intCellMarginY+310,103,155);		
		CellC8.setBounds(intCellMarginX+721,intCellMarginY+310,103,155);
		
		//BUTTON VISIBILITY 
		
		CellA1.setOpaque(false);
		CellA1.setContentAreaFilled(false);
		CellA1.setBorderPainted(false);
		CellA2.setOpaque(false);
		CellA2.setContentAreaFilled(false);
		CellA2.setBorderPainted(false);
		CellA3.setOpaque(false);
		CellA3.setContentAreaFilled(false);
		CellA3.setBorderPainted(false);
		CellA4.setOpaque(false);
		CellA4.setContentAreaFilled(false);
		CellA4.setBorderPainted(false);
		CellA5.setOpaque(false);
		CellA5.setContentAreaFilled(false);
		CellA5.setBorderPainted(false);
		CellA6.setOpaque(false);
		CellA6.setContentAreaFilled(false);
		CellA6.setBorderPainted(false);
		CellA7.setOpaque(false);
		CellA7.setContentAreaFilled(false);
		CellA7.setBorderPainted(false);
		CellA8.setOpaque(false);
		CellA8.setContentAreaFilled(false);
		CellA8.setBorderPainted(false);
		
		CellB1.setOpaque(false);
		CellB1.setContentAreaFilled(false);
		CellB1.setBorderPainted(false);
		CellB2.setOpaque(false);
		CellB2.setContentAreaFilled(false);
		CellB2.setBorderPainted(false);
		CellB3.setOpaque(false);
		CellB3.setContentAreaFilled(false);
		CellB3.setBorderPainted(false);
		CellB4.setOpaque(false);
		CellB4.setContentAreaFilled(false);
		CellB4.setBorderPainted(false);
		CellB5.setOpaque(false);
		CellB5.setContentAreaFilled(false);
		CellB5.setBorderPainted(false);
		CellB6.setOpaque(false);
		CellB6.setContentAreaFilled(false);
		CellB6.setBorderPainted(false);
		CellB7.setOpaque(false);
		CellB7.setContentAreaFilled(false);
		CellB7.setBorderPainted(false);
		CellB8.setOpaque(false);
		CellB8.setContentAreaFilled(false);
		CellB8.setBorderPainted(false);
		
		CellC1.setOpaque(false);
		CellC1.setContentAreaFilled(false);
		CellC1.setBorderPainted(false);
		CellC2.setOpaque(false);
		CellC2.setContentAreaFilled(false);
		CellC2.setBorderPainted(false);
		CellC3.setOpaque(false);
		CellC3.setContentAreaFilled(false);
		CellC3.setBorderPainted(false);
		CellC4.setOpaque(false);
		CellC4.setContentAreaFilled(false);
		CellC4.setBorderPainted(false);
		CellC5.setOpaque(false);
		CellC5.setContentAreaFilled(false);
		CellC5.setBorderPainted(false);
		CellC6.setOpaque(false);
		CellC6.setContentAreaFilled(false);
		CellC6.setBorderPainted(false);
		CellC7.setOpaque(false);
		CellC7.setContentAreaFilled(false);
		CellC7.setBorderPainted(false);
		CellC8.setOpaque(false);
		CellC8.setContentAreaFilled(false);
		CellC8.setBorderPainted(false);
		
		Grid1.setOpaque(false);
		Grid1.setContentAreaFilled(false);
		Grid1.setBorderPainted(false);
		Grid2.setOpaque(false);
		Grid2.setContentAreaFilled(false);
		Grid2.setBorderPainted(false);
		
		
		SelectionConfirm.setOpaque(false);
		SelectionConfirm.setContentAreaFilled(false);
		SelectionConfirm.setBorderPainted(false);
		hostButton.setOpaque(false);
		hostButton.setContentAreaFilled(false);
		hostButton.setBorderPainted(false);
		joinButton.setOpaque(false);
		joinButton.setContentAreaFilled(false);
		joinButton.setBorderPainted(false);
		
		SendMessageButton.setOpaque(false);
		SendMessageButton.setContentAreaFilled(false);
		SendMessageButton.setBorderPainted(false);
		
		CellA1.addActionListener(this);
		CellA2.addActionListener(this);
		CellA3.addActionListener(this);
		CellA4.addActionListener(this);
		CellA5.addActionListener(this);
		CellA6.addActionListener(this);
		CellA7.addActionListener(this);
		CellA8.addActionListener(this);
		CellB1.addActionListener(this);
		CellB2.addActionListener(this);
		CellB3.addActionListener(this);
		CellB4.addActionListener(this);
		CellB5.addActionListener(this);
		CellB6.addActionListener(this);
		CellB7.addActionListener(this);
		CellB8.addActionListener(this);
		CellC1.addActionListener(this);
		CellC2.addActionListener(this);
		CellC3.addActionListener(this);
		CellC4.addActionListener(this);
		CellC5.addActionListener(this);
		CellC6.addActionListener(this);
		CellC7.addActionListener(this);
		CellC8.addActionListener(this);

		selectPanel.add(CellA1);
		selectPanel.add(CellA2);
		selectPanel.add(CellA3);
		selectPanel.add(CellA4);
		selectPanel.add(CellA5);
		selectPanel.add(CellA6);
		selectPanel.add(CellA7);
		selectPanel.add(CellA8);
		selectPanel.add(CellB1);
		selectPanel.add(CellB2);
		selectPanel.add(CellB3);
		selectPanel.add(CellB4);
		selectPanel.add(CellB5);
		selectPanel.add(CellB6);
		selectPanel.add(CellB7);
		selectPanel.add(CellB8);
		selectPanel.add(CellC1);
		selectPanel.add(CellC2);
		selectPanel.add(CellC3);
		selectPanel.add(CellC4);
		selectPanel.add(CellC5);
		selectPanel.add(CellC6);
		selectPanel.add(CellC7);
		selectPanel.add(CellC8);

		// JFrame
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setResizable(false);
		theFrame.pack();
		theFrame.setVisible(true);
		
		
		
	}  
	
	// Main Method
	public static void main(String[] args){
		new UI();
	}
	
	
	
	
}
