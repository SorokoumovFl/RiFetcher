package rocks.flazhik.boilerplate.fetcher.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.sql2o.Sql2o;
import rocks.flazhik.boilerplate.fetcher.persistence.GoodBoyRepository;
import rocks.flazhik.boilerplate.fetcher.provider.DatasourceProvider;
import rocks.flazhik.boilerplate.fetcher.provider.Sql2oProvider;

import javax.sql.DataSource;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataSource.class).toProvider(DatasourceProvider.class).in(Singleton.class);
        bind(Sql2o.class).toProvider(Sql2oProvider.class).in(Singleton.class);

        bind(GoodBoyRepository.class).in(Singleton.class);
    }
}
