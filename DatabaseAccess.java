/**
 * The Database Accessor for Guess Who
 * * @author Kristen, Marcus, & Nicole
 * @version 1.3
 */
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Font;
import java.awt.FontMetrics;


public class DatabaseAccess{
	/**Method for loading images
	 * Returns a BufferedImage object of the image name given*/
	public static BufferedImage imageloading(String strFilename){
		try{
			return ImageIO.read(new File("Database/"+strFilename+".png"));
		}catch(IOException e){
			return null;
		}
	}
	
	/**Method for loading fonts
	 * Returns a Font object of the font name and size given*/
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
	
	/**Method for loading character grids
	 * returns a grid file name for the integer code of grid given*/
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
	
	/**Method for creating BufferedReaders for files
	 * Returns a BufferedReader for the file name given*/
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
