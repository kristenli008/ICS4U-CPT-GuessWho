/**
 * The JPanel for the Win Screen of Guess Who
 * * @author Kristen, Marcus, & Nicole
 * @version 1.1
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JWinScreen extends JPanel{
	//Properties
	String strUmaName = "daitakuhelios";
	DatabaseAccess DA = new DatabaseAccess();
	
	//Methods
	/**Method for painting the JPanel*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DA.imageloading("winscreen"),0,0,null);
		g.drawImage(DA.imageloading(strUmaName),589,252,null);
	}
	
	//Constructor
	public JWinScreen(){
		super();
	}

}
