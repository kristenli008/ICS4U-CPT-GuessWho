import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class DatabaseAccess{
	
	public static BufferedImage imageloading(String strFilename){
		try{
			return ImageIO.read(new File(strFilename+".png"));
		}catch(IOException e){
			return null;
		}
	}
	
}
