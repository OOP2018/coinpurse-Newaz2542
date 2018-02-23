package coinpurse;

/**
 * Coin represents Money with a fixed value and currency.
 * @author Vichakorn Yotboonrueang
 */
public class Coin extends Money {
	/**
	 * Create a coin with a attribute that should have.
	 * 
	 * @param value of coin.
	 * @param curenc of coin.
	 */
	public Coin(double value, String currency) {
		super(value,currency);
	}
	/**
	 * Describing what coin is in the purse.
	 * @return describe of coin.
	 */
	@Override
	public String toString() {
		if(currency.equalsIgnoreCase("Ringgit"))
		return value*100 + "-" + "Sen";
		else 
		return value + "-" + currency;
	}

}
