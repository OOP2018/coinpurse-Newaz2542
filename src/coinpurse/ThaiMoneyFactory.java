package coinpurse;

/**
 * This class is for create Thai money
 * 
 * @author Vichakorn Yotboonrueang
 *
 */
public class ThaiMoneyFactory extends MoneyFactory {
	/**
	 * set the serial number
	 */
	private static long nextSerialNumber = 50000;

	/**
	 * This method is for create Thai money, if the value didn't match condition that mean it doesn't have that money.
	 * It have serial number different of other country.
	 */
	@Override
	public Valuable createMoney(double value) {
		if (value == 1 || value == 2 || value == 5 || value == 10) {
			Valuable coin = new Coin(value, "Baht");
			return coin;
		} else if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000) {
			Valuable banknote = new Banknote(value, "Baht", nextSerialNumber);
			nextSerialNumber++;
			return banknote;
		} else {
			System.out.println("Thai doesn't have " + value + " money.Please summit again");
			return null;
		}
	}
}