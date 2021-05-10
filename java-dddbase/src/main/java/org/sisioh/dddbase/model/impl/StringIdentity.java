/*
 * Copyright 2011 Sisioh Project and Others. (http://sisioh.org/)
 * Copyright 2011 TRICREO, Inc. (http://tricreo.jp/)
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
 * 文字列表現の識別子を表す値オブジェクト。
 * 
 * @version $Id$
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class StringIdentity extends AbstractIdentity<String> {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param id 文字列表現の識別子
	 * @return {@link StringIdentity}
	 */
	public static StringIdentity of(String id) {
		return new StringIdentity(id);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param id 文字列表現の識別子
	 */
	protected StringIdentity(String id) {
		super(id);
	}
	
}
