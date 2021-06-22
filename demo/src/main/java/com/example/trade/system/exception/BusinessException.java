package com.example.trade.system.exception;

public class BusinessException extends Exception {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Status code of the exception
	 */
	private int statusCode;

	/**
	 * Getter for StatusCode
	 * @return
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 * @param message
	 * @param statusCode
	 */
	public BusinessException(final String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	/**
	 * 
	 * @param exception
	 * @param statusCode
	 */
	public BusinessException(final Exception exception, int statusCode) {
		super(exception);
		this.statusCode = statusCode;
	}

	/**
	 * 
	 * @param message
	 * @param exception
	 * @param statusCode
	 */
	public BusinessException(final String message, final Exception exception, int statusCode) {
		super(message, exception);
		this.statusCode = statusCode;
	}
}
