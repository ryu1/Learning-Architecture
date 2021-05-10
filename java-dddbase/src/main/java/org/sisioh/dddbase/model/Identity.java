/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 * Copyright 2011 Sisioh Project and Others. (http://sisioh.org/)
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
package org.sisioh.dddbase.model;

import java.io.Serializable;

/**
 * 識別子を表すバリューオブジェクト。
 * 
 * @param <T> 識別子の値の型
 *
 * @author j5ik2o
 */
public interface Identity<T> extends Serializable {
	
	/**
	 * この識別子の値表現を返す。
	 *
	 * @return 識別子の値表現
	 */
	T getValue();
	
}
