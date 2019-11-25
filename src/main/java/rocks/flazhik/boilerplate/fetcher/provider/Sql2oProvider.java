package rocks.flazhik.boilerplate.fetcher.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Singleton
public class Sql2oProvider implements Provider<Sql2o> {

    private final DataSource dataSource;

    @Inject
    private Sql2oProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Sql2o get() {
        return new Sql2o(dataSource);
    }
}
