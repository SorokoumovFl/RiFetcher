package rocks.flazhik.boilerplate.fetcher.scheduler;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.flazhik.boilerplate.fetcher.fetch.Fetcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Singleton
public class Schedulers {

    private static final Logger log = LoggerFactory.getLogger("Schedulers");

    private final ScheduledExecutorService scheduler = newScheduledThreadPool(4);
    private final Map<Fetcher, ScheduledFuture<?>> schedules = new ConcurrentHashMap<>();

    public <F extends Fetcher> void scheduleFor(F fetcher, long period, TimeUnit unit) {
        schedules.putIfAbsent(fetcher, scheduler.scheduleAtFixedRate(fetcher::fetch, 0, period, unit));
        log.info("Fetching scheduled for {} once in a {} {}", fetcher.getClass().getName(), period, unit.name());
    }

}
