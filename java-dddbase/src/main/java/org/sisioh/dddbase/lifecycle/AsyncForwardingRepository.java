/*
 * Copyright 2007-2012 Sisioh Project and the Others.
 * Created on 2012/06/04
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
package org.sisioh.dddbase.lifecycle;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

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
public abstract class AsyncForwardingRepository<T extends Entity<ID>, ID> extends AsyncForwardingEntityResolver<T, ID>
		implements AsyncRepository<T, ID> {
	
	/**
	 * インスタンスを生成する。
	 */
	protected AsyncForwardingRepository() {
		
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param executor 
	 * @param callbackExecutor 
	 */
	protected AsyncForwardingRepository(Executor executor, Executor callbackExecutor) {
		super(executor, callbackExecutor);
	}
	
	@Override
	public Future<Void> beginDelete(Identity<ID> identity) {
		return beginDelete(identity, null);
	}
	
	@Override
	public Future<Void> beginDelete(final Identity<ID> identity, final AsyncCallback<Void> asyncCallback) {
		return submitTask(new Callable<Void>() {
			
			@Override
			public Void call() {
				delegate().delete(identity);
				return null;
			}
		}, asyncCallback);
	}
	
	@Override
	public Future<Void> beginDelete(T entity) {
		return beginDelete(entity.getIdentity());
	}
	
	@Override
	public Future<Void> beginDelete(T entity, AsyncCallback<Void> asyncCallback) {
		return beginDelete(entity.getIdentity(), asyncCallback);
	}
	
	@Override
	public Future<Void> beginDeleteAll() {
		return beginDeleteAll(null);
	}
	
	@Override
	public Future<Void> beginDeleteAll(final AsyncCallback<Void> asyncCallback) {
		return submitTask(new Callable<Void>() {
			
			@Override
			public Void call() {
				delegate().deleteAll();
				return null;
			}
		}, asyncCallback);
	}
	
	@Override
	public Future<Void> beginStore(Collection<T> entites) {
		return beginStore(entites, null);
	}
	
	@Override
	public Future<Void> beginStore(final Collection<T> entites, final AsyncCallback<Void> asyncCallback) {
		return submitTask(new Callable<Void>() {
			
			@Override
			public Void call() {
				delegate().store(entites);
				return null;
			}
		}, asyncCallback);
	}
	
	@Override
	public Future<Void> beginStore(T entity) {
		return beginStore(entity, null);
	}
	
	@Override
	public Future<Void> beginStore(final T entity, final AsyncCallback<Void> asyncCallback) {
		return submitTask(new Callable<Void>() {
			
			@Override
			public Void call() {
				delegate().store(entity);
				return null;
			}
		}, asyncCallback);
	}
	
	@Override
	protected abstract Repository<T, ID> delegate();
	
	@Override
	public void endDelete(Future<Void> future) {
		futureGet(future);
	}
	
	@Override
	public void endDeleteAll(Future<Void> future) {
		futureGet(future);
	}
	
	@Override
	public void endStore(Future<Void> future) {
		futureGet(future);
	}
	
}
