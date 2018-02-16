package coinpurse;

/**
 * BankNote represents Money with a fixed value, currency and serial number.
 * @author Vichakorn Yotboonrueang
 */
public class BankNote extends Money{
	/** save the serial number of Bank note. */
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
	public BankNote(double value, String currency,long serialNumber) {
		super(value,currency);
		this.serialNumber = serialNumber;
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
	 * toString returns a string description of the bank note. 
	 * @return massage that useful for bank note.
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s note[%d]", value, currency, serialNumber);
	}

}
