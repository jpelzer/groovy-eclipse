/*
 * Copyright 2003-2010 the original author or authors.
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
package org.codehaus.groovy.eclipse.dsl.lookup;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.jdt.groovy.internal.compiler.ast.JDTResolver;

/**
 * A wrapper around the JDT resolver that caches
 * resolve requests...consider pushing this back into 
 * GroovyCompilationUnit
 * @author andrew
 * @created Nov 21, 2010
 */
public class ResolverCache {

    private final Map<String, ClassNode> nameTypeCache;
    private final JDTResolver resolver;
    
    public ResolverCache(JDTResolver resolver) {
        this.nameTypeCache = new HashMap<String, ClassNode>();
        this.resolver = resolver;
    }
    
    public ClassNode resolve(String qName) {
        ClassNode clazz = nameTypeCache.get(qName);
        if (clazz == null && resolver != null) {
            clazz = resolver.resolve(qName);
            nameTypeCache.put(qName, clazz);
        }
        
        return clazz;
    }
    
}