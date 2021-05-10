/*
 * Copyright 2007-2012 Jiemamy Project and the Others.
 * Created on 2012/05/27
 *
 * This file is part of Jiemamy.
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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link Identity}の骨格実装。
 * 
 * @param <T> 識別子の値の型 
 * 
 * @version $Id$
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class AbstractIdentity<T> implements Identity<T> {
	
	/** 識別子の値 */
	protected final T value;
	
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param value 識別子の値
	 */
	protected AbstractIdentity(T value) {
		Validate.notNull(value);
		this.value = value;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !(o instanceof Identity)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		AbstractIdentity<T> that = (AbstractIdentity<T>) o;
		return new EqualsBuilder().append(value, that.value).isEquals();
	}
	
	@Override
	public T getValue() {
		return value;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).hashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(value).toString();
	}
	
}
