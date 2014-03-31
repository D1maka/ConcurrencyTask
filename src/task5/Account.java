package task5;

import java.util.concurrent.locks.Lock;

public class Account {
	private int id;
	private int balance;
	private Lock lock;
	
	public void deposit(int amount) {
		if (amount > 0) {
			balance += amount;
		}
	}
	
	public void withdraw(int amount) {
		if (amount > 0) {
			balance -= amount;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public int getBalance() {
		return balance;
	}
	
	private Lock getLock() {
		return lock;
	}
}
