package ca.ubc.cpsc210.account;

import java.util.LinkedList;
import java.util.List;

import ca.ubc.cpsc210.account.exceptions.IllegalValueException;

public class Bank {
	
	// Store the accounts
	List<Account> accounts;
	
	public Bank() {
		accounts = new LinkedList<Account>();
		Account ivansAccount = new Account("Ivan's Account", 1000000);
		Account gailsAccount = new Account("Gail's Account", 1000);
		accounts.add(ivansAccount);
		accounts.add(gailsAccount);
	}
	
	public void depositToAccount(String accountName, double value) {
		Account anAccount = findAccount(accountName);
		if (anAccount != null) {
			anAccount.deposit(value);
		}
	}
	
	public void withdrawFromAccount(String accountName, double value) {
		Account anAccount = findAccount(accountName);
		if (anAccount != null) {
			anAccount.withdraw(value);
		}
	}
	
	public Account findAccount(String accountName) {
		for (Account a: accounts) {
			if (a.getName().equals(accountName))
				return a;
		}
		return null;
	}
	
	public static void main(String args[]) {
		Bank b = new Bank();
		b.depositToAccount("Gail's Account", -1000);
		b.withdrawFromAccount("Gail's Account", 5);
		System.out.println(b.findAccount("Gail's Account"));
	}

}
