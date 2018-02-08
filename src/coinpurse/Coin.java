package coinpurse;

/*
 * Coin represents coinage (money) with a fixed value and currency.
 * @author Vichakorn Yotboonrueang
 */
public class Coin implements Comparable<Coin> {
	/** save the value of coin. */
	private double value;
	/** save the currency of coin. */
	private String currency;
	/**
	 * Create a coin with a attribute that should have.
	 * 
	 * @param value of coin.
	 * @param curenc of coin.
	 */
	public Coin(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}
	/**
	 * Get value of coin from attribute.
	 * @return value of coin.
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Get currency of coin from attribute.
	 * @return currency of coin.
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * Compares two object between this object and specified object.
	 * @param arg is the object that been using for compare.
	 * @return true if two object are the same.
	 */
	@Override
	public boolean equals(Object arg) {
		if (arg == null) {
			return false;
		}
		if (arg.getClass() != this.getClass()) {
			return false;
		}
		Coin other = (Coin) arg;
		return this.value == other.value && this.currency.equals(other.currency);
	}
	/**
	 * Compares Coin by value.
	 * @param coin is the object that compared;
	 * @return -1 if value this object after specified object,
	 * 		    1 if value this object before specified object,
	 * 		    0 if value are the same. 
	 */
	@Override
	public int compareTo(Coin coin) {
		Coin other = (Coin) coin;
		if (this.value < other.value)
			return -1;
		if (this.value > other.value)
			return 1;
		else
			return 0;
	}
	/**
	 * Describing what coin is in the purse.
	 * @return describe of coin.
	 */
	@Override
	public String toString() {
		return value + "-" + currency;
	}

}