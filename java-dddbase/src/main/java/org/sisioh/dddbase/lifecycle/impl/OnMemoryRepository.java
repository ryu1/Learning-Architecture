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
package org.sisioh.dddbase.lifecycle.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.common.base.Predicate;

import org.apache.commons.lang.Validate;
import org.sisioh.dddbase.lifecycle.EntityIterableResolver;
import org.sisioh.dddbase.lifecycle.PredicateEntityResolver;
import org.sisioh.dddbase.lifecycle.Repository;
import org.sisioh.dddbase.lifecycle.exception.EntityMultipleFoundRuntimeException;
import org.sisioh.dddbase.lifecycle.exception.EntityNotFoundRuntimeException;
import org.sisioh.dddbase.model.Entity;
import org.sisioh.dddbase.model.Identity;

/**
 * オンメモリ実装のリポジトリ。
 *
 * @param <ID> 識別子の値の型 
 * @param <T> エンティティの型
 * @author j5ik2o
 */
public class OnMemoryRepository<T extends Entity<ID>, ID> implements Repository<T, ID>, PredicateEntityResolver<T, ID>,
		EntityIterableResolver<T, ID>, Cloneable {
	
	private Map<Identity<ID>, T> entities = new ConcurrentHashMap<Identity<ID>, T>();
	
	private List<T> entityList = new CopyOnWriteArrayList<T>();
	
	
	@Override
	@SuppressWarnings("unchecked")
	public OnMemoryRepository<T, ID> clone() {
		try {
			OnMemoryRepository<T, ID> result = (OnMemoryRepository<T, ID>) super.clone();
			result.entities = toMap();
			result.entityList = toList();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new Error("clone not supported");
		}
	}
	
	@Override
	public boolean contains(Identity<ID> identity) {
		Validate.notNull(identity);
		return entities.containsKey(identity);
	}
	
	@Override
	public boolean contains(Predicate<T> predicate) {
		Validate.notNull(predicate);
		for (T entity : entities.values()) {
			if (predicate.apply(entity)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean contains(T entity) {
		Validate.notNull(entity);
		return contains(entity.getIdentity());
	}
	
	@Override
	public void delete(Identity<ID> identity) {
		Validate.notNull(identity);
		entities.remove(identity);
		for (T e : entityList) {
			if (identity.equals(e.getIdentity())) {
				entityList.remove(e);
				break;
			}
		}
	}
	
	@Override
	public void delete(T entity) {
		Validate.notNull(entity);
		delete(entity.getIdentity());
	}
	
	@Override
	public void deleteAll() {
		entities.clear();
		entityList.clear();
	}
	
	@Override
	public Iterator<T> iterator() {
		return toSet().iterator();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T resolve(Identity<ID> identity) {
		Validate.notNull(identity);
		if (contains(identity) == false) {
			throw new EntityNotFoundRuntimeException();
		}
		return (T) entities.get(identity).clone();
	}
	
	@Override
	public T resolve(Predicate<T> predicate) {
		Validate.notNull(predicate);
		T result = null;
		for (T entity : entities.values()) {
			if (predicate.apply(entity)) {
				if (result != null) {
					throw new EntityMultipleFoundRuntimeException();
				}
				result = entity;
			}
		}
		if (result == null) {
			throw new EntityNotFoundRuntimeException();
		}
		return result;
	}
	
	@Override
	public void store(Collection<T> entites) {
		Validate.notNull(entites);
		Validate.notEmpty(entites);
		for (T entity : entites) {
			store(entity);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void store(T entity) {
		Validate.notNull(entity);
		entities.put(entity.getIdentity(), (T) entity.clone());
		entityList.add(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> toList() {
		CopyOnWriteArrayList<T> result = new CopyOnWriteArrayList<T>();
		for (T e : entityList) {
			result.add((T) e.clone());
		}
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<Identity<ID>, T> toMap() {
		ConcurrentHashMap<Identity<ID>, T> result = new ConcurrentHashMap<Identity<ID>, T>();
		Set<Entry<Identity<ID>, T>> entrySet = entities.entrySet();
		for (Entry<Identity<ID>, T> entry : entrySet) {
			result.put(entry.getKey(), (T) entry.getValue().clone());
		}
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<T> toSet() {
		CopyOnWriteArraySet<T> result = new CopyOnWriteArraySet<T>();
		for (T entity : entities.values()) {
			result.add((T) entity.clone());
		}
		return result;
	}
	
}
