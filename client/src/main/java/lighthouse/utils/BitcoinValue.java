package lighthouse.utils;

import org.bitcoinj.core.*;

import java.math.*;

import static lighthouse.utils.I18nUtil.*;

// TODO: Move this into bitcoinj

public class BitcoinValue {
    // Takes user input in bitcoins and yields either a value in satoshis or a NumberFormatException if out of range.
    public static long userInputToSatoshis(String userInput) throws NumberFormatException {
        BigDecimal bd = new BigDecimal(userInput.trim());
        if (bd.signum() < 0)
            throw new NumberFormatException(tr("Negative numbers not allowed"));
        final BigDecimal satoshis = bd.movePointRight(8);
        if (satoshis.scale() > 0)
            throw new NumberFormatException(tr("Fractional value units not allowed"));
        final long l = satoshis.longValue();
        if (l > NetworkParameters.MAX_MONEY.longValue())
            throw new NumberFormatException(tr("Larger than MAX_MONEY"));
        return l;
    }
}
