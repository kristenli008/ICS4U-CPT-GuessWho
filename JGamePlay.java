import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JGamePlay extends JPanel{
	// properties
	
	//Umas
	BufferedImage background = null;
	boolean CellA1OPEN = true;
	boolean CellA2OPEN = true;
	boolean CellA3OPEN = true;
	boolean CellA4OPEN = true;
	boolean CellA5OPEN = true;
	boolean CellA6OPEN = true;
	boolean CellA7OPEN = true;
	boolean CellA8OPEN = true;
	
	boolean CellB1OPEN = true;
	boolean CellB2OPEN = true;
	boolean CellB3OPEN = true;
	boolean CellB4OPEN = true;
	boolean CellB5OPEN = true;
	boolean CellB6OPEN = true;
	boolean CellB7OPEN = true;
	boolean CellB8OPEN = true;
	
	boolean CellC1OPEN = true;
	boolean CellC2OPEN = true;
	boolean CellC3OPEN = true;
	boolean CellC4OPEN = true;
	boolean CellC5OPEN = true;
	boolean CellC6OPEN = true;
	boolean CellC7OPEN = true;
	boolean CellC8OPEN = true;

	int umarow=2;
	int umacol=3;
	
	String strSelectedGrid;
	String strGrid[][];
	String strUma = "no uma";
	//Other Assets
	
	
	BufferedReader gridchoice;
	
	
	// methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		background = DatabaseAccess.imageloading("gameplayscreen");
		g.drawImage(background,0,0,null);
		
		String strLine;
		String strSplit[];
		String strCell;
		int intGridrow;
		int intGridcol;
		
		strGrid = new String[3][8];		
		gridchoice = DatabaseAccess.fileloading(strSelectedGrid);
	
		if(gridchoice != null){
			
			for(intGridrow = 0; intGridrow<3; intGridrow++){
				//read each line each new introw 
				try{
					strLine = gridchoice.readLine();
				}catch(IOException e){
					strLine = "daitakuhelios";
				}
				strSplit = strLine.split(",");
				for(intGridcol = 0; intGridcol<8; intGridcol++){
					strGrid[intGridrow][intGridcol] = strSplit[intGridcol];
				}
			}
			intGridrow = 0;
			intGridcol = 0;
			
			for(intGridrow = 0; intGridrow <3; intGridrow++){
				for(intGridcol = 0; intGridcol <8; intGridcol++){
					strCell = strGrid[intGridrow][intGridcol];
					g.drawImage(DatabaseAccess.imageloading(strCell), intGridcol*103+45, intGridrow*155+205,null);
				}
			}
			g.drawImage(DatabaseAccess.imageloading(strGrid[umarow][umacol]), 376,29,null);
			//strUma = strGrid[umarow][umacol];
		}
		
		//g.drawImage(DatabaseAccess.imageloading(yesbutton.PNG), 
		
		
	}
	
	// constructor
	public JGamePlay(){
		super();
		
		//riceshower = DatabaseAccess.imageloading("riceshower");
		
	}
}
