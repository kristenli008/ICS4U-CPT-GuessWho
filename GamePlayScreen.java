import java.util.*;
import javax.swing.*;

public class GamePlayScreen{
	SuperSocketMaster ssm = null;
	
	public HashTable gameplay(HashTable<JFrame, SuperSocketMaster> tablepass){
		ssm = new SuperSocketMaster(1234,this);
		ssm.connect();
		
		return tablepass;
	}
	
	public static void main(String[] args){
		
	}
}
