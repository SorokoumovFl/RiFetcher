package rocks.flazhik.boilerplate.fetcher.resource;

import static spark.Spark.exception;

public class ExceptionResource extends AbstractResource {

    @Override
    public void register() {
        exception(Exception.class, (ex, rq, rs) -> {
            rs.status(500);
            rs.body("UwU you've made a fucky wucky! A little fucko boingo! ('ω^＼)");
        });
    }
}
