package coinpurse;
/** Money is a superclass of Coin and BankNote that have a same method and attribute like
 * equals(),compareTo(), getValue(), getCurrency(), value, currency.
 * 
 */
public class Money implements Valuable {

	/** save the value of Bank note. */
	protected double value;
	/** save the currency of Bank note. */
	protected String currency;

	public Money(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}

	/**
	 * Get value of bank note from attribute.
	 * 
	 * @return value of bank note.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Get value of bank note from attribute.
	 * 
	 * @return currency of bank note.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 *
	 * Compare two objects that implement Valuable. check two objects has the same
	 * objective or not, and than return Integer to sort list.
	 *
	 */
	@Override
	public int compareTo(Valuable o) {
		if (this.currency.equals(o.getCurrency())) {
			if (this.value > o.getValue())
				return 1;
			else if (this.value < o.getValue())
				return -1;
			else
				return 0;
		} else {
			return this.getCurrency().compareTo(o.getCurrency());
		}
	}

	/**
	 * Compares two object between this object and specified object.
	 * 
	 * @param arg
	 *            is the object that been using for compare.
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
		Money other = (Money) arg;
		return this.value == other.value && this.currency.equals(other.currency);
	}

}