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
public interface Repository<T extends Entity<ID>, ID> extends EntityResolver<T, ID> {
	
	/**
	 * 指定した識別子のエンティティを削除する。
	 *
	 * @param identity 識別子
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	void delete(Identity<ID> identity);
	
	/**
	 * 指定したエンティティを削除する。
	 *
	 * @param entity エンティティ
	 * @throws EntityNotFoundRuntimeException 指定された識別子を持つエンティティが見つからなかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	void delete(T entity);
	
	/**
	 * すべてのエンティティを削除する。
	 * 
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	void deleteAll();
	
	/**
	 * 複数のエンティティを保存する。
	 * 
	 * @param entites 複数のエンティティ
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	void store(Collection<T> entites);
	
	/**
	 * エンティティを保存する。
	 *
	 * @param entity 保存する対象のエンティティ
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	void store(T entity);
	
}
