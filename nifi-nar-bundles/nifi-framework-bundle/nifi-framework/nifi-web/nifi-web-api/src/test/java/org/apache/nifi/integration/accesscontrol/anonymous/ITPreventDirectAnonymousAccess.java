/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nifi.integration.accesscontrol.anonymous;

import org.apache.nifi.integration.accesscontrol.OneWaySslAccessControlHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test for preventing direct anonymous access.
 */
public class ITPreventDirectAnonymousAccess extends AbstractAnonymousUserTest {

    private static OneWaySslAccessControlHelper helper;

    @BeforeAll
    public static void setup() throws Exception {
        helper = new OneWaySslAccessControlHelper();
    }

    /**
     * Attempt to create a processor anonymously.
     *
     * @throws Exception ex
     */
    @Test
    public void testDirectAnonymousAccess() throws Exception {
        final Response response = super.testCreateProcessor(helper.getBaseUrl(), helper.getUser());

        // ensure the request is not successful
        assertEquals(401, response.getStatus());
    }

    @AfterAll
    public static void cleanup() throws Exception {
        helper.cleanup();
    }
}
