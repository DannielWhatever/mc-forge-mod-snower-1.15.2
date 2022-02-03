package dannielwhatever.mc.snower.utils;

import java.util.Random;

public final class Rand {

    private static Random randomSeed = new Random();

    public static <T> T getOne(T firstClass, T secondClass, T thirdClass) {
        int lucky = Rand.rand5();
        return lucky >= 3 ? firstClass : lucky == 0 ? thirdClass: secondClass;
    }
    public static int rand5() {
        return randomSeed.nextInt(5);
    }

    public static int ninetyPercent() {
        int result = randomSeed.nextInt(9);
        return result > 0 ? 1 : 0;
    }

    public static int fiftyPercent() {
        return randomSeed.nextInt(2);
    }

    public static int twentyPercent() {
        int result = rand5();
        return result == 1 ? 1 : 0;
    }

    public static synchronized void reInitSeed() {
        Rand.randomSeed = new Random();
    }

}
