/*
 * Copyright 2012 Sisioh Project and Others. (http://sisioh.org/)
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
package org.sisioh.dddbase.model.impl;

/**
 * 汎用的な識別子を表す値オブジェクト。
 * 
 * @param <ID> 識別子の値の型
 * 
 * @version $Id$
 * @author junichi_kato
 */
@SuppressWarnings("serial")
public class GenericIdentity<ID> extends AbstractIdentity<ID> {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param id 識別子の値
	 * @return {@link GenericIdentity}
	 */
	public static <T>GenericIdentity<T> of(T id) {
		return new GenericIdentity<T>(id);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param value 識別子の値
	 */
	public GenericIdentity(ID value) {
		super(value);
	}
	
}
