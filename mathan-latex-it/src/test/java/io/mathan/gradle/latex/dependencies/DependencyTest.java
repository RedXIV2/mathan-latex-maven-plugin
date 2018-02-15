/*
 * Copyright 2017 Matthias Hanisch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mathan.gradle.latex.dependencies;

import io.mathan.gradle.latex.AbstractIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DependencyTest extends AbstractIntegrationTest {

  public DependencyTest(Build build) {
    super(build);
  }

  @Test
  public void includeFromJar() throws Exception {
    verifier("dependencies", "dependency", "install", "jar", null);
    verifier("dependencies", "main");
  }

  @Test
  public void includeFromZip() throws Exception {
    verifier("dependencies", "zip-dependency", "install", "zip", "any");
    verifier("dependencies", "zip-main");
  }

}