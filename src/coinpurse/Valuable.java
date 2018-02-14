package coinpurse;
/*
 * An interface for objects having a monetary value and currency.
 * @author Vichakorn Yotboonrueang
 */
public interface Valuable extends Comparable<Valuable> {
	/**
	 * Get the value of Money
	 * @return the value of Money
	 */
	public double getValue();
	/**
	 * Get the currency of Money
	 * @return the currency of MOney
	 */
	public String getCurrency();
}
