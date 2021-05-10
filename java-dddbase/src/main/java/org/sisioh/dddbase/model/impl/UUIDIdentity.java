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
package org.sisioh.dddbase.model.impl;

import java.util.UUID;

import org.sisioh.dddbase.model.Identity;

/**
 * {@link Identity}のデフォルト実装。
 *
 * @author j5ik2o
 */
@SuppressWarnings("serial")
public class UUIDIdentity extends AbstractIdentity<UUID> {
	
	/**
	 * 文字列表現のUUIDから{@link UUIDIdentity}を生成する。
	 * 
	 * @param uuid 文字列表現のUUID
	 * @return {@link UUIDIdentity}
	 */
	public static UUIDIdentity of(String uuid) {
		return new UUIDIdentity(UUID.fromString(uuid));
	}
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param uuid {@link UUID}
	 * @return {@link UUIDIdentity}
	 */
	public static UUIDIdentity of(UUID uuid) {
		return new UUIDIdentity(uuid);
	}
	
	/**
	 * IDを自動的に生成するファクトリメソッド。
	 * 
	 * @return {@link UUIDIdentity}
	 */
	public static UUIDIdentity randomId() {
		return of(UUID.randomUUID());
	}
	
	/**
	 * インスタンスを生成する。
	 * <p>{@link UUID}を基にしてエンティティの識別子を生成する</p>
	 *
	 * @param uuid {@link UUID}
	 */
	protected UUIDIdentity(UUID uuid) {
		super(uuid);
	}
	
}
