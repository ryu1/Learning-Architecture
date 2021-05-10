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
package org.sisioh.dddbase.model.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import org.junit.Test;
import org.sisioh.dddbase.model.Identity;

/**
 * {@link AbstractEntity}のためのテスト。
 * 
 * @author j5ik2o
 */
public class AbstractEntityTest {
	
	/**
	 * テスト用エンティティ。
	 * 
	 * @param <ID> 識別子の値の型
	 * @author j5ik2o
	 */
	@SuppressWarnings("serial")
	public static class StubEntity<ID> extends AbstractEntity<ID> {
		
		/**
		 * インスタンスを生成する。
		 * 
		 * @param identity {@link Identity}
		 */
		public StubEntity(Identity<ID> identity) {
			super(identity);
		}
		
	}
	
	
	/**
	 * testGetIdentifier
	 */
	@Test
	public void testGetIdentifier() {
		Identity<UUID> identifier = UUIDIdentity.randomId();
		StubEntity<UUID> entity = new StubEntity<UUID>(identifier);
		assertThat(entity.getIdentity(), is(identifier));
	}
	
	/**
	 * testNew_Null
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testNew_Null() {
		new StubEntity<UUID>(null);
	}
	
}
