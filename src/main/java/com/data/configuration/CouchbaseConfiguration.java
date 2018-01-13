package com.data.configuration;

import com.data.persistence.repository.BaseCouchBaseRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackageClasses = BaseCouchBaseRepository.class)
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "Administrator";
    }

    @Override
    protected String getBucketPassword() {
        return "123456";
    }

    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, true, false);
    }
}
