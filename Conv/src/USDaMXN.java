/**
 * Esta clase funciona como la tasa de monto de cambio mas actual, hace la conversión y se muestra al momento de hacer el llamado
 * , en este caso de Dolar a Peso Mexicano
 */
public class USDaMXN extends CurrencyConverter {

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
        return "MXN";
    }
}
