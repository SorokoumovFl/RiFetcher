package rocks.flazhik.boilerplate.fetcher;

import com.google.inject.Inject;
import rocks.flazhik.boilerplate.fetcher.persistence.GoodBoyRepository;
import spark.Route;

import java.util.UUID;

import static rocks.flazhik.boilerplate.fetcher.Json.toJson;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;

public class GoodBoyResource extends AbstractResource {

    private final GoodBoyRepository goodBoyRepository;

    @Inject
    public GoodBoyResource(GoodBoyRepository goodBoyRepository) {
        this.goodBoyRepository = goodBoyRepository;
    }

    @Override
    public void register() {

        path("/goodboys", () -> {
            get("/all", getAllGoodBoys());
            delete("/:id", deleteGoodBoy());
        });
    }

    private Route getAllGoodBoys() {
        return (rq, rs) -> toJson(goodBoyRepository.all());
    }

    private Route deleteGoodBoy() {
        return (rq, rs) -> {
            UUID id = UUID.fromString(rq.params("id"));
            goodBoyRepository.delete(id);
            rs.status(204);
            return "";
        };
    }
}
