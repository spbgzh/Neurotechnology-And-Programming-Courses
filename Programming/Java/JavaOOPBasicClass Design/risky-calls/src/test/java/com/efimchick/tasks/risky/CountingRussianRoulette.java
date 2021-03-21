package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.RussianRoulette;

public class CountingRussianRoulette extends RussianRoulette {

    int hit;

    @Override
    public int shot(final int i) throws Exception {
        hit++;
        return super.shot(i);
    }
}
