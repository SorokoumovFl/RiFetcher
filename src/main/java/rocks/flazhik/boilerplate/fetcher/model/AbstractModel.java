package rocks.flazhik.boilerplate.fetcher.model;

import java.util.UUID;

public abstract class AbstractModel {

    public final UUID id;

    protected AbstractModel(UUID id) {
        this.id = id;
    }
}
