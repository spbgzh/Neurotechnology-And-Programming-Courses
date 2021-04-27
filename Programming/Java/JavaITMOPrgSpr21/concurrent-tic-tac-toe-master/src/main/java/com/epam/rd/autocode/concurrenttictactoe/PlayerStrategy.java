package com.epam.rd.autocode.concurrenttictactoe;

public interface PlayerStrategy {

    /**
     * Computes a new Move.
     * @param mark - mark to set in move.
     * @param ticTacToe - board to make a move.
     * @return a move - combination of x and y coordinates.
     */
    Move computeMove(char mark, TicTacToe ticTacToe);
}
