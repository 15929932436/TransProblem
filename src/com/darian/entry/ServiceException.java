package com.darian.entry;

/**
 * �Զ����쳣
 *
 * @author Darian
 * @since 2020-03-11
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 3152744386523460788L;

	/**
	 * �޲ι��췽��
	 */
	public ServiceException() {
		super();
	}
	
	/**
	 * ����Message���вι��췽��
	 *
	 * @param message ��Ϣ
	 */
	public ServiceException(String message) {
		super(message);
	}
}
