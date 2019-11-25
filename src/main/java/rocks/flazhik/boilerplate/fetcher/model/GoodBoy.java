package rocks.flazhik.boilerplate.fetcher.model;

import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * An exhibit of how any model could look like
 */
public final class GoodBoy extends AbstractModel {

    public final String name;
    public final String breed;
    public final Integer tailLength;

    public GoodBoy(UUID id, String name, String breed, Integer tailLength) {
        super(id);
        this.name = name;
        this.breed = breed;
        this.tailLength = tailLength;
    }

    public GoodBoy(String name, String breed, Integer tailLength) {
        super(randomUUID());
        this.name = name;
        this.breed = breed;
        this.tailLength = tailLength;
    }

}
