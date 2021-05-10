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

import java.util.concurrent.Future;

import org.sisioh.dddbase.lifecycle.exception.EntityNotFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link EntityResolver}の非同期版。
 * 
 * @param <T> エンティティの型
 * @param <ID> 識別子の型
 * 
 * @version $Id$
 * @author junichi_kato
 */
public interface AsyncEntityResolver<T extends Entity<ID>, ID> {
	
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
	 * @param entity エンティティ
	 * @return {@link Future}
	 * @throws org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(T entity);
	
	/**
	 * 指定したエンティティが存在するかを返す。(非同期版)
	 *
	 * @param entity エンティティ
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	Future<Boolean> beginContains(T entity, AsyncCallback<Boolean> asyncCallback);
	
	/**
	 * 識別子に該当するエンティティを取得する。(非同期版)
	 *
	 * @param identity 識別子
	 * @return {@link Future}
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<T> beginResolve(Identity<ID> identity);
	
	/**
	 * 識別子に該当するエンティティを取得する。(非同期版)
	 *
	 * @param identity 識別子
	 * @param asyncCallback {@link AsyncCallback}
	 * @return {@link Future}
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	Future<T> beginResolve(Identity<ID> identity, AsyncCallback<T> asyncCallback);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return エンティティが含まれている場合はtrue
	 */
	boolean endContains(Future<Boolean> future);
	
	/**
	 * {@link Future}から戻り値を取得する。
	 * 
	 * @param future {@link Future}
	 * @return エンティティ
	 */
	T endResolve(Future<T> future);
}
