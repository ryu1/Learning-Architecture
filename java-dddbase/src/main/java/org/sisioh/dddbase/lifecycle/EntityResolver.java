/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
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

import org.sisioh.dddbase.lifecycle.exception.EntityNotFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link Entity}を引き当てる責務を表すインターフェイス。
 * 
 * @param <T> エンティティの型
 * @param <ID> 識別子の値の型 
 *  
 * @author j5ik2o
 */
public interface EntityResolver<T extends Entity<ID>, ID> {
	
	/**
	 * 指定した識別子のエンティティが存在するかを返す。
	 *
	 * @param identity 識別子
	 * @return 存在する場合はtrue
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(Identity<ID> identity);
	
	/**
	 * 指定したエンティティが存在するかを返す。
	 *
	 * @param entity エンティティ
	 * @return 存在する場合はtrue
	 * @throws org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(T entity);
	
	/**
	 * 識別子に該当するエンティティを取得する。
	 *
	 * @param identity 識別子
	 * @return エンティティ
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	T resolve(Identity<ID> identity);
	
}
