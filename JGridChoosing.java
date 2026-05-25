import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JGridChoosing extends JPanel{
	//Propeties
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DatabaseAccess.imageloading("gridscreen"),0,0,null);
	}
	
	//Constructor
	public JGridChoosing(){
		super();
	}
	
}
