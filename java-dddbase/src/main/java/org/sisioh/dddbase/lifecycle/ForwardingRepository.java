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

import java.util.Collection;

import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * 別の{@link Repository}に委譲するための{@link Repository}の骨格実装。
 * 
 * @param <T> エンティティの型
 * @param <ID> 識別子の値の型 
 *  
 * @version $Id$
 * @author j5ik2o
 */
public abstract class ForwardingRepository<T extends Entity<ID>, ID> extends ForwardingEntityResolver<T, ID> implements
		Repository<T, ID> {
	
	@Override
	protected abstract Repository<T, ID> delegate();
	
	@Override
	public void delete(Identity<ID> identity) {
		delegate().delete(identity);
	}
	
	@Override
	public void delete(T entity) {
		delegate().delete(entity);
	}
	
	@Override
	public void deleteAll() {
		delegate().deleteAll();
	}
	
	@Override
	public void store(Collection<T> entites) {
		delegate().store(entites);
	}
	
	@Override
	public void store(T entity) {
		delegate().store(entity);
	}
	
}
