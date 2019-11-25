package rocks.flazhik.boilerplate.fetcher.persistence;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import rocks.flazhik.boilerplate.fetcher.model.AbstractModel;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class Repository<M extends AbstractModel> {

    protected Sql2o sql;

    protected Repository(Sql2o sql) {
        this.sql = sql;
    }

    public abstract UUID create(M model);

    public abstract Optional<M> get(UUID id);

    public abstract void update(UUID id, M model);

    public abstract void delete(UUID id);

    protected <R> R getConnection(Function<Connection, R> handler) {
        try (Connection connection = sql.beginTransaction()) {
           return handler.apply(connection);
        }
    }

}
