/*
 * Copyright 2007-2012 Jiemamy Project and the Others.
 * Created on 2012/05/29
 *
 * This file is part of Jiemamy.
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

import com.google.common.base.Predicate;

import org.sisioh.dddbase.lifecycle.exception.EntityMultipleFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.EntityNotFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.RepositoryRuntimeException;
import org.sisioh.dddbase.model.Entity;

/**
 * 述語に対応したエンティティリゾルバー。
 * 
 * @param <T> エンティティの型 
 * @param <ID> 識別子の型
 * @version $Id$
 * @author j5ik2o
 */
public interface PredicateEntityResolver<T extends Entity<ID>, ID> {
	
	/**
	 * 指定した述語が該当するかを返す。
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。</p>
	 *
	 * @param predicate エンティティ
	 * @return 存在する場合はtrue
	 * @throws RepositoryRuntimeException リポジトリにアクセスできない場合
	 */
	boolean contains(Predicate<T> predicate);
	
	/**
	 * 述語に該当するエンティティを取得する。
	 * <p>識別子以外を用いる複雑な条件の場合に利用する。
	 * 述語に複数のエンティティが該当する場合は最初に発見したエンティティを返す。</p>
	 *
	 * @param predicate 述語
	 * @return エンティティ
	 * @throws IllegalArgumentException       引数が不正な場合
	 * @throws EntityNotFoundRuntimeException エンティティが見つからなかった場合
	 * @throws EntityMultipleFoundRuntimeException 熟語に対して複数のエンティティが見つかった場合
	 * @throws RepositoryRuntimeException     リポジトリにアクセスできない場合
	 */
	T resolve(Predicate<T> predicate);
}
