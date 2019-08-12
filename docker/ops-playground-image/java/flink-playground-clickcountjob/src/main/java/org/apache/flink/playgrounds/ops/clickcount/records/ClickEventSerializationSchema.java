/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.playgrounds.ops.clickcount.records;


import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A Kafka {@link SerializationSchema} to serialize {@link ClickEvent}s as JSON.
 *
 */
public class ClickEventSerializationSchema implements SerializationSchema<ClickEvent> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public ClickEventSerializationSchema() {
		super();
	}

	@Override
	public byte[] serialize(ClickEvent message) {
		try {
			//if topic is null, default topic will be used
			return objectMapper.writeValueAsBytes(message);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Could not serialize record: " + message, e);
		}
	}
}
