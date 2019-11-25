package rocks.flazhik.boilerplate.fetcher;

import rocks.flazhik.boilerplate.fetcher.model.GoodBoy;

import java.util.Random;
import java.util.Set;

/**
 * For demonstration purposes only
 */
public class GoodBoyGenerator {

    private static final Random RANDOM = new Random();

    private static final Set<String> GOOD_BOY_NAMES = Set.of(
        "Charlie", "Max", "Buddy", "Oscar", "Milo", "Archie"
    );

    private static final Set<String> GOOD_BOY_BREEDS = Set.of(
        "Afghan Hound", "Airedale Terrier", "Malamute", "Coonhound", "Foxhound", "Samoyed"
    );

    public static GoodBoy generateGoodBoy() {
        return new GoodBoy(oneRandomFrom(GOOD_BOY_NAMES), oneRandomFrom(GOOD_BOY_BREEDS), RANDOM.nextInt(10));
    }

    private static <T> T oneRandomFrom(Set<T> set) {
        return set.stream()
            .skip((int)(set.size() * RANDOM.nextFloat()))
            .findFirst()
            .get();
    }

}