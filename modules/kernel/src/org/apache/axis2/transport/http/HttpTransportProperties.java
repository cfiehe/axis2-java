package org.apache.axis2.transport.http;

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

import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.auth.AuthScope;

import java.util.Properties;
import java.util.List;
/**
 * Utility bean for setting transport properties in runtime.
 */
public class HttpTransportProperties {
    protected boolean chunked;
    protected HttpVersion httpVersion;
    protected String protocol;

    public HttpTransportProperties() {
    }

    public boolean getChunked() {
        return chunked;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setChunked(boolean chunked) {
        this.chunked = chunked;
    }

    public void setHttpVersion(HttpVersion httpVerion) {
        this.httpVersion = httpVerion;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public static class ProxyProperties {
        protected int proxyPort = -1;
        protected String domain;
        protected String passWord;
        protected String proxyHostName;
        protected String userName;

        public ProxyProperties() {
        }

        public String getDomain() {
            if (domain == null || domain.length() == 0) {
                return "anonymous";
            } else {
                return domain;
            }
        }

        public String getPassWord() {
            if (passWord == null || passWord.length() == 0) {
                return "anonymous";
            } else {
                return passWord;
            }
        }

        public String getProxyHostName() {
            return proxyHostName;
        }

        public int getProxyPort() {
            return proxyPort;
        }

        public String getUserName() {
            if (userName == null || userName.length() == 0) {
                return "anonymous";
            } else {
                return userName;
            }
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public void setProxyName(String proxyHostName) {
            this.proxyHostName = proxyHostName;
        }

        public void setProxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
    /*
    This class is responsible for holding all the necessary information needed for NTML, Digest
    and Basic Authentication. Authentication itself is handled by httpclient. User doesn't need to
    warry about what authentication mechanism it uses. Axis2 uses httpclinet's default authentication
    patterns.
    */
    public static class Authenticator{
        /*host that needed to be authenticated with*/
        private String host;
        /*port of the host that needed to be authenticated with*/
        private int port = AuthScope.ANY_PORT;
        /*Realm for authentication scope*/
        private String realm = AuthScope.ANY_REALM;
        /*Domain needed by NTCredentials for NT Domain*/
        private String domain;
        /*User for authenticate*/
        private String username;
        /*Password of the user for authenticate*/
        private String password;
        private List authSchemes;
        private boolean preempt = true;

        public static final java.lang.String NTLM = "NTLM";
        public static final java.lang.String DIGEST = "Digest";
        public static final java.lang.String BASIC = "Basic";

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getRealm() {
            return realm;
        }

        public void setRealm(String realm) {
            this.realm = realm;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setAuthSchemes(List authSchemes) {
            this.authSchemes = authSchemes;
        }

        public List getAuthSchemes() {
            return this.authSchemes;
        }
        
        public void setPreemptiveAuthentication(boolean preempt) {
            this.preempt = preempt; 
        }

        public boolean getPreemptiveAuthentication() {
            return this.preempt; 
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }
    public static class MailProperties{
        final Properties mailProperties = new Properties();

        private String password;

        public void addProperty(String key, String value) {
            mailProperties.put(key,value);
        }

        public void deleteProperty(String key) {
            mailProperties.remove(key);
        }

        public Properties getProperties() {
            return mailProperties;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
}
