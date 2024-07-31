package com.app.tester;
import static com.app.utils.BankDisplay.GetAccDetails;
import java.time.LocalDate;
import java.util.Scanner;

import com.app.banking.AcType;
import com.app.banking.BankAccount;
import com.app.utils.BankDisplay;

import static com.app.utils.BankingValidations.*;

public class BankingApp {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// start up : init phase
			boolean exit = false;
			// init D.S -- array
			System.out.println("Enter max no of bank accounts");
			BankAccount[] accounts = new BankAccount[sc.nextInt()];// 100
			int counter = 0;
			while (!exit) {
				// clnt servicing phase
				System.out.println("Options : 1. Create A/C \n" + "2. Display All Accounts \n" + "3.Get Account details 4. Withdraw from particular account 0. Exit ");
				System.out.println("Choose an option");
				try {
					switch (sc.nextInt()) {
					case 1:
						// boundary condition checking
						if (counter < accounts.length) {
							System.out.println("Enter a/c details -  acctNo,  firstName,"

									+ " lastName,  acType,  dob, creationDate, balance");
							BankAccount acct = validateInputs(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(),
									sc.next(), sc.nextDouble(),accounts);
							accounts[counter++] = acct;
							System.out.println("A/c created !");

						} else
							System.out.println("Bank capacity full !!!!");

						break;
					case 2: // display all account details
						System.out.println("All account details -");
						for (BankAccount a : accounts)//foreach loop always works on copy of array and it use for display only not for alter
							if (a != null) //no runtime error but give null values which we dont add
								System.out.println(a);

						break;
					case 3:
						System.out.println("Enter the account number");
						//int accno=sc.nextInt();
						BankAccount account=GetAccDetails(sc.nextInt(), accounts);
						System.out.println(account);
						break;
					case 4:
						System.out.println("Enter the account number");
						//int accno=sc.nextInt();
						 account=GetAccDetails(sc.nextInt(), accounts);
						System.out.println(account.withdraw(sc.nextDouble()));
						

					case 0:
						exit = true;
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
					//read off all the pending tokens from the Scanner's buffer till new line
					sc.nextLine();
				}

			}
		} // JVM : sc.close()

		System.out.println("main over...");

	}

}












-----------------------------------------------------------------








package com.app.banking;
/*
 * state - account no , customer name(first name last name), 
 * account type , customer dob , a/c creation date , balance

 */

import java.time.LocalDate;

import com.app.custom_exceptions.BankingException;

import static com.app.utils.BankingValidations.*;

public class BankAccount {
//tight encapsulation
	private int acctNo;
	private String firstName;
	private String lastName;
	private AcType acType;
	private LocalDate dob;
	private LocalDate creationDate;
	private double balance;

	public BankAccount(int acctNo, String firstName, String lastName, AcType acType, LocalDate dob,
			LocalDate creationDate, double balance) {
		super();
		this.acctNo = acctNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.acType = acType;
		this.dob = dob;
		this.creationDate = creationDate;
		this.balance = balance;
	}
	//add overloaded ctor to wrap : acct no
	public BankAccount(int acctNo) {
		this.acctNo=acctNo;
	}

	@Override
	public String toString() {
		return "BankAccount [acctNo=" + acctNo + ", firstName=" + firstName + ", lastName=" + lastName + ", acType="
				+ acType + ", dob=" + dob + ", creationDate=" + creationDate + ", balance=" + balance + "]";
	}

	// add Business logic methods --for withdraw , deposit n fund transfer
	// deposit method
	public double deposit(double amount) {
		this.balance += amount;
		return balance;
	}

	// withdraw funds
	public double withdraw(double amount) throws BankingException {
		// validateBalance
		validateBalance(this.acType, balance - amount);
		this.balance -= amount;
		return balance;
	}

	// transfer funds
	public void transferFunds(BankAccount dest, double amount) throws BankingException {
		this.withdraw(amount);
		// => withdrwan : successfully
		dest.deposit(amount);
	}

	// override equals method inherited from Object class , to replace ref equality
	// by UID based equality
	// eg : acct no
	@Override
	public boolean equals(Object o) {
		System.out.println("in acct's equals");
		if (o instanceof BankAccount) {// if we not check type then it will may convert into Integer class Integer can not cast into bankaccount...is Integer instance of bankAccount..no then return false
			return this.acctNo == ((BankAccount) o).acctNo;
		}
		return false;
	}

}






----------------------------------------------------------------




package com.app.utils;

import java.time.LocalDate;

import com.app.banking.AcType;
import com.app.banking.BankAccount;
import com.app.custom_exceptions.BankingException;

public class BankingValidations {

//add public static method to validate for min bal
	public static void validateBalance(AcType type, double balance) throws BankingException {
		if (balance < type.getMinBalance())
			throw new BankingException("Insufficient Balance !!!!!");
		System.out.println("valid balance....");
	}

	// add public static method to validate all i/ps
	public static BankAccount validateInputs(int acctNo, String fName, String lastName, String acType, String dob,
			String creationDate, double balance,BankAccount[] accounts) throws BankingException { //account array is for the duplicate acc not enter.. in java everything is pass by value
		checkForDup(acctNo, accounts);
		AcType type = parseAndValidateAcType(acType);
		LocalDate birthDate = parseDate(dob);
		LocalDate crDate = parseDate(creationDate);
		validateBalance(type, balance);
		// => all i/ps are valid
		return new BankAccount(acctNo, fName, lastName, type, birthDate, crDate, balance);//validateinput method returning the object 
	}

	// add a static method for parsing n validating ac type
	public static AcType parseAndValidateAcType(String acType) {
		return AcType.valueOf(acType.toUpperCase()); ///string to enum conversion
	}

	// add a static method for parsing string --> LocalDate
	public static LocalDate parseDate(String date) {
		return LocalDate.parse(date);//string to localdate conversion
	}

	// add a static method for checking dup accts (PK / UID -- acct no)
	public static void checkForDup(int acctNo, BankAccount[] accts) throws BankingException {
		//encapsulate acct no (PK) in bank account object
		BankAccount acct=new BankAccount(acctNo); //
		for (BankAccount a : accts) {
			if (a != null && a.equals(acct)) //if we pass here acc no it give wrong output
				throw new BankingException("Dup account no !!!!!");
		}
	}
	

}


---------------------------------------------------------



package com.app.tester;
import static com.app.utils.BankDisplay.GetAccDetails;
import java.time.LocalDate;
import java.util.Scanner;

import com.app.banking.AcType;
import com.app.banking.BankAccount;
import com.app.utils.BankDisplay;

import static com.app.utils.BankingValidations.*;

public class BankingApp {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// start up : init phase
			boolean exit = false;
			// init D.S -- array
			System.out.println("Enter max no of bank accounts");
			BankAccount[] accounts = new BankAccount[sc.nextInt()];// 100
			int counter = 0;
			while (!exit) {
				// clnt servicing phase
				System.out.println("Options : 1. Create A/C \n" + "2. Display All Accounts \n" + "3.Get Account details 4. Withdraw from particular account 0. Exit ");
				System.out.println("Choose an option");
				try {
					switch (sc.nextInt()) {
					case 1:
						// boundary condition checking
						if (counter < accounts.length) {
							System.out.println("Enter a/c details -  acctNo,  firstName,"

									+ " lastName,  acType,  dob, creationDate, balance");
							BankAccount acct = validateInputs(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(),
									sc.next(), sc.nextDouble(),accounts);
							accounts[counter++] = acct;
							System.out.println("A/c created !");

						} else
							System.out.println("Bank capacity full !!!!");

						break;
					case 2: // display all account details
						System.out.println("All account details -");
						for (BankAccount a : accounts)//foreach loop always works on copy of array and it use for display only not for alter
							if (a != null) //no runtime error but give null values which we dont add
								System.out.println(a);

						break;
					case 3:
						System.out.println("Enter the account number");
						//int accno=sc.nextInt();
						BankAccount account=GetAccDetails(sc.nextInt(), accounts);
						System.out.println(account);
						break;
					case 4:
						System.out.println("Enter the account number");
						//int accno=sc.nextInt();
						 account=GetAccDetails(sc.nextInt(), accounts);
						System.out.println(account.withdraw(sc.nextDouble()));
						

					case 0:
						exit = true;
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
					//read off all the pending tokens from the Scanner's buffer till new line
					sc.nextLine();
				}

			}
		} // JVM : sc.close()

		System.out.println("main over...");

	}

}


-------------------------------------------------
package com.app.banking;

public enum AcType { // give validation for each acc min balance condition
	SAVINGS(10000), CURRENT(5000), FD(50000), 
	LOAN(25000), DMAT(40000), NRE(150000);

	// state
	private double minBalance;

	// ctor
	AcType(double minBalance) {
		//super(name and ordinal position add by compiler)
		this.minBalance = minBalance;
	}
	//can you override toString ?
	@Override
	public String toString() { // if we not overide then it call from its superclass enum
		return name()+" : "+minBalance;
	}
	//can you add methods ? Yes
	public double getMinBalance() {
		return minBalance;
	}
	
	
}

------------------------------------------------------
package com.app.utils;

import java.util.Scanner;

import com.app.banking.BankAccount;
import com.app.custom_exceptions.BankingException;

public class BankDisplay {
	//add static method to written account details and throw exception
	public static BankAccount GetAccDetails(int acctNo,BankAccount[] accts) throws BankingException
	{
		//create bankaccount instance wrapping the primary key
		BankAccount acct=new BankAccount(acctNo);
		for(BankAccount a:accts) 
		{
				if(a!=null && a.equals(acct))
				
				   return a;
				
		}
			
		throw new BankingException("Invalid Account number");
	}	 
}

