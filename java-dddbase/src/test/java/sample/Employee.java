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
package sample;

import org.sisioh.dddbase.model.Identity;
import org.sisioh.dddbase.model.impl.AbstractEntity;

/**
 * 従業員を表すエンティティ。
 * 
 * @version $Id$
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class Employee extends AbstractEntity<String> {
	
	private final String name;
	
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param identifier {@link Identifier}
	 * @param name 名前
	 */
	public Employee(Identity<String> identity, String name) {
		super(identity);
		this.name = name;
	}
	
	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
}
