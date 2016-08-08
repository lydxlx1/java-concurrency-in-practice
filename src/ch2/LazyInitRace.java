package ch2;


import net.jcip.annotations.NotThreadSafe;

/**
 * Created by lydxlx on 8/6/16.
 */
@NotThreadSafe
public abstract class LazyInitRace {
    public static class ExpensiveObject {
    }

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) // This is check-then-act
            instance = new ExpensiveObject();
        return instance;
    }
}

