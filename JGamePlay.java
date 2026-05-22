import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class JGamePlay extends JPanel{
	// properties
	BufferedImage reddesire = null;
	BufferedImage admiregroove = null;
	BufferedImage lovesonlyyou = null;
	BufferedImage victoirepisa = null;
	BufferedImage bubblegumfellow = null;
	BufferedImage dreamjourney = null;
	BufferedImage fusaichipandora = null;
	BufferedImage taninogimlet = null;
	BufferedImage orfevre = null;
	BufferedImage neouniverse = null;
	BufferedImage gentildonna = null;
	BufferedImage hishimiracle = null;
	BufferedImage riceshower = null;
	BufferedImage matikanefukukitaru = null;
	BufferedImage daitakuhelios = null;
	BufferedImage meishodoto = null;
	BufferedImage tmoperao = null;
	BufferedImage agnesdigital = null;
	BufferedImage mejiroryan = null;
	BufferedImage wonderacute = null;
	BufferedImage currenbouquetdor = null;
	BufferedImage finemotion = null;
	BufferedImage mejiropalmer = null;
	BufferedImage currenchan = null;
	BufferedImage satonodiamond = null;
	BufferedImage mejirodober = null;
	BufferedImage matikanetannhauser = null;
	BufferedImage supercreek = null;
	BufferedImage dantsuflame = null;
	BufferedImage espoircity = null;
	BufferedImage stillinlove = null;
	BufferedImage northflight = null;
	BufferedImage mejiroramonu = null;
	BufferedImage copanorickey = null;
	BufferedImage vivlos = null;
	BufferedImage kiseki = null;
	
	
	// methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(riceshower,0,0,null);
	}
	
	// constructor
	public JGamePlay(){
		super();
		
		riceshower = DatabaseAccess.imageloading("rice shower");
		
	}
}
