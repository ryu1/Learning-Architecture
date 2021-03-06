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
package sample;

import org.apache.commons.lang.Validate;

/**
 * 個人名を表すバリューオブジェクト。
 * 
 * @author j5ik2o
 */
public final class PersonName {
	
	/**
	 * ファクトリメソッド。
	 * 
	 * @param firstName 名
	 * @param lastName  姓
	 * @return {@link PersonName}
	 */
	public static PersonName of(String firstName, String lastName) {
		return new PersonName(firstName, lastName);
	}
	

	private final String firstName;
	
	private final String lastName;
	

	/**
	 * インスタンスを生成する。
	 *
	 * @param firstName 名
	 * @param lastName  姓
	 */
	public PersonName(String firstName, String lastName) {
		Validate.notNull(firstName);
		Validate.notNull(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		PersonName that = (PersonName) o;
		
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
			return false;
		}
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 名を取得する。
	 * 
	 * @return 名
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 姓を取得する。
	 * 
	 * @return 姓
	 */
	public String getLastName() {
		return lastName;
	}
	
//
//	@Override
//	public int hashCode() {
//		return new HashCodeBuilder().append(firstName).append(lastName).toHashCode();
//	}
	
	@Override
	public int hashCode() {
		int result = firstName != null ? firstName.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}
	//	@Override
//	public boolean equals(Object o) {
//		if (this == o) {
//			return true;
//		}
//		if (o == null || getClass() != o.getClass()) {
//			return false;
//		}
//		PersonName that = (PersonName) o;
//		return new EqualsBuilder().append(firstName, that.firstName).append(lastName, that.lastName).isEquals();
//	}
}
