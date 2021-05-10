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

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * 別の{@link EntityIterableResolver}に委譲するための{@link EntityIterableResolver}の骨格実装。(非同期版)
 * 
 * @param <T> エンティティの型
 * @param <ID> 識別子の値の型 
 * 
 * @version $Id$
 * @author j5ik2o
 */
public abstract class AsyncForwardingEntityIteableResolver<T extends Entity<ID>, ID> extends
		AsyncForwardingEntityResolver<T, ID> implements AsyncEntityIterableResolver<T, ID> {
	
	/**
	 * インスタンスを生成する。
	 */
	protected AsyncForwardingEntityIteableResolver() {
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param asyncTaskService 
	 */
	protected AsyncForwardingEntityIteableResolver(Executor executor, Executor callbackExecutor) {
		super(executor, callbackExecutor);
	}
	
	@Override
	public Future<Iterator<T>> beginIterator() {
		return beginIterator(null);
	}
	
	@Override
	public Future<Iterator<T>> beginIterator(AsyncCallback<Iterator<T>> asyncCallback) {
		return submitTask(new Callable<Iterator<T>>() {
			
			@Override
			public Iterator<T> call() {
				return delegate().iterator();
			}
		}, asyncCallback);
	}
	
	@Override
	public Future<Map<Identity<ID>, T>> beginToMap() {
		return beginToMap(null);
	}
	
	@Override
	public Future<Map<Identity<ID>, T>> beginToMap(AsyncCallback<Map<Identity<ID>, T>> asyncCallback) {
		return submitTask(new Callable<Map<Identity<ID>, T>>() {
			
			@Override
			public Map<Identity<ID>, T> call() {
				return delegate().toMap();
			}
		}, asyncCallback);
	}
	
	@Override
	public Future<Set<T>> beginToSet() {
		return beginToSet(null);
	}
	
	@Override
	public Future<Set<T>> beginToSet(AsyncCallback<Set<T>> asyncCallback) {
		return submitTask(new Callable<Set<T>>() {
			
			@Override
			public Set<T> call() {
				return delegate().toSet();
			}
		}, asyncCallback);
	}
	
	@Override
	protected abstract EntityIterableResolver<T, ID> delegate();
	
	@Override
	public Iterator<T> endIterator(Future<Iterator<T>> future) {
		return futureGet(future);
	}
	
	@Override
	public Map<Identity<ID>, T> endToMap(Future<Map<Identity<ID>, T>> future) {
		return futureGet(future);
	}
	
	@Override
	public Set<T> endToSet(Future<Set<T>> future) {
		return futureGet(future);
	}
	
}
