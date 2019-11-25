package rocks.flazhik.boilerplate.fetcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static rocks.flazhik.boilerplate.fetcher.Json.mapper;

public class ResourceFetcherConfig {

    public final DatabaseConfig database;

    public ResourceFetcherConfig(@JsonProperty("database") DatabaseConfig database) {
        this.database = database;
    }

    public static class DatabaseConfig {

        public final String driverClass;
        public final String url;
        public final String username;
        public final String password;

        public DatabaseConfig(@JsonProperty("driver-class") String driverClass,
                              @JsonProperty("url") String url,
                              @JsonProperty("username") String username,
                              @JsonProperty("password") String password) {
            this.driverClass = driverClass;
            this.url = url;
            this.username = username;
            this.password = password;
        }
    }

    public static final ResourceFetcherConfig fromYAML(File file) throws IOException {
        try (final FileInputStream stream = new FileInputStream(file)) {
            return mapper().readValue(new YAMLFactory().createParser(stream), ResourceFetcherConfig.class);
        }
    }

}
