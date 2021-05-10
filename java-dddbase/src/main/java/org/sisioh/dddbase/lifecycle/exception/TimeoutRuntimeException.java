/*
 * Copyright 2007-2012 Sisioh Project and the Others.
 * Created on 2012/06/04
 *
 * This file is part of Sisioh.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.sisioh.dddbase.lifecycle.exception;

/**
 * TODO for junichi
 * 
 * @version $Id$
 * @author junichi
 */
public class TimeoutRuntimeException extends RuntimeException {
	
	/**
	 * インスタンスを生成する。
	 * 
	 */
	public TimeoutRuntimeException() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message
	 */
	public TimeoutRuntimeException(String message) {
		super(message);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message
	 * @param cause
	 */
	public TimeoutRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param cause
	 */
	public TimeoutRuntimeException(Throwable cause) {
		super(cause);
	}
	
}
