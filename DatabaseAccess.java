import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Font;
import java.awt.FontMetrics;


public class DatabaseAccess{
	
	public static BufferedImage imageloading(String strFilename){
		try{
			return ImageIO.read(new File("Database/"+strFilename+".png"));
		}catch(IOException e){
			return null;
		}
	}
	public static Font fontloading(String strFontname, int FontSize){    
		Font theFont = null;
		try{
			theFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Database/"+strFontname)); 
			return theFont.deriveFont(Font.PLAIN, FontSize);
		}catch(Exception e){
			System.out.println("Couldn't load font");
		}
		return theFont;
	}
	
	public static String SelectedGrid(int intGrid, boolean blninput){
		String strGrid = "";
		if(blninput == true){
			if(intGrid == 1){
				strGrid = "grid1.csv";
			}else if(intGrid == 2){
				strGrid = "grid2.csv";
			}
		}else if(blninput == false){
			return strGrid;
		}
		return strGrid;
	}
	public static BufferedReader fileloading(String strFilename){
		BufferedReader theFile = null;
		try{
			theFile = new BufferedReader(new FileReader("Database/"+strFilename+".csv"));

		}catch(FileNotFoundException e){
			System.out.println("FILE NOT FOUND");
		}
		return theFile;
	}
	

	
}
