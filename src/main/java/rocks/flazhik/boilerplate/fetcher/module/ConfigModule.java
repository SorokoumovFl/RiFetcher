package rocks.flazhik.boilerplate.fetcher.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import rocks.flazhik.boilerplate.fetcher.ResourceFetcherConfig;

import java.io.File;
import java.io.IOException;

public class ConfigModule extends AbstractModule {

    private final ResourceFetcherConfig config;

    public ConfigModule(String path) throws IOException {
        this.config = ResourceFetcherConfig.fromYAML(new File(path));
    }

    @Provides
    ResourceFetcherConfig config() {
        return config;
    }

}
