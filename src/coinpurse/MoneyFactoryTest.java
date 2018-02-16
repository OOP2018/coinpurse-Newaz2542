package coinpurse;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This class is for test the method of MoneyFactory with JUnit.
 * 
 * @author Vichakorn Yotboonreuang
 *
 */
public class MoneyFactoryTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	/**
	 * test getInstance() 
	 */
	@Test(timeout = 1000)
	public void instanceTest() {
		MoneyFactory factory1 = MoneyFactory.getInstance();
		MoneyFactory factory2 = MoneyFactory.getInstance();
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory factory3 = MoneyFactory.getInstance();
		assertEquals(true, factory1 == factory2);
		assertEquals(false, factory1 == factory3);
		assertEquals(false, factory2 == factory3);
	}
	/**
	 * test creatMoney() by double and String
	 */
	@Test(timeout = 1000)
	public void createMoneyTest() {
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory factoryT = MoneyFactory.getInstance();
		Valuable m = factoryT.createMoney( 10 );
		assertEquals(m.getCurrency(), "Baht");
		assertEquals(10, m.getValue(),TOL);
		Valuable m2 = factoryT.createMoney("1000.0");
		assertEquals(m.getCurrency(), "Baht");
		assertEquals(1000.0, m2.getValue(),TOL);
		
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory factoryM = MoneyFactory.getInstance();
		Valuable m3 = factoryM.createMoney( 0.05 );
		assertEquals(m3.getCurrency(), "Sen");
		assertEquals(5.0, m3.getValue(),TOL);
		Valuable m4 = factoryM.createMoney("1");
		assertEquals(m4.getCurrency(), "Ringgit");
		assertEquals(1, m4.getValue(),TOL);
	}

}
