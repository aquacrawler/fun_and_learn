package com.funlearn.config;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EktorpConfiguration {
    private static final Logger LOG = Logger.getLogger(EktorpConfiguration.class.getName());
    
    public HttpClient httpClient() {
        LOG.info("Instantiating HttpClient...");
        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984")
                    .build();
            return httpClient;
        } catch (MalformedURLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return httpClient;
    }
    
    
    public CouchDbInstance couchDbInstance() {
        LOG.info("Instantiating CouchDbInstance...");
        return new StdCouchDbInstance(httpClient());
    }
    
    
    @Bean
    public CouchDbConnector couchDbConnector() {
        LOG.info("Instantiating CouchDbConnector...");
        return couchDbInstance().createConnector("fun_learn", true);
    }
    
    
}
