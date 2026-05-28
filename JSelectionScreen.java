import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JSelectionScreen extends JPanel{
	//Properties
	BufferedImage selectionbg = null;
	BufferedReader gridchoice;
	String strSelectedGrid;
	
	int umarow = 2;
	int umacol = 3;
			String strGrid[][];
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(selectionbg,0,0,null);
		
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
					strLine = " ";
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
					g.drawImage(DatabaseAccess.imageloading(strCell), intGridcol*103+227, intGridrow*155+148,null);
				}
			}
			//g.drawImage(DatabaseAccess.imageloading(strGrid[umarow][umacol]), 1080,300,null);
		//	System.out.println(umarow + " " + umacol + " " + strGrid[umarow][umacol]);
		}

	}
	
	//Constructor
	public JSelectionScreen(){
		super();
		
		selectionbg = DatabaseAccess.imageloading("umaselectionscreen");
	}
}
