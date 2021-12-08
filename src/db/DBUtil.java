package db;
import java.sql.*; 

public class DBUtil {
	
	//connection to database
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
            String user = "root";
            String pass = "Gjl220718";
            conn = DriverManager.getConnection(url,user,pass);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return conn;
	}
	
	//close  connection to database
	public void closeConn(Connection conn){
		try{
			if(conn!=null){
				conn.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String []args){
		DBUtil db = new DBUtil();
		System.out.println(db.getConn());
		
	}
}
