package coinpurse;

/*
 * BankNote represents coinage (money) with a fixed value, currency and serial number.
 * @author Vichakorn Yotboonrueang
 */
public class BankNote implements Valuable {
	/** save the value of Bank note. */
	private double value;
	/** save the currency of Bank note. */
	private String currency;
	/** save the serial number of Bank note. */
	private static long nextSerialNumber = 1000000;
	private long serialNumber;

	/**
	 * this method is set value,currency, and serial number.(serial number has
	 * changed for each bank note)
	 * 
	 * @param value
	 *            of bank note
	 * @param currency
	 *            of bank note
	 */
	public BankNote(double value, String currency) {
		this.value = value;
		this.currency = currency;
		serialNumber = nextSerialNumber;
		nextSerialNumber++;
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
	 * Get value of bank note from attribute.
	 * 
	 * @return serial number of bank note.
	 */
	public long getSerial() {
		return serialNumber;
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
		BankNote other = (BankNote) arg;
		return this.value == other.value && this.currency.equals(other.currency);
	}

	/**
	 * toString returns a string description of the bank note. 
	 * @return messege that useful for bank note.
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s note[%d]", value, currency, serialNumber);
	}

}
