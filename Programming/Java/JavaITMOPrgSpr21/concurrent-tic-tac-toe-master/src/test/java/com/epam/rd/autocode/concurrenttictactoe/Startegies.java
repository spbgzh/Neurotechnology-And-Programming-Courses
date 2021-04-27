package com.epam.rd.autocode.concurrenttictactoe;

import java.util.stream.Stream;

class NorthWestStrategy implements PlayerStrategy {

    @Override
    public Move computeMove(final char mark, final TicTacToe ticTacToe) {
        final char[][] table = ticTacToe.table();
        for (int dia = 0; dia < 5; dia++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i + j == dia && table[i][j] == ' ') {
                        return new Move(i, j);
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }

}

class EastSouthStrategy implements PlayerStrategy {
    @Override
    public Move computeMove(final char mark, final TicTacToe ticTacToe) {
        final char[][] table = ticTacToe.table();
        for (int dia = 4; dia >= 0; dia--) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i + j == dia && table[i][j] == ' ') {
                        return new Move(i, j);
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }
}

class CircularStrategy implements PlayerStrategy {
    @Override
    public Move computeMove(final char mark, final TicTacToe ticTacToe) {
        final char[][] table = ticTacToe.table();
        return Stream.of(
                new Move(1, 1),
                new Move(0, 0),
                new Move(0, 1),
                new Move(0, 2),
                new Move(1, 2),
                new Move(2, 2),
                new Move(2, 1),
                new Move(2, 0),
                new Move(1, 0)
        )
                .filter(move -> table[move.row][move.column] == ' ')
                .findFirst().orElse(null);
    }
}

class RowByRowStrategy implements PlayerStrategy {
    @Override
    public Move computeMove(final char mark, final TicTacToe ticTacToe) {
        final char[][] table = ticTacToe.table();
        return Stream.of(
                new Move(0, 0),
                new Move(0, 1),
                new Move(0, 2),
                new Move(1, 0),
                new Move(1, 1),
                new Move(1, 2),
                new Move(2, 0),
                new Move(2, 1),
                new Move(2, 2)
        )
                .filter(move -> table[move.row][move.column] == ' ')
                .findFirst().orElse(null);
    }
}
