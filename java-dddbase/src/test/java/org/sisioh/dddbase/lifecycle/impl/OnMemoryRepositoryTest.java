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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import com.google.common.base.Predicate;

import org.junit.Before;
import org.junit.Test;
import org.sisioh.dddbase.model.Identity;
import org.sisioh.dddbase.model.impl.AbstractEntity;
import org.sisioh.dddbase.model.impl.UUIDIdentity;

import sample.PersonName;

/**
 * {@link OnMemoryRepository}のためのテスト。
 * 
 * @author j5ik2o
 */
public class OnMemoryRepositoryTest {
	
	/**
	 * 従業員を表すエンティティ。
	 * 
	 * @author j5ik2o
	 */
	@SuppressWarnings("serial")
	public static final class Employee extends AbstractEntity<UUID> {
		
		private PersonName name;
		
		
		/**
		 * インスタンスを生成する。
		 * 
		 * @param identity {@link Identity}
		 * @param name {@link PersonName}
		 */
		public Employee(Identity<UUID> identity, PersonName name) {
			super(identity);
			this.name = name;
		}
		
		@Override
		public Employee clone() {
			return (Employee) super.clone();
		}
		
		/**
		 * {@link PersonName}を取得する。
		 * 
		 * @return {@link PersonName}
		 */
		public PersonName getName() {
			return name;
		}
		
		/**
		 * {@link PersonName}を設定する。
		 *  
		 * @param name {@link PersonName}
		 */
		public void setName(PersonName name) {
			this.name = name;
		}
	}
	
	
	private OnMemoryRepository<Employee, UUID> repository = new OnMemoryRepository<Employee, UUID>();
	
	private Identity<UUID> identifier;
	
	private Employee employee;
	
	
	/**
	 * セットアップ
	 */
	@Before
	public void setUp() {
		identifier = UUIDIdentity.randomId();
		PersonName name = PersonName.of("Junichi", "Kato");
		employee = new Employee(identifier, name);
		repository.store(employee);
	}
	
	/**
	 * 識別子でエンティティを取得できること
	 */
	@Test
	public void test01_識別子でエンティティを取得できること() {
		Employee resolve = repository.resolve(identifier);
		assertThat(resolve, is(notNullValue()));
		assertThat(resolve, is(employee));
		assertThat(resolve.getIdentity(), is(employee.getIdentity()));
	}
	
	/**
	 * 熟語でエンティティを取得できること
	 */
	@Test
	public void test02_熟語でエンティティを取得できること() {
		Employee resolve = repository.resolve(new Predicate<Employee>() {
			
			@Override
			public boolean apply(Employee input) {
				return input.getIdentity().equals(identifier);
			}
		});
		assertThat(resolve, is(notNullValue()));
		assertThat(resolve, is(employee));
		assertThat(resolve.getIdentity(), is(employee.getIdentity()));
		
		Employee predicateEmployee = new Employee(UUIDIdentity.randomId(), PersonName.of("Junichi", "KATO"));
		repository.store(predicateEmployee);
		
		Employee resolve2 = repository.resolve(new Predicate<Employee>() {
			
			@Override
			public boolean apply(Employee input) {
				return input.getName().getLastName().equals("KATO");
			}
		});
		assertThat(resolve2, is(notNullValue()));
		assertThat(resolve2, is(predicateEmployee));
		assertThat(resolve2.getIdentity(), is(predicateEmployee.getIdentity()));
	}
	
	@Test
	public void test03_toListが取得できること() {
		assertThat(repository.toList().size(), is(1));
		repository.delete(employee);
		assertThat(repository.toList().size(), is(0));
	}
	
	@Test
	public void test04_toSetが取得できること() {
		assertThat(repository.toSet().size(), is(1));
		repository.delete(employee);
		assertThat(repository.toSet().size(), is(0));
	}
	
	@Test
	public void test05_toMapが取得できること() {
		assertThat(repository.toMap().size(), is(1));
		repository.delete(employee);
		assertThat(repository.toMap().size(), is(0));
	}
	
}
