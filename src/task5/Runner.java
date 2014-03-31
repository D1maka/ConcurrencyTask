package task5;

import java.util.ArrayList;

public class Runner {

	public static final int ACCOUNT_AMOUNT = 100;
	public static final int THREADS_AMOUNT = 200;

	public static void main(String[] args) {

		ArrayList<Account> accounts = new ArrayList<Account>();
		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i < ACCOUNT_AMOUNT; i++) {
			Account account = new Account();
			account.deposit((int) (Math.random() * ACCOUNT_AMOUNT));
			accounts.add(account);
		}

		Bank bank = new Bank(accounts);

		System.out.println("Total bank balance before trasactions: "
				+ bank.getBankBalance());

		Runner runner = new Runner();
		for (int i = 0; i < THREADS_AMOUNT; i++) {
			threads.add(new Thread(runner.getNewTransaction(bank)));
			threads.get(i).start();
		}
		
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Total bank balance after trasactions: "
				+ bank.getBankBalance());
		System.out.println("Transaction amount: "
				+ bank.getTransactionNumber());
	}

	private Runnable getNewTransaction(Bank bank) {
		return new Transaction(bank);
	}

	private class Transaction implements Runnable {
		private Bank bank;

		public Transaction(Bank bank) {
			this.bank = bank;
		}

		@Override
		public void run() {
			Account from = null;
			Account to = null;
			do {
				from = bank.getRandomAccount();
				to = bank.getRandomAccount();
			} while (from == to || from == null || to == null);
			
			int transferMoneyAmount = (int) (Math.random() * ACCOUNT_AMOUNT);
			bank.transfer(from, to, transferMoneyAmount);
		}

	}
}
