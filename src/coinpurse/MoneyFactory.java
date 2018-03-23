package coinpurse;

/**
 * This class is for create a money automatically.
 * This factory is depends on subclass, so it can create a different money.
 * @author Vichakorn Yotboonrueang
 *
 */
public abstract class MoneyFactory {
	/**
	 * set default of factory.
	 */
	private static MoneyFactory instance = new ThaiMoneyFactory();

	/**
	 * get an instance of MoneyFactory
	 * 
	 * @return an object of a subclass
	 */
	public static MoneyFactory getInstance() {
		return instance;
	}

	/**
	 * create new money object in the local currency. If the value is not a valid
	 * currency amount, then throw IllegalArgumentException .
	 * 
	 * @param value
	 * @return Object of Money
	 */
	public abstract Valuable createMoney(double value);

	/**
	 * This method is for convenience of the U.I. The default implementation of this
	 * method converts parameter to a double and calls createMoney(double)
	 * 
	 * @param value
	 * @return Object of Money
	 */
	public Valuable createMoney(String value) {
		double valueDouble = 0;
		try {
			valueDouble = Double.parseDouble(value);
		} catch (IllegalArgumentException er) {
			System.out.print("Value can't be parse to Double, please put right format");
			return null;
		}
		return createMoney(valueDouble);
	}

	/**
	 * Static method to a "set" the MoneyFactory object that is used. This is mostly
	 * for testing of MoneyFactory.
	 * 
	 * @param f
	 */
	public static void setFactory(MoneyFactory f) {
		instance = f;
	}
}
