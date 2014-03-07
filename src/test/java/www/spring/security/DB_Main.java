package www.spring.security;

public class DB_Main {
	
	public static void main(String[]args){
		
		
		
		DB_Run db = new DB_Run();
		
		db.connectDB(new DB_Mssql());
		db.connectDB(new DB_Mysql());
		db.connectDB(new DB_Oracle());
		
		
		
	}

}
