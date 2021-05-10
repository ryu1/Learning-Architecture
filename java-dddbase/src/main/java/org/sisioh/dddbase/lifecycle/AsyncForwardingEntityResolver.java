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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.sisioh.dddbase.lifecycle.exception.ExecutionRuntimeException;
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
public abstract class AsyncForwardingEntityResolver<T extends Entity<ID>, ID> implements AsyncEntityResolver<T, ID> {
	
	protected final Executor executor;
	
	protected final Executor callbackExecutor;
	
	/** Future監視インターバイル */
	protected static final int SLEEP_INTERVAL_TIME = 500;
	
	
	/**
	 * インスタンスを生成する。
	 */
	protected AsyncForwardingEntityResolver() {
		executor = Executors.newCachedThreadPool();
		callbackExecutor = Executors.newCachedThreadPool();
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param executor 
	 * @param callbackExecutor 
	 */
	protected AsyncForwardingEntityResolver(Executor executor, Executor callbackExecutor) {
		this.executor = executor;
		this.callbackExecutor = callbackExecutor;
	}
	
	@Override
	public Future<Boolean> beginContains(final Identity<ID> identity) {
		return beginContains(identity, null);
	}
	
	@Override
	public Future<Boolean> beginContains(final Identity<ID> identity, final AsyncCallback<Boolean> asyncCallback) {
		return submitTask(new Callable<Boolean>() {
			
			@Override
			public Boolean call() {
				return delegate().contains(identity);
			}
		}, asyncCallback);
	}
	
	@Override
	public Future<Boolean> beginContains(T entity) {
		return beginContains(entity.getIdentity());
	}
	
	@Override
	public Future<Boolean> beginContains(T entity, AsyncCallback<Boolean> asyncCallback) {
		return beginContains(entity.getIdentity(), asyncCallback);
	}
	
	@Override
	public Future<T> beginResolve(Identity<ID> identity) {
		return beginResolve(identity, null);
	}
	
	@Override
	public Future<T> beginResolve(final Identity<ID> identity, final AsyncCallback<T> asyncCallback) {
		return submitTask(new Callable<T>() {
			
			@Override
			public T call() {
				return delegate().resolve(identity);
			}
		}, asyncCallback);
	}
	
	/**
	 * 委譲先の{@link EntityResolver}を返す。
	 * 
	 * @return 委譲先の{@link EntityResolver}
	 */
	protected abstract EntityResolver<T, ID> delegate();
	
	@Override
	public boolean endContains(Future<Boolean> future) {
		return futureGet(future);
	}
	
	@Override
	public T endResolve(Future<T> future) {
		return futureGet(future);
	}
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * <p>戻り値が取得できるまでブロックする。</p>
	 * 
	 * @param future {@link Future}
	 * @return 戻り値
	 */
	protected <R>R futureGet(Future<R> future) {
		try {
			return future.get();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new ExecutionRuntimeException(e);
		}
	}
	
	/**
	 * スレッドプールに非同期タスクを依頼する。
	 * 
	 * @param callable {@link Callable}
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 */
	protected <R>Future<R> submitTask(Callable<R> callable, AsyncCallback<R> asyncCallback) {
		CallbackFutureTask<R> callbackFutureTask = new CallbackFutureTask<R>(callable, asyncCallback, callbackExecutor);
		executor.execute(callbackFutureTask);
		return callbackFutureTask;
	}
	
}
