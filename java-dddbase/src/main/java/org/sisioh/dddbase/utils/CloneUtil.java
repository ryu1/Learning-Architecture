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
package org.sisioh.dddbase.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.sisioh.dddbase.model.Entity;

/**
 * {@link Entity}の集合を複製するユーティリティクラス。
 * 
 * @version $Id$
 * @author daisuke
 */
public final class CloneUtil {
	
	/**
	 * 指定した {@link Collection} の要素を全て{@link Object#clone() クローン}し、
	 * それらを要素とする新しい {@link ArrayList} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link ArrayList}
	 */
	public static <E extends Entity<?>>ArrayList<E> cloneEntityArrayList(Collection<E> collection) {
		ArrayList<E> cloneCollection = Lists.newArrayListWithExpectedSize(collection.size());
		for (E element : collection) {
			@SuppressWarnings("unchecked")
			E cloneElement = (E) element.clone();
			cloneCollection.add(cloneElement);
		}
		return cloneCollection;
	}
	
	/**
	 * 指定した {@link Map} の値({@link Map#values()})を全て{@link Object#clone() クローン}し、
	 * それらを値とする新しい {@link ConcurrentHashMap} を返す。
	 * 
	 * @param <K> キーの型
	 * @param <V> 値の型
	 * @param map 元となる写像
	 * @return {@link ConcurrentHashMap}
	 */
	public static <K, V extends Entity<?>>ConcurrentHashMap<K, V> cloneEntityConcurrentHashMap(Map<K, V> map) {
		ConcurrentHashMap<K, V> cloneMap = new ConcurrentHashMap<K, V>(map.size());
		for (Entry<K, V> element : map.entrySet()) {
			@SuppressWarnings("unchecked")
			V cloneValue = (V) element.getValue().clone();
			cloneMap.put(element.getKey(), cloneValue);
		}
		return cloneMap;
	}
	
	/**
	 * 指定した {@link Map} の値({@link Map#values()})を全て{@link Object#clone() クローン}し、
	 * それらを値とする新しい {@link HashMap} を返す。
	 * 
	 * @param <K> キーの型
	 * @param <V> 値の型
	 * @param map 元となる写像
	 * @return {@link HashMap}
	 */
	public static <K, V extends Entity<?>>HashMap<K, V> cloneEntityHashMap(Map<K, V> map) {
		HashMap<K, V> cloneMap = Maps.newHashMapWithExpectedSize(map.size());
		for (Entry<K, V> element : map.entrySet()) {
			@SuppressWarnings("unchecked")
			V cloneValue = (V) element.getValue().clone();
			cloneMap.put(element.getKey(), cloneValue);
		}
		return cloneMap;
	}
	
	/**
	 * 指定した {@link Collection} の要素を全て{@link Object#clone() クローン}し、
	 * それらを要素とする新しい {@link HashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 * @since 1.0.0
	 */
	public static <E extends Entity<?>>HashSet<E> cloneEntityHashSet(Collection<E> collection) {
		HashSet<E> cloneCollection = Sets.newHashSetWithExpectedSize(collection.size());
		for (E element : collection) {
			@SuppressWarnings("unchecked")
			E cloneElement = (E) element.clone();
			cloneCollection.add(cloneElement);
		}
		return cloneCollection;
	}
	
	/**
	 * 指定した {@link Collection} の要素を全て{@link Object#clone() クローン}し、
	 * それらを要素とする新しい {@link LinkedHashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link LinkedHashSet}
	 */
	public static <E extends Entity<?>>LinkedHashSet<E> cloneEntityLinkedHashSet(Collection<E> collection) {
		LinkedHashSet<E> cloneCollection = Sets.newLinkedHashSet();
		for (E element : collection) {
			@SuppressWarnings("unchecked")
			E cloneElement = (E) element.clone();
			cloneCollection.add(cloneElement);
		}
		return cloneCollection;
	}
	
	/**
	 * 指定した {@link Collection} の要素を要素とする新しい {@link ArrayList} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E>ArrayList<E> cloneValueArrayList(Collection<E> collection) {
		return Lists.newArrayList(collection);
	}
	
	/**
	 * 指定した {@link Map} の要素を要素とする新しい {@link HashMap} を返す。
	 * 
	 * @param <K> キーの型
	 * @param <V> 値の型
	 * @param map 元となる写像
	 * @return {@link HashMap}
	 */
	public static <K, V>HashMap<K, V> cloneValueHashMap(Map<K, V> map) {
		return Maps.newHashMap(map);
	}
	
	/**
	 * 指定した {@link Collection} の要素を要素とする新しい {@link HashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E>HashSet<E> cloneValueHashSet(Collection<E> collection) {
		return Sets.newHashSet(collection);
	}
	
	/**
	 * 指定した {@link Collection} の要素を要素とする新しい {@link LinkedHashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E>LinkedHashSet<E> cloneValueLinkedHashSet(Collection<E> collection) {
		return Sets.newLinkedHashSet(collection);
	}
	
	private CloneUtil() {
	}
}
