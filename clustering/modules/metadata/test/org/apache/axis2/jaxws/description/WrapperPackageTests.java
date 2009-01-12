/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.axis2.jaxws.description;

import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebFault;

import junit.framework.TestCase;

/**
 * Tests the request and response wrappers based on the different values used by
 * WSGen and WSImport; WSGen generates artifacts in the "jaxws" package below the
 * SEI package (which seems to be correct per JAX-WS 2.0 Spec Section 3.6.2.1) while
 * WSImport generates them in the same package of the SEI.
 */
public class WrapperPackageTests extends TestCase {
    
    public void testSEIPackageWrapper() {
        EndpointInterfaceDescription eiDesc = getEndpointInterfaceDesc(SEIPackageWrapper.class);
        OperationDescription opDesc = eiDesc.getOperation("method1");
        String requestWrapperClass = opDesc.getRequestWrapperClassName();
        assertEquals("org.apache.axis2.jaxws.description.Method1", requestWrapperClass);
        String responseWrapperClass = opDesc.getResponseWrapperClassName();
        assertEquals("org.apache.axis2.jaxws.description.Method1Response", responseWrapperClass);
        FaultDescription fDesc = opDesc.getFaultDescriptions()[0];
        String faultExceptionClass = fDesc.getExceptionClassName();
        assertEquals("org.apache.axis2.jaxws.description.Method1Exception", faultExceptionClass);
        String faultBeanClass = fDesc.getFaultBean();
        assertEquals("org.apache.axis2.jaxws.description.ExceptionBean", faultBeanClass);

    }
    
    public void testSEISubPackageWrapper() {
        EndpointInterfaceDescription eiDesc = getEndpointInterfaceDesc(SEISubPackageWrapper.class);
        OperationDescription opDesc = eiDesc.getOperation("subPackageMethod1");
        String requestWrapperClass = opDesc.getRequestWrapperClassName();
        assertEquals("org.apache.axis2.jaxws.description.jaxws.SubPackageMethod1", requestWrapperClass);
        String responseWrapperClass = opDesc.getResponseWrapperClassName();
        assertEquals("org.apache.axis2.jaxws.description.jaxws.SubPackageMethod1Response", responseWrapperClass);
        FaultDescription fDesc = opDesc.getFaultDescriptions()[0];
        String faultExceptionClass = fDesc.getExceptionClassName();
        assertEquals("org.apache.axis2.jaxws.description.SubPackageException", faultExceptionClass);
        String faultBeanClass = fDesc.getFaultBean();
        assertEquals("org.apache.axis2.jaxws.description.jaxws.SubPackageExceptionBean", faultBeanClass);

    }
    
    /*
     * Method to return the endpoint interface description for a given implementation class.
     */
    private EndpointInterfaceDescription getEndpointInterfaceDesc(Class implementationClass) {
        // Use the description factory directly; this will be done within the JAX-WS runtime
        ServiceDescription serviceDesc = 
            DescriptionFactory.createServiceDescriptionFromServiceImpl(implementationClass, null);
        assertNotNull(serviceDesc);
        
        EndpointDescription[] endpointDesc = serviceDesc.getEndpointDescriptions();
        assertNotNull(endpointDesc);
        assertEquals(1, endpointDesc.length);
        
        // TODO: How will the JAX-WS dispatcher get the appropriate port (i.e. endpoint)?  Currently assumes [0]
        EndpointDescription testEndpointDesc = endpointDesc[0];
        EndpointInterfaceDescription testEndpointInterfaceDesc = testEndpointDesc.getEndpointInterfaceDescription();
        assertNotNull(testEndpointInterfaceDesc);

        return testEndpointInterfaceDesc;
    }
}

@WebService()
class SEIPackageWrapper {
    @RequestWrapper()
    @ResponseWrapper()
    public String method1(String string) throws Method1Exception {
        return string;
    }
}

class Method1 {
    
}

class Method1Response {
    
}

@WebFault()
class Method1Exception extends Exception {
    public ExceptionBean getFaultInfo() { return null; }
}

class ExceptionBean {
    
}

@WebFault
class SubPackageException extends Exception {
    // No getFaultInfo method
}

@WebService()
class SEISubPackageWrapper {
    @RequestWrapper()
    @ResponseWrapper()
    public String subPackageMethod1(String string) throws SubPackageException {
        return string;
    }
}