package coinpurse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Comparator;

/**
 * A coinpurse contains money(coins/bank notes). You can insert money(coins/bank note), withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author Vichakorn Yotboonrueang
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Valuable> money = new ArrayList<Valuable>();
	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
	 */
	private final int capacity;
	
	private Comparator<Valuable> comp = new ValueComparator();

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of coins you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Count and return the number of coins in the purse. This is the number of
	 * money(coins/bank note), not their value.
	 * 
	 * @return the number of money(coins/bank note) in the purse
	 */
	public int count() {
		return money.size();
	}

	/**
	 * Get the total value of all items in the purse.
	 * 
	 * @return the total value of items in the purse.
	 */
	public double getBalance() {
		double save = 0;
		for (int i = 0; i < money.size(); i++) {
			save += money.get(i).getValue();
		}
		return save;
	}

	/**
	 * Return the capacity of the money(coins/bank note) purse.
	 * 
	 * @return the capacity
	 */

	public int getCapacity() {
		return capacity;
	}

	/**
	 * Test whether the purse is full. The purse is full if number of items in purse
	 * equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		if (money.size() < capacity)
			return false;
		else
			return true;
	}

	/**
	 * Insert a money(coins/bank note) into the purse. The money is only inserted if the purse has
	 * space for it and the money has positive value. No worthless money!
	 * 
	 * @param coin
	 *            is a Coin/BankNote object to insert into purse
	 * @return true if coin inserted, false if can't insert
	 */
	public boolean insert(Valuable coin) {
		boolean ok = true;
		if (coin.getValue() <= 0 || this.isFull())
			return false;
		else
			money.add(coin);
		return ok;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Coins withdrawn
	 * from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of money objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(double amount) {
		List<Valuable> withdrawSave = new ArrayList<>();
		java.util.Collections.sort(money,comp);
		java.util.Collections.reverse(money);
		if (amount <= 0 || money.size() == 0 || this.getBalance() < amount) 
			return null;
		for (int i = 0; i < money.size(); i++) {
			if (amount >= money.get(i).getValue()) {
				amount -= money.get(i).getValue();
				withdrawSave.add(money.get(i));
			}
			if (amount == 0) 
				break;
		}
		if (amount != 0)
			return null;
		for (Valuable reset : withdrawSave)
			money.remove(reset);
		Valuable[] withdraw = new Valuable[withdrawSave.size()];

		return withdrawSave.toArray(withdraw);
	}

	/**
	 * toString returns a string description of the purse contents. It can return
	 * whatever is a useful description.
	 */
	public String toString() {

		return this.count() + " coins with value " + this.getBalance();
	}

}
