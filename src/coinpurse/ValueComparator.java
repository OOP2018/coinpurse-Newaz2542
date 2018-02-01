package coinpurse;

import java.util.Comparator;

public class ValueComparator implements Comparator<Valuable> {

	@Override
	public int compare(Valuable arg0, Valuable arg1) {
		if (arg0.getValue() < arg1.getValue())
			return -1;
		if (arg0.getValue() > arg1.getValue())
			return 1;
		else
			return 0;
	}

}
