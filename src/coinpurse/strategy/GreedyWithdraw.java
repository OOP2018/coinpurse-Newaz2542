package coinpurse.strategy;
/**
 * This class is create to test WithdrawTest, until it passes. 
 * @author Vichakorn
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import coinpurse.Valuable;
import coinpurse.ValueComparator;

public class GreedyWithdraw implements WithdrawStrategy {
/**
 * This method is will return the withdraw amount that you want not the money in the list.
 */
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		List<Valuable> withdrawSave = new ArrayList<>();
		double amountSave = amount.getValue();
		if (amountSave <= 0 || money.size() == 0 || amount == null) {
			return null;
		}
		for (Valuable m : money) {
			if (m.getCurrency().equalsIgnoreCase(amount.getCurrency())) {
				if (amount.getValue() >= m.getValue()) {
					amountSave -= m.getValue();
					withdrawSave.add(m);
				}
			}

			if (amountSave == 0)
				break;

		}
		if (amountSave != 0)
			return null;

		return withdrawSave;
	}

}
