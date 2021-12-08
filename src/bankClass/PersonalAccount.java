package bankClass;

import java.sql.ResultSet;
import java.util.Scanner;

import db.CardDao;
import db.TransactionDao;
/*
-- Account Details
-- Deposit Cash 
-- Withdraw Cash
-- Transfer Money 
-- Transaction History 
*/
public class PersonalAccount {
	
	Scanner sc = new Scanner(System.in);
	public PersonalAccount(Scanner sc) {
		super();
		this.sc = sc;
	}
	// Account Details of Personal Account of a user
	public void  AccountDetails(String type) {
		System.out.println("Bank card number");
		String no = sc.next();
		CardDao carddao = new CardDao();
		double money = carddao.selectMoneyByNo(no,type);
		if(money==0){
			System.out.println("No this Bank card number");
		}else{
			System.out.println("Bank card number: "+no+"  Account Balance: "+money+"  Account type: "+type);
		}
	}
	//Deposit money more than 1 of Personal Account
	public void  DepositCash(String type) {
		System.out.println("Bank card number");
		String no = sc.next();
		System.out.println("Enter the deposit amount");
		double money = sc.nextDouble();
      
		CardDao carddao = new CardDao();
		int rows = carddao.in(no, money,type);     //get money
		if(rows==0){
			System.out.println("No this Bank card number");
		}else{
			
			TransactionDao tran = new TransactionDao();     //transfer info
			tran.add(no, "Deposit", money,type);      
			System.out.println("Deposit Success");
		}
	}
	//Withdraw money more than 1 of Personal Account
	public void  WithdrawCash(String type) {
		System.out.println("Bank card number");
		String no = sc.next();
		System.out.println("Enter the withdraw amount");
		double money = sc.nextDouble();
		CardDao carddao = new CardDao();
		int rows = carddao.out(no, money,type);     //lose
		if(rows==0){
			System.out.println("No this Bank card number");
		}else{
			
			TransactionDao tran = new TransactionDao();
			tran.add(no, "Withdraw", money,type);		 // transfer info
			System.out.println("Withdraw Success");
		}
	}
	// Transfer money more than 1 of Personal Account
	// Only transfer between accounts of the same type
	// when transfer between 2 users: one user lose money, one user get money.
	// The two accounts money should change.
	public void TransferMoney(String type) {
		
		CardDao carddao = new CardDao();
		System.out.println("Enter Bank card number");
		String no = sc.next();
		System.out.println("Enter the money want to transfer");
		double money = sc.nextDouble();
		System.out.println("Which want to transfer Bank card number");
		String otherNo = sc.next();
		
		// Just can transfer to same type account, Personal Account to Personal Account
		String otherNoType = carddao.selectTypeByNo(otherNo, type);
		if (type.equals(otherNoType)) {
			System.out.println("Set the pin to transfer");
			String pin = sc.next();

			TransactionDao tran = new TransactionDao();
			
				int rows = tran.transfer(no, "transfer", 100, otherNo, pin, type); // transfer info
				if (rows > 0) {
					carddao.out(no, money, type); // a user lose(withdraw) money
					carddao.in(otherNo, money, type); //a user get(deposit) money
				}
		}else {
			System.out.println("The two Bank card not same account type or not exsit one card");
		}
	}
	//The history of a bank car of deposit, withdraw and transfer of a bank card.
	public void  TransactionHistory(String no) {
		
		System.out.println("This is the transaction history of bank card number " + no);
		 try {
         	TransactionDao transactionDao = new TransactionDao();
				ResultSet rs = transactionDao.selectAll(no);
				System.out.println("card number\tType\tamount\t\t\ttime\tAccount type\t");
				while (rs.next()) {
					
					System.out.print(rs.getString("cardno") + "\t");
					System.out.print(rs.getString("type") + "\t");
					System.out.print(rs.getDouble("money") + "\t");
					System.out.print(rs.getString("tdate") + "\t");
					System.out.print(rs.getString("ctype") + "\n");
				}
				rs.close();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}
	
	
}
