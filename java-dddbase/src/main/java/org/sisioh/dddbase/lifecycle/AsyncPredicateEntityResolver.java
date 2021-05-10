/*
 * Copyright 2007-2012 Sisioh Project and the Others.
 * Created on 2012/05/29
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

import java.util.concurrent.Future;

import com.google.common.base.Predicate;

import org.sisioh.dddbase.lifecycle.exception.EntityMultipleFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.EntityNotFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;

/**
 * 述語に対応したエンティティリゾルバー。
 * 
 * @version $Id$
 * @param <T> エンティティの型 
 * @param <ID> 識別子の型
 * @author j5ik2o
 */
public interface AsyncPredicateEntityResolver<T extends Entity<ID>, ID> {
	
	/**
	 * 指定した述語が該当するかを返す。(非同期版)
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。</p>
	 *
	 * @param predicate エンティティ
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(Predicate<T> predicate);
	
	/**
	 * 指定した述語が該当するかを返す。(非同期版)
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。</p>
	 *
	 * @param predicate エンティティ
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(Predicate<T> predicate, AsyncCallback<Boolean> asyncCallback);
	
	/**
	 * 述語に該当するエンティティを取得する。(非同期版)
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。
	 * 述語に複数のエンティティが該当する場合は最初に発見したエンティティを返す。</p>
	 *
	 * @param predicate 述語
	 * @return {@link Future}
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws EntityMultipleFoundRuntimeException 熟語に対して複数のエンティティが見つかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<T> beginResolve(Predicate<T> predicate);
	
	/**
	 * 述語に該当するエンティティを取得する。(非同期版)
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。
	 * 述語に複数のエンティティが該当する場合は最初に発見したエンティティを返す。</p>
	 *
	 * @param predicate 述語
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws EntityMultipleFoundRuntimeException 熟語に対して複数のエンティティが見つかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<T> beginResolve(Predicate<T> predicate, AsyncCallback<Boolean> asyncCallback);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return 含まれている場合はtrue
	 */
	boolean endContains(Future<Boolean> future);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return 解決したエンティティ
	 */
	T endResolve(Future<T> future);
}
