/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 * Copyright 2011 Sisioh Project and others. (http://www.sisioh.org/)
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
import java.util.concurrent.Future;

import org.sisioh.dddbase.lifecycle.exception.EntityNotFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * リポジトリを表すインターフェイス。
 *
 * @param <T> エンティティの型
 * @param <ID> 識別子の値の型
 * 
 * @author junichi_kato
 */
public interface AsyncRepository<T extends Entity<ID>, ID> extends AsyncEntityResolver<T, ID> {
	
	/**
	 * 指定した識別子のエンティティを削除する。(非同期版)
	 *
	 * @param identity 識別子
	 * @return {@link Future}
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<Void> beginDelete(Identity<ID> identity);
	
	/**
	 * 指定した識別子のエンティティを削除する。(非同期版)
	 *
	 * @param identity 識別子
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<Void> beginDelete(Identity<ID> identity, AsyncCallback<Void> asyncCallback);
	
	/**
	 * 指定したエンティティを削除する。(非同期版)
	 *
	 * @param entity エンティティ
	 * @return {@link Future}
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<Void> beginDelete(T entity);
	
	/**
	 * 指定したエンティティを削除する。(非同期版)
	 *
	 * @param entity エンティティ
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<Void> beginDelete(T entity, AsyncCallback<Void> asyncCallback);
	
	/**
	 * すべてのエンティティを削除する。(非同期版)
	 * 
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Void> beginDeleteAll();
	
	/**
	 * すべてのエンティティを削除する。(非同期版)
	 * 
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Void> beginDeleteAll(AsyncCallback<Void> asyncCallback);
	
	/**
	 * 複数のエンティティを保存する。(非同期版)
	 * 
	 * @param entites 複数のエンティティ
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Void> beginStore(Collection<T> entites);
	
	/**
	 * 複数のエンティティを保存する。(非同期版)
	 * 
	 * @param entites 複数のエンティティ
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Void> beginStore(Collection<T> entites, AsyncCallback<Void> asyncCallback);
	
	/**
	 * エンティティを保存する。(非同期版)
	 *
	 * @param entity 保存する対象のエンティティ
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Void> beginStore(T entity);
	
	/**
	 * エンティティを保存する。(非同期版)
	 *
	 * @param entity 保存する対象のエンティティ
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Void> beginStore(T entity, AsyncCallback<Void> asyncCallback);
	
	/**
	 * {@link Future}が完了するまで待機する。
	 * 
	 * @param future {@link Future}
	 */
	void endDelete(Future<Void> future);
	
	/**
	 * {@link Future}が完了するまで待機する。
	 * 
	 * @param future {@link Future}
	 */
	void endDeleteAll(Future<Void> future);
	
	/**
	 * {@link Future}が完了するまで待機する。
	 * 
	 * @param future {@link Future}
	 */
	void endStore(Future<Void> future);
	
}
