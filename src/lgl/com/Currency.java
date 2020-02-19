package lgl.com;

/*
 * Author: Lordly G. Licayan
 * Date: Feb. 19, 2020
 * Description: Currency converter.
 */

import java.util.Map;

public class Currency {
	private static final String BASE_CURRENCY= "EUR";
	private static final String INVALID_CURRENCY= "Invalid Currency!";
	
	private String base;
	private String date;
	private Map<String, Double> rates;
	
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	
	public boolean isValidCurrency(String currency){
		return ((this.getBase().toUpperCase().equals(currency)) || this.getRates().containsKey(currency));
	}
	
	public double getCurrencyRate(String currency) throws CurrencyException{
		currency= currency.toUpperCase().trim();
		if (BASE_CURRENCY.equals(currency)){
			return 1.0;	
		}else if(this.rates.containsKey(currency)){
			return this.rates.get(currency);
		}
		throw new CurrencyException(INVALID_CURRENCY);	
	}
	
	public double getCurrencyConversion(double amount, String baseCurrency, String toCurrency) throws CurrencyException{
		double baseCurrencyRate= getCurrencyRate(baseCurrency);
		double toCurrencyRate= getCurrencyRate(toCurrency);
		
		return ((1/baseCurrencyRate) * amount) * toCurrencyRate;
	}
	
	public String toString(){
		StringBuffer sb= new StringBuffer();
		sb.append("\n");
		sb.append("base: ");
		sb.append(base);
		sb.append("\t");
		sb.append("date: ");
		sb.append(date);
		for (Map.Entry<String, Double> entry : rates.entrySet()) {
			sb.append("\n");
			sb.append(entry.getKey());
			sb.append(": ");
			sb.append(entry.getValue());
		}
		return sb.toString();
	}
}
