/**
 * Esta clase funciona como la tasa de monto de cambio mas actual, hace la conversión y se muestra al momento de hacer el llamado,
 * en este caso de Dolar a Yen Japones
 */
public class USDaJPY extends CurrencyConverter {

    @Override
    public double convert(double monto) throws Exception {
        double exchangeRate = getExchangeRate(getSourceCurrency(), getTargetCurrency());
        return monto * exchangeRate;
    }

    @Override
    public String getSourceCurrency() {
        return "USD";
    }

    @Override
    public String getTargetCurrency() {
        return "JPY";
    }
}
