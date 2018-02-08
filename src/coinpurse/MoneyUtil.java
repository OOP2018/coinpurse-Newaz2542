package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * MoneyUtill class for test code.
 * 
 * @author Vichakorn Yotboonrueang
 *
 */
public class MoneyUtil {

	/**
	 * sortCoins is for check the compareTo method
	 */
	public void sortCoins() {
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(new Coin(10.0, "Bath"));
		coins.add(new Coin(0.5, "Bath"));
		coins.add(new Coin(2.0, "Bath"));
		coins.add(new Coin(1.0, "Bath"));
		coins.add(new Coin(1.0, "Bath"));
		coins.add(new Coin(150.0, "USD"));
		coins.add(new Coin(10.0, "Bath"));
		coins.add(new Coin(10.0, "Yen"));

		sortCoins(filterByCurrency(coins, "Bath"));

	}

	/**
	 * Print coin in the list.
	 * 
	 * @param coins
	 *            is list of coin.
	 */
	public static void printCoin(List<Coin> coins) {
		for (int i = 0; i < coins.size(); i++) {
			System.out.print(coins.get(i).toString() + "-" + coins.get(i).getCurrency());
			System.out.println("");
		}
	}

	/**
	 * Return coins with the same currency.
	 * 
	 * @param coins
	 *            is list of coin.
	 * @param Currency
	 *            of coins.
	 * @return list of coins that have the same currency.
	 */
	public static List<Coin> filterByCurrency(List<Coin> coins, String Currency) {
		List<Coin> coin = new ArrayList<Coin>();
		for (int i = 0; i < coins.size(); i++) {
			if (coins.get(i).getCurrency().equals(Currency))
				coin.add(coins.get(i));
		}
		return coin;
	}

	/**
	 * sortCoins is for sort value of coin before print.
	 * 
	 * @param coins
	 *            is list of coins that have been sorted.
	 */
	public static void sortCoins(List<Coin> coins) {
		java.util.Collections.sort(coins);
		printCoin(coins);
	}
}