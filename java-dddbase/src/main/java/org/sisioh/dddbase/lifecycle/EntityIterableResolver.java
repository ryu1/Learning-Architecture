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

import java.util.Map;
import java.util.Set;

import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link Iterable}を派生したエンティティリゾルバ。
 * 
 * @param <T> エンティティの型 
 * @param <ID> エンティティの識別子の型 
 * 
 * @version $Id$
 * @author junichi_kato
 */
public interface EntityIterableResolver<T extends Entity<ID>, ID> extends EntityResolver<T, ID>, Iterable<T> {
	
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
	 * @param entity {@link Entity}
	 * @return 存在する場合はtrue
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(T entity);
	
	/**
	 * エンティティの集合をマップに変換する。
	 * 
	 * @return {@link Map}
	 */
	Map<Identity<ID>, T> toMap();
	
	/**
	 * エンティティの集合をセットに変換する。
	 * 
	 * @return {@link Set}
	 */
	Set<T> toSet();
	
}
