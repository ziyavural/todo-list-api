package com.data.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.data.couchbase")
@EnableConfigurationProperties
public class CouchbaseProperties {

    private String couchbaseHost;
    private String couchbaseBucket;
    private String couchbaseBucketPassword;
}
