/**
 * The JPanel for the Grid Choosing Screen of Guess Who
 * * @author Kristen, Marcus, & Nicole
 * @version 2.50
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JGridChoosing extends JPanel{
	//Propeties
	DatabaseAccess DA = new DatabaseAccess();
	
	//Methods
	/**Method for painting the JPanel*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DA.imageloading("gridscreen"),0,0,null);
	}
	
	//Constructor
	public JGridChoosing(){
		super();
	}
	
}
