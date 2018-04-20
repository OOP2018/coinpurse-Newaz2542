package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
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
	 * sortMoney is for check the compareTo method
	 */
	public static void main(String[] args) {
//		List<Coin> coins = new ArrayList<Coin>();
//		coins.add(new Coin(10.0, "Bath"));
//		coins.add(new Coin(0.5, "Bath"));
//		coins.add(new Coin(2.0, "Bath"));
//		coins.add(new Coin(1.0, "Bath"));
//		coins.add(new Coin(1.0, "Bath"));
//		coins.add(new Coin(150.0, "USD"));
//		coins.add(new Coin(10.0, "Bath"));
//		coins.add(new Coin(10.0, "Yen"));
//		List<Valuable> values = new ArrayList<Valuable>();
//		values.addAll(coins);
//		sortCoins(filterByCurrency(values, "Bath"));
		String max1 = MoneyUtil.max("dog","zebra","cat");
		System.out.println(max1);
		System.out.println("***************************");
		
		Money m1 = new Banknote(100,"Baht",0);
		Money m2 = new Banknote(200,"Baht",1);
		Money m3 = new Coin(2,"Baht");
		Money max2 = MoneyUtil.max(m1,m2,m3);
		System.out.println("Problem 2 max is :"+max2);
		System.out.println("***************************");
		
		List<Banknote> list = new ArrayList<Banknote>();
		list.add(new Banknote(10.0,"USD",0));
		list.add(new Banknote(500,"Baht",0));
		sortMoney(list);
		System.out.println("***************************");
		List<Coin> coins = Arrays.asList( new Coin(5,"Baht"), new Coin(0.1,"Ringgit"), new Coin(5,"Cent") );
		List<Coin> result = MoneyUtil.filterByCurrency(coins, "Baht");
		System.out.println("Problem 4 : "+result);
		
	}

	/**
	 * Print coin in the list.
	 * 
	 * @param coins
	 *            is list of coin.
	 */
	public static void printCoin(List<? extends Valuable> coins) {
		for (Valuable c : coins) {
			System.out.println(c);
		}
	}

	/**
	 * Return money with the same currency.
	 * 
	 * @param money
	 *            is list of coin or bank note.
	 * @param Currency
	 *            of coins or bank note.
	 * @return list of coins or bank note that have the same currency.
	 */
	public static <E extends Valuable> List<E> filterByCurrency(List<E> money, String Currency) {
		List<E> coin = new ArrayList<E>();
		for (E c : money) {
				coin.add(c);
		}
		return coin;
	}

	/**
	 * sortMoney is for sort value of Valuable before print.
	 * 
	 * @param money
	 *            is list of Valuable that have been sorted.
	 */
	public static void sortMoney(List<? extends Valuable> money) {
		java.util.Collections.sort(money,comp);
		printCoin(money);
	}
	/**
	 * Return the larger argument, based on sort order, using 
	 * the objects' own compareTo method for comparing.
	 * @param args one or more Comparable objects to compare.
	 * @return the argument that would be last if sorted the elements.
	 * @throws IllegalArgumentException if no arguments given.
	 */
	public static <E extends Comparable<? super E>> E max(E... args) throws IllegalArgumentException {
		E max = args[0];
		for(E obj : args) {
			int save = obj.compareTo(max);
			if(save > 0) {
				max = obj;
			}
		}
		return max;
		
	}
}
