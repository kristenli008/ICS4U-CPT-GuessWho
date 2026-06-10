/**
 * The JPanel for the Lose Screen of Guess Who
 * * @author Kristen, Marcus, & Nicole
 * @version 2.50
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;



public class JLoseScreen extends JPanel{
	DatabaseAccess DA = new DatabaseAccess();
	
	//Properties
	String strUmaName = "daitakuhelios";
	
	//Methods
	/**Method for painting the JPanel*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DA.imageloading("losescreen"),0,0,null);
		g.drawImage(DA.imageloading(strUmaName),589,252,null);
	}
	
	//Constructor
	public JLoseScreen(){
		super();
	}

}
