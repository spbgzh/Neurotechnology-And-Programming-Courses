package com.epam.rd.autotasks.chesspuzzles;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Cell {
    public final char letter;
    public final int number;

    private Cell(final char letter, final int number) {
        this.letter = letter;
        this.number = number;
    }

    private static Table<Character, Integer, Cell> cells = HashBasedTable.create(8, 8);

    static {
        for (int number = 1; number <= 8; number++) {
            for (char letter = 'A'; letter <= 'H'; letter++) {
                cells.put(letter, number, new Cell(letter, number));
            }
        }
    }

    public static Cell cell(final char letter, final int number) {
        if ('A' > letter || letter > 'H' || 1 > number || number > 8)
            return null;
        return cells.get(letter, number);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Cell cell = (Cell) o;

        if (letter != cell.letter) return false;
        return number == cell.number;
    }

    @Override
    public int hashCode() {
        int result = letter;
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(letter) + number;
    }
}
