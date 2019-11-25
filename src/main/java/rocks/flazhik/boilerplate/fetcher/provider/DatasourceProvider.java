package rocks.flazhik.boilerplate.fetcher.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import rocks.flazhik.boilerplate.fetcher.ResourceFetcherConfig;

import javax.sql.DataSource;

@Singleton
public class DatasourceProvider implements Provider<DataSource> {

    private final ResourceFetcherConfig config;

    @Inject
    public DatasourceProvider(ResourceFetcherConfig config) {
        this.config = config;
    }

    @Override
    public DataSource get() {
        final HikariConfig dsConfig = new HikariConfig();
        dsConfig.setDriverClassName(config.database.driverClass);
        dsConfig.setJdbcUrl(config.database.url);
        dsConfig.setUsername(config.database.username);
        dsConfig.setPassword(config.database.password);
        dsConfig.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        return new HikariDataSource(dsConfig);
    }

}
