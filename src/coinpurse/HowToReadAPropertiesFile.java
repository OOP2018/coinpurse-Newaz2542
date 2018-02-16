package coinpurse;
import java.util.ResourceBundle;
/**
 * This class is reading subclass of MoneyFactory than return a MoneyFactory.
 * @author Vichakorn Yotboonrueang
 *
 */
public class HowToReadAPropertiesFile {
	
	public static MoneyFactory readFile() {
	ResourceBundle bundle = ResourceBundle.getBundle( "purse" );
	String factoryclass = bundle.getString( "moneyfactory" ); 
	if(factoryclass == null) factoryclass = "ThaiMoneyFactory";
	MoneyFactory factory = null;
	try {
	    factory = (MoneyFactory)Class.forName(factoryclass).newInstance();
	} 
	catch (ClassCastException cce) {
	System.out.println(factoryclass+" is not type MoneyFactory");
	}
	catch (Exception ex) {
	System.out.println("Error creating MoneyFactory "+ex.getMessage() );
	}
	if (factory == null) System.exit(1);
	return factory;
	}
}
