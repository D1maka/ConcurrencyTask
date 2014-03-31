package task5;

import java.util.LinkedList;

public class Bank {
	LinkedList<Account> accounts;

	public Bank(LinkedList<Account> accounts) {
		this.accounts = accounts;
	}

	public int getBankBalance() {
		int balance = 0;
		for (Account account : accounts) {
			balance += account.getBalance();
		}
		return balance;
	}

	public void transfer(Account from, Account to, int amount) {
		
	}
}
