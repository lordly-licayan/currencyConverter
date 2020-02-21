package lgl.com;

/*
 * Author: Lordly G. Licayan
 * Date: Feb. 19, 2020
 * Description: Currency converter.
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class Main {
	private static final String INVALID_NO_ARGS= "Invalid no. of arguments!";
	private static final String INVALID_AMOUNT= "Amount is inappropriate.";
	private static final String INVALID_CURENCY= " currency is not valid!";
	private static final String RATE_NOT_FOUND= "Rate not found!";
	private static final String CONNECTION_ERROR= "There is a connection problem.";
	
	
	public static void main(String[] args) {
		try {
			if(args.length != 3){
				System.out.println(INVALID_NO_ARGS);
				return;
			}
			
			String baseAmount= args[0];
			String baseCurrency= args[1].toUpperCase();
			String toCurrency= args[2].toUpperCase();
			double amount= Double.parseDouble(baseAmount);

			String currencyRates= Helper.getCurrencyRates();
			Currency currency= Helper.getCurrency(currencyRates);
			//System.out.println(">>List of currencies: " + currency);
			
			if(!currency.isValidCurrency(baseCurrency)){
				System.out.println(baseCurrency + INVALID_CURENCY);
				return;
			}else if(!currency.isValidCurrency(toCurrency)){
				System.out.println(toCurrency + INVALID_CURENCY);
				return;
			}
			
			double convertedAmount = currency.getCurrencyConversion(amount, baseCurrency, toCurrency);
			System.out.println(Helper.getFormattedOutput(baseAmount, baseCurrency, convertedAmount, toCurrency));
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (ProtocolException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(CONNECTION_ERROR);
		} catch (NullPointerException e){
			System.out.println(RATE_NOT_FOUND);
		} catch (NumberFormatException e){
			System.out.println(INVALID_AMOUNT);
		} catch (RuntimeException e){
			System.out.println(e.getMessage());
		} catch (CurrencyException e) {
			System.out.println(e.getMessage());
		}
	}
}
