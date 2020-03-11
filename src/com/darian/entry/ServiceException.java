package com.darian.entry;

/**
 * 自定义异常
 *
 * @author Darian
 * @since 2020-03-11
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 3152744386523460788L;

	/**
	 * 无参构造方法
	 */
	public ServiceException() {
		super();
	}
	
	/**
	 * 带有Message的有参构造方法
	 *
	 * @param message 信息
	 */
	public ServiceException(String message) {
		super(message);
	}
}
