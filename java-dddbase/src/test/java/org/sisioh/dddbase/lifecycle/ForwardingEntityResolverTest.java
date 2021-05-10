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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.sisioh.dddbase.lifecycle.impl.OnMemoryRepository;
import org.sisioh.dddbase.model.impl.StringIdentity;

import sample.Employee;

/**
 * TODO for j5ik2o
 * 
 * @version $Id$
 * @author j5ik2o
 */
public class ForwardingEntityResolverTest extends ForwardingEntityResolver<Employee, String> {
	
	OnMemoryRepository<Employee, String> repository = new OnMemoryRepository<Employee, String>();
	
	
	@Override
	protected EntityResolver<Employee, String> delegate() {
		return repository;
	}
	
	/**
	 * Test method for {@link org.sisioh.dddbase.lifecycle.ForwardingEntityResolver#resolve(org.sisioh.dddbase.model.Identity)}.
	 */
	@Test
	public void testResolveIdentifier() {
		Employee entity = new Employee(StringIdentity.of("ABC"), "KATO");
		repository.store(entity);
		Employee resolve = this.resolve(StringIdentity.of("ABC"));
		assertThat(entity, is(resolve));
		
	}
	
}
