package rocks.flazhik.boilerplate.fetcher.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import rocks.flazhik.boilerplate.fetcher.scheduler.Schedulers;

public class ScheduleModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Schedulers.class).in(Singleton.class);
    }
}
