package coinpurse.strategy;

/**
 * Test the WithdrawStrategy using JUnit. This is a JUnit 4 test suite.
 * @author Vichakorn
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.Coin;
import coinpurse.Money;
import coinpurse.Valuable;

public class WithdrawTest {
	private static String CURRENCY;
	private static WithdrawStrategy strategy;
	private static List<Valuable> money;

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		money = new ArrayList<Valuable>();
		CURRENCY = "Baht";
		strategy = new RecursiveWithdraw();

	}

	/**
	 * test when money doesn't have in purse and you withdraw it.
	 */
	@Test(timeout = 1000)
	public void testNothingWithdraw() {
		Valuable coin = new Coin(5, CURRENCY);
		Valuable bankNote = new Money(20, CURRENCY);
		money.clear();
		assertNull(strategy.withdraw(coin, money));
		assertNull(strategy.withdraw(bankNote, money));

	}
	/**
	 * Add one coin and remove it. 
	 */
	@Test(timeout = 1000)
	public void testEasyWithdraw() {
		Valuable coin = new Coin(5, CURRENCY);
		money.add(coin);
		List<Valuable> result = strategy.withdraw(new Money(5, CURRENCY), money);

		assertTrue(result != null);
		assertEquals(1, result.size());
		assertSame(coin, result.get(0));

		money.clear();
	}
	/**
	 * add 3 money and once withdraw sum of money in list.
	 */
	@Test(timeout = 1000)
	public void testMutiWithdraw() {
		money.add(new Money(20.0, CURRENCY));
		money.add(new Money(10.0, CURRENCY));
		money.add(new Money(5.0, CURRENCY));

		List<Valuable> test1 = strategy.withdraw(new Money(5, CURRENCY), money);
		assertTrue(test1.get(0).getValue() == 5);

		List<Valuable> test2 = strategy.withdraw(new Money(30, CURRENCY), money);
		assertTrue(test2.get(0).getValue() + test2.get(1).getValue() == 30);

		money.clear();
	}
	/**
	 * test when you don't have that amount of money in your list.
	 */
	@Test(timeout = 1000)
	public void testThatNotInWindraw() {
		money.add(new Money(10.0, CURRENCY));
		assertNull(strategy.withdraw(new Money(20, CURRENCY), money));
		assertNull(strategy.withdraw(new Money(12, CURRENCY), money));

		money.clear();
	}
	/**
	 * check if currency is not the same it can't be withdraw.
	 */
	@Test(timeout = 1000)
	public void testCurrency() {
		money.add(new Money(5, CURRENCY));
		money.add(new Money(10, CURRENCY));
		assertNull(strategy.withdraw(new Money(5, "Yen"), money));
		assertNull(strategy.withdraw(new Money(10, "Rupees"), money));
		assertNotNull(strategy.withdraw(new Money(5, CURRENCY), money));

	}
	/**
	 * Check the method withdraw doesn't change anything in money list.
	 */
	@Test(timeout = 1000)
	public void testWithdrawNotChangePurse() {
		money.add(new Money(20, CURRENCY));
		money.add(new Money(10, CURRENCY));
		money.add(new Money(5, CURRENCY));
		strategy.withdraw(new Money(20, CURRENCY), money);
		strategy.withdraw(new Money(10, CURRENCY), money);
		strategy.withdraw(new Money(5, CURRENCY), money);
		assertTrue(money.get(0).getValue() == 20);
		assertTrue(money.get(1).getValue() == 10);
		assertTrue(money.get(2).getValue() == 5);
	}

}
