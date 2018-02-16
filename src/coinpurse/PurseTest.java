package coinpurse;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Purse using JUnit. This is a JUnit 4 test suite.
 * 
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4, but you have to
 * tell the IDE to add it to your project as a "Library". To run these tests,
 * right click on this file (in Project panel) and choose Run As -> JUnit test
 * 
 * @author Resident Evil
 * @version 2018.01.19
 */
public class PurseTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	private static final String CURRENCY = "BTC";

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		// nothing to initialize
	}

	/** Make a coin with the default currency. To save typing "new Coin(...)" */
	private Coin makeCoin(double value) {
		return new Coin(value, CURRENCY);
	}

	/**
	 * Make a bank note with the default currency. To save typing "new Coin(...)"
	 */

	/** Easy test that the Purse constructor is working. */
	@Test
	public void testConstructor() {
		Purse purse = new Purse(3);
		assertEquals(3, purse.getCapacity());
		assertEquals(false, purse.isFull());
		assertEquals(0, purse.count());
	}
	/** Test the BankNote. */
	@Test
	public void testBankNote() {
		// test insert
		Purse purse = new Purse(4);
		MoneyFactory makeNote = MoneyFactory.getInstance();
		Valuable money1 = makeNote.createMoney(50);
		Valuable money2 = makeNote.createMoney(100);
		Valuable money3 = makeNote.createMoney(20);
		Valuable money4 = makeNote.createMoney(50);
		assertTrue(purse.insert(money1));
		assertTrue(purse.insert(money3));
		assertTrue(purse.insert(money2));
		assertTrue(purse.insert(money4));
		assertEquals(4, purse.count());
		// purse is full so insert should fail
		assertFalse(purse.insert(makeNote.createMoney(1)));
		// test insert full
		assertFalse(purse.insert(makeNote.createMoney(1)));
		// testEquals
		assertFalse(money1.equals(money2));
		assertFalse(money2.equals(money4));
		assertTrue(money1.equals(money4));
		// testgetBalance()
		assertEquals(220, purse.getBalance(), TOL);
		// test withdraw
		purse.withdraw(100);
		assertEquals(120, purse.getBalance(), TOL);
		purse.withdraw(50);
		assertEquals(70, purse.getBalance(), TOL);
	}

	/** Insert some coins. Easy test. */
	@Test
	public void testInsert() {
		Purse purse = new Purse(3);
		Coin coin1 = makeCoin(5);
		Coin coin2 = makeCoin(10);
		Coin coin3 = makeCoin(1);
		assertTrue(purse.insert(coin1));
		assertTrue(purse.insert(coin3));
		assertTrue(purse.insert(coin2));
		assertEquals(3, purse.count());
		// purse is full so insert should fail
		assertFalse(purse.insert(makeCoin(1)));
	}

	/** Insert should reject coin with no value. */
	@Test
	public void testInsertNoValue() {
		Purse purse = new Purse(3);
		Coin fakeCoin = new Coin(0, CURRENCY);
		assertFalse(purse.insert(fakeCoin));
	}

	@Test(timeout = 1000)
	public void testIsFull() { // borderline case (capacity 1)
		Purse purse = new Purse(1);
		assertFalse(purse.isFull());
		purse.insert(makeCoin(1));
		assertTrue(purse.isFull());
		// real test
		int capacity = 4;
		purse = new Purse(capacity);
		for (int k = 1; k <= capacity; k++) {
			assertFalse(purse.isFull());
			purse.insert(makeCoin(k));
		}
		// should be full now
		assertTrue(purse.isFull());
		assertFalse(purse.insert(makeCoin(5)));
	}

	/**
	 * Should be able to insert same coin many times, since spec doesn't say
	 * anything about this.
	 */
	@Test(timeout = 1000)
	public void testInsertSameCoin() {
		int capacity = 5;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		MoneyFactory coin = MoneyFactory.getInstance();
		assertTrue(purse.insert(coin.createMoney(value)));
		assertTrue(purse.insert(coin.createMoney(value))); // should be allowed
		assertTrue(purse.insert(coin.createMoney(value))); // should be allowed
		assertTrue(purse.insert(coin.createMoney(value))); // should be allowed
		assertTrue(purse.insert(coin.createMoney(value))); // should be allowed
		assertEquals(purse.getBalance(), 5 * value, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout = 1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double[] values = { 1, 20, 0.5, 10 }; // values of coins we will insert

		for (double value : values) {
			Coin coin = makeCoin(value);
			assertTrue(purse.insert(coin));
			assertEquals(value, purse.getBalance(), TOL);
			Valuable[] result = purse.withdraw(value);
			assertTrue(result != null);
			assertEquals(1, result.length);
			assertSame(coin, result[0]); // should be same object
			assertEquals(0, purse.getBalance(), TOL);
		}
	}

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout = 1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
		Coin[] coins = { makeCoin(5.0), makeCoin(10.0), makeCoin(1.0), makeCoin(5.0) };
		// insert them all
		for (Coin coin : coins)
			assertTrue(purse.insert(coin));

		double amount1 = coins[1].getValue() + coins[3].getValue();
		double amount2 = coins[0].getValue() + coins[2].getValue();
		assertEquals(amount1 + amount2, purse.getBalance(), TOL);

		Valuable[] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL);

		assertEquals(amount2, purse.getBalance(), TOL);
		Valuable[] wd2 = purse.withdraw(amount2);

		// should be empty now
		assertEquals(0, purse.getBalance(), TOL);
	}

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout = 1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Coin> coins = Arrays.asList(makeCoin(1.0), makeCoin(0.5), makeCoin(10.0), makeCoin(0.25),
				makeCoin(5.0));
		// num = number of coins to insert and then withdraw
		for (int num = 1; num <= coins.size(); num++) {
			double amount = 0.0;
			List<Coin> subList = coins.subList(0, num);
			for (Coin c : subList) {
				purse.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals(amount, purse.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s", amount,
					Arrays.toString(subList.toArray()));
			assertNotNull(errmsg, result);
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals(0.0, purse.getBalance(), TOL);
		}
	}

	@Test(timeout = 1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull(purse.withdraw(1));
		purse.insert(makeCoin(20));
		assertNull(purse.withdraw(1));
		assertNull(purse.withdraw(19));
		assertNull(purse.withdraw(21));
		purse.insert(makeCoin(20)); // now it has 20 + 20
		assertNull(purse.withdraw(30));
	}

	/**
	 * Sum the value of some money.
	 * 
	 * @param result
	 *            array of Valuable
	 * @return sum of values of the money
	 */
	private double sum(Valuable[] result) {
		if (result == null)
			return 0.0;
		double sum = 0;
		for (Valuable c : result)
			if (c != null)
				sum += c.getValue();
		return sum;
	}
}
