/**
 * The Decoder for the SuperSocketMaster for Guess Who
 * * @author Kristen, Marcus, & Nicole
 * @version 1.6
 */
import java.lang.*;

public class SocketNetwork{
	
	/**Method for reading SuperSocketMaster messages */
	public static int sendingcheck(String strMessage){
		// index for chat messages
		if(strMessage.startsWith("chat/")){
			return 1;
			
		// index for asking questions
		}else if(strMessage.startsWith("ques/")){
			return 2;
			
		// index for passing grid types
		}else if(strMessage.startsWith("grid/")){
			return 3;
			
		// index for passing question answers
		}else if(strMessage.startsWith("answ/")){
			return 4;
			
		// index for player winning / losing
		}else if(strMessage.startsWith("wilo/")){
			return 5;
			
		// index for testing connection between player/host
		}else if(strMessage.startsWith("test/")){
			return 6;
			
		// index for passing player / host ready to play
		}else if(strMessage.startsWith("redy/")){
			return 7;
			
		// index for guessing characters
		}else if(strMessage.startsWith("gues/")){
			return 8;
		// error index
		}else{
			return 0;
		}
	}
	
	/**Returns the SuperSocketMaster message for removing the index from the message.*/
	public static String parsemsg(String strMessage){
		int intLength = strMessage.length();
		return strMessage.substring(5,intLength);
	}
}
