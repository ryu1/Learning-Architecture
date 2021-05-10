/*
 * Copyright 2012 Dwango Co.,Ltd.
 * Created on 2012/06/04
 */
package org.sisioh.dddbase.lifecycle;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/**
 * TODO for kosuke_kawahira
 * 
 * @version $Id$
 * @author kosuke_kawahira
 * @param <V> 結果の型
 */
public class CallbackFutureTask<V> extends FutureTask<V> {
	
	Runnable callback;
	
	Executor callbackExecutoer;
	
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param callable 実行されるタスク
	 * @param callback 完了時に実行される
	 * @param callbackExecutoer コールバックを実行する
	 */
	public CallbackFutureTask(final Callable<V> callable, final AsyncCallback<V> callback,
			final Executor callbackExecutoer) {
		super(callable);
		
		this.callbackExecutoer = callbackExecutoer;
		this.callback = new Runnable() {
			
			@Override
			public void run() {
				callback.onFinished(CallbackFutureTask.this);
			}
		};
	}
	
	@Override
	public void done() {
		if (callback != null) {
			callbackExecutoer.execute(callback);
		}
	}
	
}
