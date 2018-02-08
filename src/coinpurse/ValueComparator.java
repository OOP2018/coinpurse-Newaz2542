package coinpurse;

import java.util.Comparator;

public class ValueComparator implements Comparator<Valuable> {
	/**
     * Compare two objects that implement Valuable.
     * check two objects has the same objective or not, and than return Integer to sort list.
     */
	@Override
	public int compare(Valuable a, Valuable b) {
		if(a.getCurrency().equals(b.getCurrency())) {
		if (a.getValue() < b.getValue())
			return -1;
		if (a.getValue() > b.getValue())
			return 1;
		else
			return 0;
		}
		else {
			return a.getCurrency().compareTo(b.getCurrency());
		}
	}

}
