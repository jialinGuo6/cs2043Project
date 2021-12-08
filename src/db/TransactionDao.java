package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
public class TransactionDao {
	//Add a row of data to the database table transaction as history
	//When users Deposit Cash, Withdraw Cash, Transfer Money
    public int add(String no,String type,double money, String ctype){
    	int rows = 0;
    	DBUtil db = new DBUtil();
     	Connection conn = null;
     	try{
     		conn = db.getConn();
  String sql = "insert into transaction(cardno,type,money,ctype,tdate) values(?,?,?,?,?)";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, no);
             ps.setString(2, type);
             ps.setDouble(3, money);
             ps.setString(4, ctype);
             Date date = new Date();
             ps.setString(5, date.toLocaleString());
            
             rows = ps.executeUpdate(); 
     	}catch(Exception ex){
     		ex.printStackTrace();
     	}finally{
     		db.closeConn(conn);
     	}
    	return rows; 
    }
    // get all data to the database table transaction in a bank card;
    public ResultSet selectAll(String no){
     	DBUtil db = new DBUtil();
     	Connection conn = null;
     	ResultSet rs = null;
     	try{
     		conn = db.getConn();
     		String sql = "select * from transaction where cardno = ?";
     		PreparedStatement ps = conn.prepareStatement(sql);
     		ps.setString(1, no);
     		rs = ps.executeQuery();
     	}catch(Exception ex){
     		ex.printStackTrace();
     	}
     	return rs;
    }
    // Add a record of a user transferring money to another user in the database table transaction
    public int transfer(String no,String type,double money,String ocardno,String pin,String ctype) {
        	int rows = 0;
        	DBUtil db = new DBUtil();
         	Connection conn = null;
         	try{
         		conn = db.getConn();
         		//7 ?
      String sql = "insert into transaction(cardno,type,money,ctype,ocardno,pin,tdate) values(?,?,?,?,?,?,?)";
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ps.setString(1, no);
                 ps.setString(2, type);
                 ps.setDouble(3, money);
                 ps.setString(4, ctype);
                 ps.setString(5, ocardno);
                 ps.setString(6, pin);
                 Date date = new Date();
                 ps.setString(7, date.toLocaleString());
                
                 rows = ps.executeUpdate();
         	}catch(Exception ex){
         		ex.printStackTrace();
         	}finally{
         		db.closeConn(conn);
         	}
        	return rows; 
    }
    
    
}
