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
