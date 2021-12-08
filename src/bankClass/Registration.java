package bankClass;

import java.util.Date;
import java.util.Scanner;

import db.CardDao;
import db.UsersDao;

public class Registration {
	
	String name;
	String password;
	Scanner sc = new Scanner(System.in);
	// Register here if a user is not registered, get the user's name and password
	// Users can create an account during registration
	public Registration(String name, String password, Scanner sc) {
		this.name = name;
		this.password = password;
		this.sc = sc;
 		UsersDao userdao = new UsersDao();  
     	int rows = userdao.add(name,password);  //Registration;
     	  //create account;
     	String type;
     		do{
     			System.out.println("Create:\n Personal Account Enter=> P \n Business Account Enter => B\n No Create Account => N  ");
     			type = sc.next();
     			CreateAccount(type, sc,userdao);
     		}while(!type.equals("N"));
     	    //success ?
     		if(rows>0){
     			System.out.println("Registration success"); 
     		}
		
		}
	//create account Personal Account or Business Account
	//if deposit money more than 1, Personal Account can be create
	//if deposit money more than 500, Business Account can be create
	public void CreateAccount(String type, Scanner sc, UsersDao userdao) {
		Date date = new Date();
			if(type.equals("P")) { 
				System.out.println("The name of bank");
				String bank = sc.next();
				System.out.println("Bank card number");
    	        String no = sc.next(); 
             	System.out.println("Personal account opening amount");
             	double money = sc.nextDouble();
    	        int uid = userdao.FindUid(name, password);    //Find which user(id) create account;
    	        CardDao carddao = new CardDao();
    		if(money >= 1){ 
    			int rows2 = carddao.add(bank, no, date.toLocaleString(), money, uid, type);
    		}else {
    			System.out.println("At least 1 for Personal Account");
    		}
    	
			}else if(type.equals("B")){
				System.out.println("The name of bank");
				String bank = sc.next();
				System.out.println("Bank card number");
				String no = sc.next();
				System.out.println("Business account opening amount");
				double money = sc.nextDouble();
				int uid = userdao.FindUid(name, password);    //which user(id) create account;
				CardDao carddao = new CardDao();
				if(money >= 500) {
					int rows3 = carddao.add(bank, no, date.toLocaleString(), money, uid, type);
				}else {System.out.println("At least 500 for Personal Account");}
			}
	}
	
	//get & set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
		
}
