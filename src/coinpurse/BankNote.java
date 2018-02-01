package coinpurse;

public class BankNote implements Valuable{
	private double value;
	private String currency;
	private static long nextSerialNumber = 1000000;
	private long serialNumber;

	public BankNote(double value, String currency) {
		this.value = value;
		this.currency = currency;
		serialNumber = nextSerialNumber;
		nextSerialNumber++;
	}

	public double getValue() {
		return value;
	}

	public String getCurrency() {
		return currency;
	}

	public long getSerial() {
		return serialNumber;
	}

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
	
	@Override
	public String toString() {
		return String.format("%.2f-%s note[%d]",value,currency,nextSerialNumber);
	}
	
}
