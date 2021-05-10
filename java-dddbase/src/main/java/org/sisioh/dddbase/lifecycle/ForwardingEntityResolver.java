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
package org.sisioh.dddbase.lifecycle;

import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * 別の{@link EntityResolver}に委譲するための{@link EntityResolver}の骨格実装。
 * 
 * @param <T> エンティティの型
 * @param <ID> 識別子の値の型 
 * 
 * @version $Id$
 * @author j5ik2o
 */
public abstract class ForwardingEntityResolver<T extends Entity<ID>, ID> implements EntityResolver<T, ID> {
	
	@Override
	public boolean contains(Identity<ID> identity) {
		return delegate().contains(identity);
	}
	
	@Override
	public boolean contains(T entity) {
		return delegate().contains(entity);
	}
	
	/**
	 * 委譲先の{@link EntityResolver}を返す。
	 * 
	 * @return 委譲先の{@link EntityResolver}
	 */
	protected abstract EntityResolver<T, ID> delegate();
	
	@Override
	public T resolve(Identity<ID> identity) {
		return delegate().resolve(identity);
	}
	
}
