import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JLoseScreen extends JPanel{
	//Properties
	String strUmaName = "daitakuhelios";
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DatabaseAccess.imageloading("losescreen"),0,0,null);
		g.drawImage(DatabaseAccess.imageloading(strUmaName),589,282,null);
	}
	
	//Constructor
	public JLoseScreen(){
		super();
	}

}
