import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * El llamado de la API es usado para la validación 
 *
 */
public abstract class CurrencyConverter {
    private static final String API_KEY = "9aead8dbdcb0d1895b310c3a";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public abstract double convert(double monto) throws Exception;
    public abstract String getSourceCurrency();
    public abstract String getTargetCurrency();

    protected double getExchangeRate(String sourceCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + sourceCurrency;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());
        JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

        return conversionRates.getDouble(targetCurrency);
    }
}
