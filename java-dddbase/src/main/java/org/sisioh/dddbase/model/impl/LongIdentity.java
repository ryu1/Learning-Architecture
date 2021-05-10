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
 * {@link Long}の識別子を表す値オブジェクト。
 * 
 * @version $Id$
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class LongIdentity extends AbstractIdentity<Long> {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param id {@link Long}の識別子
	 * @return {@link LongIdentity}
	 */
	public static LongIdentity of(Long id) {
		return new LongIdentity(id);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param value {@link Long}
	 */
	protected LongIdentity(Long value) {
		super(value);
	}
	
}
