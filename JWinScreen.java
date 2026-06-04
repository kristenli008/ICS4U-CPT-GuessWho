import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JWinScreen extends JPanel{
	//Properties
	String strUmaname = "daitakuhelios";
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DatabaseAccess.imageloading("winscreen"),0,0,null);
		g.drawImage(DatabaseAccess.imageloading(strUmaname),589,252,null);
	}
	
	//Constructor
	public JWinScreen(){
		super();
	}

}
