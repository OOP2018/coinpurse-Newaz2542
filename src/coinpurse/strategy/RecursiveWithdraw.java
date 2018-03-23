package coinpurse.strategy;
/**
 * This class is WithdrawStrategy by using recursion 
 * @author Vichakorn
 */
import java.util.ArrayList;
import java.util.List;

import coinpurse.Money;
import coinpurse.Valuable;

public class RecursiveWithdraw implements WithdrawStrategy {
/**
 * This method is a recursive withdraw.
 */
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		List<Valuable> withdrawSave;
		double amountSave = amount.getValue();
		if (amountSave == 0)
			return new ArrayList<>();
		if (money.size() == 0)
			return null;

		Valuable first = money.get(0);
		if (money.get(0).getCurrency().equals(amount.getCurrency())) {
			Valuable remain = new Money(amountSave - first.getValue(), amount.getCurrency());
			withdrawSave = withdraw(remain,money.subList(1, money.size()));
			if (withdrawSave != null) {
				withdrawSave.add(first);
				return withdrawSave;
			}
			return withdraw(amount, money.subList(1, money.size()));
		}
		return null;
	}

}
