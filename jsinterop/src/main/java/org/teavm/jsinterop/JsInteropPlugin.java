/*
 *  Copyright 2016 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.jsinterop;

import org.teavm.backend.javascript.TeaVMJavaScriptHost;
import org.teavm.vm.spi.TeaVMHost;
import org.teavm.vm.spi.TeaVMPlugin;

public class JsInteropPlugin implements TeaVMPlugin {
    @Override
    public void install(TeaVMHost host) {
        JsInteropClassTransformer classTransformer = new JsInteropClassTransformer();
        host.add(classTransformer);

        JsInteropContext context = classTransformer.getContext();
        TeaVMJavaScriptHost jsHost = host.getExtension(TeaVMJavaScriptHost.class);
        jsHost.add(new JsInteropPostProcessor(context));
    }
}