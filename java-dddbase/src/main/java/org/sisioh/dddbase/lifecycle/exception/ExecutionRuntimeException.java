/*
 * Copyright 2012 Dwango Co.,Ltd.
 * Created on 2012/06/04
 */
package org.sisioh.dddbase.lifecycle.exception;

/**
 * TODO for junichi
 * 
 * @version $Id$
 * @author junichi
 */
@SuppressWarnings("serial")
public class ExecutionRuntimeException extends RuntimeException {

	/**
	 * インスタンスを生成する。
	 * 
	 */
	public ExecutionRuntimeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * インスタンスを生成する。
	 * 
	 * @param arg0
	 */
	public ExecutionRuntimeException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * インスタンスを生成する。
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public ExecutionRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * インスタンスを生成する。
	 * 
	 * @param arg0
	 */
	public ExecutionRuntimeException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
