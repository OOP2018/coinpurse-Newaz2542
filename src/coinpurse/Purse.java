package coinpurse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Comparator;

/**
 * A purse contains valuable. You can insert money, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author Vichakorn Yotboonrueang
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Valuable> money = new ArrayList<Valuable>();
	private Comparator<Valuable> comp = new ValueComparator();
	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of money you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Count and return the number of item in the purse. This is the number of
	 * item money, not their value.
	 * 
	 * @return the number of item in the purse
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
	 * Return the capacity of the purse.
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
	 * Insert a money into the purse. The money is only inserted if the purse has
	 * space for it and the money has positive value. No worthless money!
	 * 
	 * @param money
	 *            is a Valuable object to insert into purse
	 * @return true if money inserted, false if can't insert
	 */
	public boolean insert(Valuable money) {
		boolean ok = true;
		if (money.getValue() <= 0 || this.isFull())
			return false;
		else
		this.money.add(money);
		return ok;
	}

	/**
	 * Withdraw the requested amount of money that have currency Baht. 
	 * Return an array of Valuable withdrawn from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Valuable objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(double amount) {
		return withdraw(new Money(amount , "Baht"));
	}
	/**
	 * Withdraw the requested amount of money that contains currency of amount. 
	 * Return an array of Valuable withdrawn from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Valuable objects for withdrawn money, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(Valuable amount) {
		List<Valuable> withdrawSave = new ArrayList<>();
		java.util.Collections.sort(money,comp);
		java.util.Collections.reverse(money);
		double amountSave = amount.getValue();
		if (amountSave <= 0 || money.size() == 0 || this.getBalance() < amountSave || amount == null) {
			return null;
		}
		for (Valuable m : money) {
			if (amount.getValue() >= m.getValue()) {
				amountSave -= m.getValue();
				withdrawSave.add(m);
			}
			if (amountSave == 0) 
				break;
		}
		if (amountSave != 0)
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

		return this.count() + " money with value " + this.getBalance();
	}

}
