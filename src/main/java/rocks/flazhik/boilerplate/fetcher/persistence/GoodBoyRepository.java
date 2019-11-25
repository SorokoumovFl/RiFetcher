package rocks.flazhik.boilerplate.fetcher.persistence;

import com.google.inject.Inject;
import org.sql2o.Sql2o;
import rocks.flazhik.boilerplate.fetcher.model.GoodBoy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GoodBoyRepository extends Repository<GoodBoy> {

    @Inject
    public GoodBoyRepository(Sql2o sql) {
        super(sql);
    }

    public List<GoodBoy> all() {
        return getConnection(connection -> {
            final List<GoodBoy> goodb = connection.createQuery(
                "SELECT * FROM good_boys")
                .executeAndFetch(GoodBoy.class);
            return goodb;
        });
    }

    @Override
    public UUID create(GoodBoy goodBoy) {
        return getConnection(connection -> {
            connection.createQuery(
                    "INSERT INTO good_boys VALUES (:id, :name, :breed, :tail_length)")
                    .addParameter("id", goodBoy.id)
                    .addParameter("name", goodBoy.name)
                    .addParameter("breed", goodBoy.breed)
                    .addParameter("tail_length", goodBoy.tailLength)
                    .executeUpdate();

            connection.commit();
            return goodBoy.id;
        });
    }

    @Override
    public Optional<GoodBoy> get(UUID id) {
        return getConnection(connection ->
            connection.createQuery(
                "SELECT * FROM good_boys WHERE id = :id")
                .addParameter("id", id)
                .executeAndFetch(GoodBoy.class)
                .stream()
                .findFirst());
    }

    @Override
    public void update(UUID id, GoodBoy model) {
        getConnection(connection -> {
            connection.createQuery(
                "UPDATE good_boys SET name = :name, breed = :breed, tail_length = :tail_length) WHERE id = :id")
                .addParameter("id", id)
                .addParameter("name", model.name)
                .addParameter("breed", model.name)
                .addParameter("tail_length", model.tailLength)
                .executeUpdate();

            return connection.commit();
        });
    }

    @Override
    public void delete(UUID id) {
        getConnection(connection -> {
            connection.createQuery(
                "DELETE FROM good_boys WHERE id = :id")
                .addParameter("id", id)
                .executeUpdate();

            return connection.commit();
        });
    }
}
