package com.epam.rd.autotasks.spliterators;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.IntConsumer;

public class RectangularSpliterator implements Spliterator.OfInt {

    int[][] content;
    int[] itercon; // int curnumba;

    public RectangularSpliterator(int[][] array) {
        this.content = array;
        // this.curnumba = -1;
        this.itercon = Arrays.stream(array).flatMapToInt(Arrays::stream).toArray();
    }

    static RectangularSpliterator of(int[][] array) {
        return new RectangularSpliterator(array);
    }

    @Override
    public RectangularSpliterator trySplit() {
        if (this.content == null)
            return null;
        if (this.content.length * this.content[0].length <= 1)
            return null;
        int[][] fhalf = null, shalf = null;
        if (content[0].length <= content.length) {
            fhalf = new int[this.content.length / 2][this.content[0].length];
            for (int i = 0; i < this.content.length / 2; i++) {
                for (int j = 0; j < this.content[0].length; j++)
                    fhalf[i][j] = content[i][j];
            }
            shalf = new int[(this.content.length + 1) / 2][this.content[0].length];
            for (int i = this.content.length / 2; i < this.content.length; i++) {
                for (int j = 0; j < this.content[0].length; j++)
                    shalf[i - this.content.length / 2][j] = content[i][j];
            }
        } else {
            fhalf = new int[this.content.length][this.content[0].length / 2];
            for (int i = 0; i < this.content.length; i++) {
                for (int j = 0; j < this.content[0].length / 2; j++)
                    fhalf[i][j] = content[i][j];
            }
            shalf = new int[this.content.length][(this.content[0].length + 1) / 2];
            for (int i = 0; i < this.content.length; i++) {
                for (int j = this.content[0].length / 2; j < this.content[0].length; j++)
                    shalf[i][j - this.content[0].length / 2] = content[i][j];
            }
        }
        this.content = shalf;
        RectangularSpliterator newborn = new RectangularSpliterator(fhalf);
        // this.curnumba = -1;
        this.itercon = Arrays.stream(content).flatMapToInt(Arrays::stream).toArray();
        return newborn;
    }

    @Override
    public boolean tryAdvance(IntConsumer action) {
        // if(curnumba + 1 < itercon.length) {
        if (itercon.length > 0) {
            // curnumba++;
            action.accept(itercon[0]);
            int[] tmp = new int[itercon.length - 1];
            for (int y = 0; y < itercon.length - 1; y++)
                tmp[y] = itercon[y + 1];
            itercon = tmp;
            this.content = new int[1][];
            content[0] = itercon;
            return true;
        }
        return false;
    }

    @Override
    public long estimateSize() {
        // return this.content == null ? 0:this.content.length * this.content[0].length;
        return this.itercon.length;
    }

    @Override
    public int characteristics() {
        return CONCURRENT + NONNULL;
    }
}
