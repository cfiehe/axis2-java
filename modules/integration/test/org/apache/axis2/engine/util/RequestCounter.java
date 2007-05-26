/*
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
 *
 */
package org.apache.axis2.engine.util;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceGroupContext;

public class RequestCounter {

	private static final String REQUEST_COUNT = "Request_Count";

	public OMElement getRequestCount(OMElement oe) {
		ServiceGroupContext sgc = MessageContext.getCurrentMessageContext().getServiceGroupContext();
		
		Integer requestCount = (Integer)sgc.getProperty(REQUEST_COUNT);
		if (requestCount == null) {
			requestCount = new Integer(1);
		} else {
			requestCount = new Integer(requestCount.intValue() + 1);
		}

		sgc.setProperty(REQUEST_COUNT, requestCount);
		
		QName qn = new QName("http://ws.apache.org/axis2/namespaces/", "RequestCount","axis2");
		OMElement response = OMAbstractFactory.getOMFactory().createOMElement(qn);
		response.setText(requestCount.toString());
		
		return response;
	}

}
