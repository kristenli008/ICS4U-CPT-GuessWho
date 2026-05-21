import java.util.*;
import javax.swing.*;

public class GamePlayScreen{
	SuperSocketMaster ssm = null;
	int integer = 0;
	
	
	/*
	public HashTable gameplay(HashTable<JFrame, SuperSocketMaster> tablepass){
		ssm = new SuperSocketMaster(1234,this);
		ssm.connect();
		
		return tablepass;
	}

	
	public SuperSocketMaster ssmpass(SuperSocketMaster ssmpassed){
		
	}
	*/
	
	public void intreceive(int intpassed){
		this.integer = intpassed;
	}
	
	public int intpass(){
		return integer;
	}
}
