package task5;

import java.util.LinkedList;

public class Runner {

	public static final int ACCOUNT_AMOUNT = 100;

	public static void main(String[] args) {

		LinkedList<Account> accounts = new LinkedList<Account>();

		for (int i = 0; i < ACCOUNT_AMOUNT; i++) {
			Account account = new Account();
			account.deposit((int) (Math.random() * 100));
			accounts.add(account);
		}

		Bank bank = new Bank(accounts);

		System.out.println("Total bank balance before trasactions: "
				+ bank.getBankBalance());
		
		
	}

	private class Transaction implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}

}
