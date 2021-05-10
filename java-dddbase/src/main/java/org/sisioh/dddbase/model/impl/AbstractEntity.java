/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
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

import org.apache.commons.lang.Validate;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link Entity}の骨格実装。
 * 
 * @param <ID> 識別子の値の型 
 * 
 * @version $Id$
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class AbstractEntity<ID> implements Entity<ID> {
	
	private Identity<ID> identity;
	
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param identity {@link Identity}
	 */
	public AbstractEntity(Identity<ID> identity) {
		Validate.notNull(identity);
		this.identity = identity;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public AbstractEntity<ID> clone() {
		try {
			return (AbstractEntity<ID>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("clone not supported");
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null || that instanceof Entity == false) {
			return false;
		}
		return identity.equals(((AbstractEntity<ID>) that).getIdentity());
	}
	
	@Override
	public Identity<ID> getIdentity() {
		return identity;
	}
	
	@Override
	public int hashCode() {
		return identity.hashCode();
	}
}
