package coinpurse;
/*
 * An interface for objects having a monetary value and currency.
 * @author Vichakorn Yotboonrueang
 */
public interface Valuable extends Comparable<Valuable> {
	public double getValue();
	public String getCurrency();
}
