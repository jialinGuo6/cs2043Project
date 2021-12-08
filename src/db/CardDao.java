package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//Connect to database data
public class CardDao {
	// Add a row of data to the database table card, when create a personal account or business account
     public int add(String bank,String no,String sdate,double money,int uid, String type){
    	int rows = 0;
    	DBUtil db = new DBUtil();
     	Connection conn = null;
     	try{
     		conn = db.getConn();
  String sql = "insert into card(bank,no,sdate,money,uid,type) values(?,?,?,?,?,?)";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, bank);
             ps.setString(2, no);
             ps.setString(3, sdate);
             ps.setDouble(4, money);
             ps.setInt(5, uid);
             ps.setString(6,type);
             rows = ps.executeUpdate();
     	}catch(Exception ex){
     		ex.printStackTrace();
     	}finally{
     		db.closeConn(conn);
     	}
    	return rows; 
     }
     
     // deposit money, hange the amount of money in the bank card in the database
     // The variable money is the amount subtracted
     public int in(String no,double money,String type){
    	int rows = 0;
      	DBUtil db = new DBUtil();
       	Connection conn = null;
       	try{
       		conn = db.getConn();
    String sql = "update card  set money = money + ? where no = ? and type = ?";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setDouble(1, money);
               ps.setString(2, no);
               ps.setString(3,type);
               rows = ps.executeUpdate();
       	}catch(Exception ex){
       		ex.printStackTrace();
       	}finally{
       		db.closeConn(conn);
       	}
      	return rows; 
     }
     
     //withdraw money，change the amount of money in the bank card in the database
     //Variable money is the amount added
     public int out(String no,double money,String type){
    	int rows = 0;
      	DBUtil db = new DBUtil();
       	Connection conn = null;
       	try{
       		conn = db.getConn();
    String sql = "update card  set money = money - ? where no = ? and type = ?";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setDouble(1, money);
               ps.setString(2, no);
               ps.setString(3,type);
               rows = ps.executeUpdate();
       	}catch(Exception ex){
       		ex.printStackTrace();
       	}finally{
       		db.closeConn(conn);
       	}
      	return rows; 
     }
     
     // get all data to the database table card
     public ResultSet selectAll(){
     	DBUtil db = new DBUtil();
     	Connection conn = null;
     	ResultSet rs = null;
     	try{
     		conn = db.getConn();
     		String sql = "select * from card";
     		PreparedStatement ps = conn.prepareStatement(sql);
     		rs = ps.executeQuery();
     	}catch(Exception ex){
     		ex.printStackTrace();
     	}
     	return rs;
     }
     //get money by the bank card to know how much money
     public double selectMoneyByNo(String no, String type){
    	 double money = 0;
    	 DBUtil db = new DBUtil();
      	 Connection conn = null;
      	 ResultSet rs = null;
      	 try{
      		conn = db.getConn();
      		String sql = "select * from card where no = ? and type = ?";
      		PreparedStatement ps = conn.prepareStatement(sql);
      		ps.setString(1, no);
      		ps.setString(2,type);
      		rs = ps.executeQuery();
      		if(rs.next()){
      			money = rs.getDouble("money");
      		}
      	}catch(Exception ex){
      		ex.printStackTrace();
      	}finally{
      		db.closeConn(conn);
      	}
    	 
    	 return money;
     }
     
   //Get account type(personal or business) by bank card
     public String selectTypeByNo(String no, String type){
    	 String type1 = "";
    	 DBUtil db = new DBUtil();
      	 Connection conn = null;
      	 ResultSet rs = null;
      	 try{
      		conn = db.getConn();
      		String sql = "select type from card where no = ? and type =?";
      		PreparedStatement ps = conn.prepareStatement(sql);
      		ps.setString(1, no);
      		ps.setString(2,type);
      		rs = ps.executeQuery();
      		if(rs.next()){
      			type1 = rs.getString("type");
      		}
      	}catch(Exception ex){
      		ex.printStackTrace();
      	}finally{
      		db.closeConn(conn);
      	}
    	 
    	 return type1;
     }
}







