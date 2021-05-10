/*
 * Copyright 2007-2012 Sisioh Project and the Others.
 * Created on 2012/06/03
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

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link EntityIterableResolver}の非同期版。
 * 
 * @param <T> エンティティの型 
 * @param <ID> 識別子の型
 * @version $Id$
 * @author junichi_kato
 */
public interface AsyncEntityIterableResolver<T extends Entity<ID>, ID> {
	
	/**
	 * 指定した識別子のエンティティが存在するかを返す。(非同期版)
	 *
	 * @param identity 識別子
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(Identity<ID> identity);
	
	/**
	 * 指定した識別子のエンティティが存在するかを返す。(非同期版)
	 *
	 * @param identity 識別子
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(Identity<ID> identity, AsyncCallback<Boolean> asyncCallback);
	
	/**
	 * 指定したエンティティが存在するかを返す。(非同期版)
	 *
	 * @param entity {@link Entity}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(T entity);
	
	/**
	 * 指定したエンティティが存在するかを返す。(非同期版)
	 *
	 * @param entity {@link Entity}
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(T entity, AsyncCallback<Boolean> asyncCallback);
	
	/**
	 * {@link Iterable#iterator()}の非同期版
	 * 
	 * @return {@link Future}
	 */
	Future<java.util.Iterator<T>> beginIterator();
	
	/**
	 * {@link Iterable#iterator()}の非同期版
	 * 
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 */
	Future<java.util.Iterator<T>> beginIterator(AsyncCallback<java.util.Iterator<T>> asyncCallback);
	
	/**
	 * エンティティの集合をマップに変換する。(非同期版)
	 * 
	 * @return {@link Future}
	 */
	Future<Map<Identity<ID>, T>> beginToMap();
	
	/**
	 * エンティティの集合をマップに変換する。(非同期版)
	 * 
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 */
	Future<Map<Identity<ID>, T>> beginToMap(AsyncCallback<Map<Identity<ID>, T>> asyncCallback);
	
	/**
	 * エンティティの集合をセットに変換する。(非同期版)
	 * 
	 * @return {@link Future}
	 */
	Future<Set<T>> beginToSet();
	
	/**
	 * エンティティの集合をセットに変換する。(非同期版)
	 * 
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 */
	Future<Set<T>> beginToSet(AsyncCallback<Set<T>> asyncCallback);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return エンティティが含まれている場合はtrue
	 */
	boolean endContains(Future<Boolean> future);
	
	/**
	 * {@link Iterator}を取得する。
	 * 
	 * @param future {@link Future}
	 * @return {@link Iterator}
	 */
	java.util.Iterator<T> endIterator(Future<java.util.Iterator<T>> future);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return マップ
	 */
	Map<Identity<ID>, T> endToMap(Future<Map<Identity<ID>, T>> future);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return セット
	 */
	Set<T> endToSet(Future<Set<T>> future);
}
