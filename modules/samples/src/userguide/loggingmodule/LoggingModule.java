/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package userguide.loggingmodule;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.modules.Module;

public class LoggingModule implements Module {


    // initialize the module
    public void init(ConfigurationContext configContext, AxisModule module) throws AxisFault {
    }

    public void engageNotify(AxisDescription axisDescription) throws AxisFault {
    }

    // shutdown the module
    public void shutdown(ConfigurationContext configurationContext) throws AxisFault {
    }
    
    public String[] getPolicyNamespaces() {
    	return null;	
    }
}
