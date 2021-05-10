/*
 * Copyright 2007-2012 Sisioh Project and the Others.
 * Created on 2012/05/30
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
package org.sisioh.dddbase.model.impl;

/**
 * {@link Integer}の識別子を表す値オブジェクト。
 * 
 * @version $Id$
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class IntegerIdentity extends AbstractIdentity<Integer> {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param id {@link Integer}の識別子
	 * @return {@link IntegerIdentity}
	 */
	public static IntegerIdentity of(Integer id) {
		return new IntegerIdentity(id);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param value {@link Integer}
	 */
	protected IntegerIdentity(Integer value) {
		super(value);
	}
	
}
