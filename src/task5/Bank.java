package task5;

import java.util.ArrayList;

public class Bank {
	ArrayList<Account> accounts;
	private int transactionsNumber;

	public Bank(ArrayList<Account> accounts) {
		this.accounts = accounts;
		transactionsNumber = 0;
	}

	public int getBankBalance() {
		int balance = 0;
		for (Account account : accounts) {
			balance += account.getBalance();
		}
		return balance;
	}

	public Account getRandomAccount() {
		int index = (int) (Math.random() * accounts.size());
		return accounts.get(index);
	}
	
	public int getTransactionNumber(){
		return transactionsNumber;
	}

	public void transfer(Account from, Account to, int amount) {
		transactionsNumber++;
		Account firstLock, secondLock;
		if (from.getId() < to.getId()) {
			firstLock = from;
			secondLock = to;
		} else {
			firstLock = to;
			secondLock = from;
		}

		synchronized (firstLock) {
			synchronized (secondLock) {
				if (from.getBalance() < amount) {
					return;
				}
				from.withdraw(amount);
				to.deposit(amount);
			}
		}
	}
}
