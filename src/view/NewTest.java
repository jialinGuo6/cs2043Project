package view;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

import bankClass.BusinessAccount;
import bankClass.PersonalAccount;
import bankClass.Registration;
import db.CardDao;
import db.TransactionDao;
import db.UsersDao;

public class NewTest {

	public static void main(String[] args) {
		System.out.println("Web based Banking System in JAVA");
		Scanner sc = new Scanner(System.in);
		System.out.println("Login Enter ==> 1\nIf not registered Enter ==> 2");
		int select = sc.nextInt();
		if (select == 2) {
			//Registration: User who is having bank account should generate 
			//unique user name and security key for log in to system.
			System.out.println("User Registration");
			System.out.println("Username");
			String name = sc.next();
			System.out.println("Password");
			String password = sc.next();
			//Registration class can register and create Account for a user;
			Registration registration = new Registration(name, password, sc); 

		} else {
			// login
			System.out.println("User login: \n");
			System.out.println("Username");
			String name = sc.next();
			System.out.println("Password");
			String password = sc.next();
			UsersDao userdao = new UsersDao();
			if (userdao.login(name, password) == true) {
				System.out.println("Login success");
				//After log in to system user is entered in to main form where 
				//choose account option is provided user can select personal or business account.
				System.out.println("Personal Account Enter=> P, Busniss Account Enter ==> B");
				String type = sc.next();
				
				//These are 5 features available on the form deposit cash, withdraw cash, 
				//transfer money, Transaction history, account details.
				while (true) {
					System.out.println("1 Deposit Cash");
					System.out.println("2 Withdraw Cash");
					System.out.println("3 Transfer");
					System.out.println("4 Transaction History");
					System.out.println("5 Account Details");
					System.out.println("0 Quit");
					int n = sc.nextInt();
					// if account type is personal use class PersonalAccount
					// if account type is business use class BusinessAccount
					if (n == 1) {
						// -- Deposit Cash :Information about total amount of deposit and time
						if (type.equals("P")) {
							PersonalAccount person = new PersonalAccount(sc);
							person.DepositCash(type);
						} else if (type.equals("B")) {
							BusinessAccount business = new BusinessAccount(sc);
							business.DepositCash(type);
						} else {
							System.out.println("Incorrect account type");
						}

					}

					if (n == 2) {
						// -- Withdraw Cash : Information about money withdrawn from ATM or other banks
						if (type.equals("P")) {
							PersonalAccount person = new PersonalAccount(sc);
							person.WithdrawCash(type);
						} else if (type.equals("B")) {
							BusinessAccount business = new BusinessAccount(sc);
							business.WithdrawCash(type);
						} else {
							System.out.println("Incorrect account type");
						}
					}

					if (n == 3) {
						// -- Transfer Money : Online money transaction details to other banks or same banks. 
						// This transaction details are transfer to, amount of transfer, security pin fields.
						// Just can transfer to same type account,
						// Business Account to Business Account; Personal Account to Personal Account
						if (type.equals("P")) {
							PersonalAccount person = new PersonalAccount(sc);
							person.TransferMoney(type);
						} else if (type.equals("B")) {
							BusinessAccount business = new BusinessAccount(sc);
							business.TransferMoney(type);
						} else {
							System.out.println("Incorrect account type");
						}
					}

					if (n == 4) {
						// -- Transaction History : This transaction time, amount, and type. 
						// Detailed information of every transaction is displayed here.
						System.out.println("Enter Bank card number");
						String no = sc.next();

						if (type.equals("P")) {
							PersonalAccount person = new PersonalAccount(sc);
							person.TransactionHistory(no);
							;
						} else if (type.equals("B")) {
							BusinessAccount business = new BusinessAccount(sc);
							business.TransactionHistory(no);
							;
						} else {
							System.out.println("Incorrect account type");
						}
					}

					if (n == 5) {
						// -- Account Details :Information of bank account id, available balance 
						// and account type are shown in this screen.
						if (type.equals("P")) {
							PersonalAccount person = new PersonalAccount(sc);
							person.AccountDetails(type);
						} else if (type.equals("B")) {
							BusinessAccount business = new BusinessAccount(sc);
							business.AccountDetails(type);
						} else {
							System.out.println("Incorrect account type");
						}
					}

					if (n == 0) {
						System.out.println("Thank you");
						break;
					}

				}

			} else {
				System.out.println("incorrect Username or Password");
			}

		}
	}

}
