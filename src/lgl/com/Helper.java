package lgl.com;

/*
 * Author: Lordly G. Licayan
 * Date: Feb. 19, 2020
 * Description: Currency converter.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Helper {

	private static final String SPACE= " ";
	private static final String EQUALS= " = ";
	
	private static String EXCHANGE_RATE_API = "https://api.exchangeratesapi.io/latest?base=EUR";
	private static DecimalFormat decimalFormatting = new DecimalFormat("#.##");
	
	
	public static String getCurrencyRates() throws IOException{
		return getCurrencyRates(EXCHANGE_RATE_API);
	}
		
	public static String getCurrencyRates(String apiURL) throws IOException{
		URL url = new URL(apiURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		String result= br.readLine();
		
		conn.disconnect();
		return result;
	}
	
	public static Currency getCurrency(String currencyRatesJson){
		JsonParser parser = new JsonParser();
		JsonObject json  = parser.parse(currencyRatesJson).getAsJsonObject();
		
		Gson gson = new Gson();
		Currency currency= gson.fromJson(json, Currency.class);
		
		return currency;
	}
	
	public static String getCurrencyFormatter(double amount){
		decimalFormatting.setRoundingMode(RoundingMode.UP);
		
		return decimalFormatting.format(amount);		
	}
	
	public static String getFormattedOutput(String baseAmount, String baseCurrency, double convertedAmount, String toCurrency) throws CurrencyException{
		StringBuffer sb= new StringBuffer();
		sb.append(baseAmount);
		sb.append(SPACE);
		sb.append(baseCurrency);
		sb.append(EQUALS);
		sb.append(Helper.getCurrencyFormatter(convertedAmount));
		sb.append(SPACE);
		sb.append(toCurrency);
		
		return sb.toString();
	}
}
