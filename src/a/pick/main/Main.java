package a.pick.main;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		try{
			throw new IOException(new SQLException(new Exception("test!")));
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
	}
}
