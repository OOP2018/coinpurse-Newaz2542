package coinpurse;

public class MoneyFactoryDemo {
	
	public static void main(String[] arg) {
		MoneyFactory factory1 = MoneyFactory.getInstance();
		MoneyFactory factory2 = MoneyFactory.getInstance();
		//should be true
		System.out.println("factory1 = factory2(True)");
		System.out.println(factory1 == factory2);
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory factory3 = MoneyFactory.getInstance();
		//should be false because we're change factory to Malaysia
		System.out.println("factory1 = factory2(False)");
		System.out.println(factory1 == factory3);
		//check method createMoney by ThaiFactory
		Valuable m = factory1.createMoney( 5 );
		System.out.println("Thai money :"+m.toString());
		Valuable m2 = factory1.createMoney("1000.0");
		System.out.println("Thai money :"+m2.toString());
		Valuable m3 = factory1.createMoney("100.0");
		System.out.println("Thai money check note has increase:"+m3.toString());
		//check method createMoney by ThaiFactory
		Valuable m4 = factory3.createMoney( 5 );
		System.out.println("Malasia money :"+m4.toString());
		Valuable m5 = factory3.createMoney( 0.05 );
		System.out.println("Malasia money :"+m5.toString());
		Valuable m6 = factory3.createMoney( 5 );
		System.out.println("Malasia money check note has increase :"+m6.toString());
	}
}
