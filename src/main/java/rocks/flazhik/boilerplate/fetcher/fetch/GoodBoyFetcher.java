package rocks.flazhik.boilerplate.fetcher.fetch;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.flazhik.boilerplate.fetcher.persistence.GoodBoyRepository;

import java.util.UUID;

import static rocks.flazhik.boilerplate.fetcher.GoodBoyGenerator.generateGoodBoy;

public class GoodBoyFetcher implements Fetcher {

    private static final Logger log = LoggerFactory.getLogger("Goodboy Fetcher");

    private final GoodBoyRepository repository;

    @Inject
    public GoodBoyFetcher(GoodBoyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetch() {
        /*
         * You can do here whatever you pleased to do, parse wherever you pleased to parse from
         * Don't forget to save a model to your repo at the end of the method.
         *
         * I have no intention to parse good boys here. Therefore I'm forced to generate good boys.
         */
        final UUID id = repository.create(generateGoodBoy());
        log.info("Good boy created, persisted with ID={}", id);
    }
}
