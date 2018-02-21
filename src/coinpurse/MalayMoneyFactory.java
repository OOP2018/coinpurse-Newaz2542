package coinpurse;
/**
 * This class is for create Malaysia money
 * 
 * @author Vichakorn Yotboonrueang
 *
 */
public class MalayMoneyFactory extends MoneyFactory {
	/**
	 * set the serial number
	 */
	private static long nextSerialNumber = 1000;
	/**
	 * This method is for create Malaysia money, if the value didn't match condition that mean it doesn't have that money.
	 * It have serial number different of other country.
	 */
	@Override
	public Valuable createMoney(double value) {
		if (value == 0.05 || value == 0.10 || value == 0.20 || value == 0.50) {
			Valuable coin = new Coin(value*100, "Sen");
			return coin;
		} else if (value == 1 || value == 2 || value == 5 || value == 10 || value == 20|| value == 50|| value == 100) {
			Valuable banknote = new BankNote(value, "Ringgit", nextSerialNumber);
			nextSerialNumber++;
			
			return banknote;
		} else
			System.out.print("Malaysia doesn't have this money.Please summit again");
		return null;
	}
}
