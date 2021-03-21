package com.epam.rd.autotasks.spliterators;

import java.util.Spliterator;
import java.util.function.IntConsumer;

public interface RectangularSpliterator extends Spliterator.OfInt {

    @Override
    RectangularSpliterator trySplit();

    @Override
    boolean tryAdvance(IntConsumer action);

    @Override
    long estimateSize();

    static RectangularSpliterator of(int[][] array){
        throw new UnsupportedOperationException();
    }
}
