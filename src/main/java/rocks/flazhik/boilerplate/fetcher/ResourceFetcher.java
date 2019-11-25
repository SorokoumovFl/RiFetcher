package rocks.flazhik.boilerplate.fetcher;

import com.google.common.collect.ImmutableList;
import com.google.inject.Injector;
import org.slf4j.Logger;
import rocks.flazhik.boilerplate.fetcher.fetch.GoodBoyFetcher;
import rocks.flazhik.boilerplate.fetcher.module.ConfigModule;
import rocks.flazhik.boilerplate.fetcher.module.PersistenceModule;
import rocks.flazhik.boilerplate.fetcher.module.ScheduleModule;
import rocks.flazhik.boilerplate.fetcher.resource.AbstractResource;
import rocks.flazhik.boilerplate.fetcher.resource.ExceptionResource;
import rocks.flazhik.boilerplate.fetcher.resource.GoodBoyResource;
import rocks.flazhik.boilerplate.fetcher.scheduler.Schedulers;

import java.io.IOException;
import java.util.List;

import static com.google.inject.Guice.createInjector;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;
import static spark.Spark.port;

public class ResourceFetcher {

    private static final Logger log = getLogger("Application");

    private static final int PORT = 8081;

    private static Injector injector;
    private static Schedulers schedulers;

    public static void main(String[] args) {
        try {
            new ResourceFetcher().launch();
        } catch (IOException e) {
            log.error("We're fucked, I guess", e);
        }
    }

    private void launch() throws IOException {
        initInjector();
        port(PORT);
        registerResources();
        registerFetchers();
    }

    private void initInjector() throws IOException {
        injector = createInjector(
            new ConfigModule("src/main/resources/config.yaml"),
            new ScheduleModule(),
            new PersistenceModule()
        );
    }

    private void registerResources() {
        final List<AbstractResource> resources = ImmutableList.of(
            injector.getInstance(GoodBoyResource.class),
            injector.getInstance(ExceptionResource.class)
        );

        resources.forEach(AbstractResource::register);
    }

    private void registerFetchers() {
        schedulers = injector.getInstance(Schedulers.class);
        schedulers.scheduleFor(
            injector.getInstance(GoodBoyFetcher.class), 10, SECONDS
        );
    }

}
