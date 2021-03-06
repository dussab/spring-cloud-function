/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.function.registry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Mark Fisher
 */
public class InMemoryFunctionRegistry extends AbstractFunctionRegistry {

	private final ConcurrentHashMap<String, Function<?, ?>> map = new ConcurrentHashMap<>();

	@Override
	@SuppressWarnings("unchecked")
	public Function<?, ?> doLookup(String name) {
		return this.map.get(name);
	}

	@Override
	public void register(String name, String function) {
		this.map.put(name, this.compile(name, function).getFunction());
	}
}
