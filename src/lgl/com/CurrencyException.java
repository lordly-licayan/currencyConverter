package lgl.com;

/*
 * Author: Lordly G. Licayan
 * Date: Feb. 19, 2020
 * Description: Currency converter.
 */

public class CurrencyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	CurrencyException(String errorMessage){
		super(errorMessage);
	}
}
