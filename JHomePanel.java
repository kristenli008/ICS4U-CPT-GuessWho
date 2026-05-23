import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JHomePanel extends JPanel{
	//Properties
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DatabaseAccess.imageloading("homescreen"),0,0,null);
	}
	
	//Constructor
	public JHomePanel(){
		super();
	}
}
