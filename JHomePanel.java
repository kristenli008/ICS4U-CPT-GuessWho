import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JHomePanel extends JPanel{
	//Properties
	int intx=(int)(Math.round(100*(Math.sin(0 + (Math.PI)/2))));
	int inty=(int)(Math.round(100*(Math.sin(0))));
	boolean blnconnected = false;
	DatabaseAccess DA = new DatabaseAccess();
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(DA.imageloading("homescreen"),0,0,null);
		
		if(blnconnected == true){
			g.setColor(new Color(255,255,255));
			g.fillOval(875,585,80,80);
			g.setColor(new Color(191,243,254));
			g.fillOval(895,605,40,40);
			g.setColor(new Color(246,90,181));
			g.fillOval(905 + intx,615 +inty,20,20);	
		}
		
	}
	
	//Constructor
	public JHomePanel(){
		super();
	}
}
