package db;
import java.sql.*;
public class UsersDao {
	// Add a row of data to the database table user, when registration a user
    public int add(String name,String password){
   	int rows = 0;
   	DBUtil db = new DBUtil();
    	Connection conn = null;
    	try{
    		conn = db.getConn();
 String sql = "insert into users(name, password) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            rows = ps.executeUpdate();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		db.closeConn(conn);
    	}
   	return rows; 
    }
    //Find the user id by use username and password
    public int FindUid (String name,String password){
    	int id = 0;
    	DBUtil db = new DBUtil();
    	Connection conn = null;
     	ResultSet rs = null;
    	try{
    		conn = db.getConn();
    		String sql = "select * from users where name = ? and password = ?";
      		PreparedStatement ps = conn.prepareStatement(sql);
      		ps.setString(1, name);
      		ps.setString(2,password);
      		rs = ps.executeQuery();
      		if(rs.next()){
      			id = rs.getInt("id");
      		}

    	}catch(Exception ex){
      		ex.printStackTrace();
      	}finally{
      		db.closeConn(conn);
      	}
    	return id;
    }
    //Check whether the username and the corresponding password are correct
    public boolean login(String name,String password){
    	
    	boolean flag = false;
    	DBUtil db = new DBUtil();
    	Connection conn = null;
    	try{
    		conn = db.getConn();
 String sql = "select * from users where name = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){
            	flag = true;
            }
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally{
    		db.closeConn(conn);
    	}
    	return flag;
    }
    
    
}




