import java.lang.*;

public class SocketNetwork{
	public static int sendingcheck(String strMessage){
		if(strMessage.startsWith("chat/")){
			return 1;
		}else if(strMessage.startsWith("turn/")){
			return 2;
		}else if(strMessage.startsWith("grid/")){
			return 3;
		}else if(strMessage.startsWith("answ/")){
			return 4;
		}else if(strMessage.startsWith("wilo/")){
			return 5;
		}else if(strMessage.startsWith("test/")){
			return 6;
		}else{
			return 0;
		}
	}
	
	public static String parsemsg(String strMessage){
		int intLength = strMessage.length();
		return strMessage.substring(5,intLength);
	}
}
