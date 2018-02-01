package coinpurse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * MoneyUtill class for test code.
 * 
 * @author Vichakorn Yotboonrueang
 *
 */
public class MoneyUtil {
	
	private static Comparator<Valuable> comp = new ValueComparator();
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
		List<Valuable> values = new ArrayList<Valuable>();
		values.addAll(coins);
		sortCoins(filterByCurrency(values, "Bath"));
	}

	/**
	 * Print coin in the list.
	 * 
	 * @param coins
	 *            is list of coin.
	 */
	public static void printCoin(List<Valuable> coins) {
		for (int i = 0; i < coins.size(); i++) {
			System.out.print(coins.get(i).toString() + "-" + coins.get(i).getCurrency());
			System.out.println("");
		}
	}

	/**
	 * Return coins or bank note with the same currency.
	 * 
	 * @param coins
	 *            is list of coin or bank note.
	 * @param Currency
	 *            of coins or bank note.
	 * @return list of coins or bank note that have the same currency.
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> coins, String Currency) {
		List<Valuable> coin = new ArrayList<Valuable>();
		for (int i = 0; i < coins.size(); i++) {
			if (coins.get(i).getCurrency().equals(Currency))
				coin.add(coins.get(i));
		}
		return coin;
	}

	/**
	 * sortCoins is for sort value of Valuable before print.
	 * 
	 * @param coins
	 *            is list of Valuable that have been sorted.
	 */
	public static void sortCoins(List<Valuable> coins) {
		java.util.Collections.sort(coins,comp);
		printCoin(coins);
	}
}
