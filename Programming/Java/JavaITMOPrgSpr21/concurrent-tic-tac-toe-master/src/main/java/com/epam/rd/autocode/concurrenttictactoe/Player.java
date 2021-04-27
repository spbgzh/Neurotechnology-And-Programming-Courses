package com.epam.rd.autocode.concurrenttictactoe;

public interface Player extends Runnable {
    static Player createPlayer(final TicTacToe ticTacToe, final char mark, PlayerStrategy strategy) {
        return
                new igrok(ticTacToe, mark, strategy);
    }
}

class igrok implements Player {

    TicTacToe board;
    char me;
    PlayerStrategy strategy;

    public igrok(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.board = ticTacToe;
        this.me = mark;
        this.strategy = strategy;
    }

    private synchronized boolean isFinished(char[][] doska) {
        for (int i = 0; i < 3; i++) {
            if (doska[i][0] == 'O' && doska[i][1] == 'O' && doska[i][2] == 'O') return true;
            if (doska[i][0] == 'X' && doska[i][1] == 'X' && doska[i][2] == 'X') return true;
        }
        for (int i = 0; i < 3; i++) {
            if (doska[0][i] == 'O' && doska[1][i] == 'O' && doska[2][i] == 'O') return true;
            if (doska[0][i] == 'X' && doska[1][i] == 'X' && doska[2][i] == 'X') return true;
        }

        if (doska[0][0] == 'O' && doska[1][1] == 'O' && doska[2][2] == 'O') return true;
        if (doska[0][0] == 'X' && doska[1][1] == 'X' && doska[2][2] == 'X') return true;

        if (doska[0][2] == 'O' && doska[1][1] == 'O' && doska[2][0] == 'O') return true;
        if (doska[0][2] == 'X' && doska[1][1] == 'X' && doska[2][0] == 'X') return true;

        boolean fnsh = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (doska[i][j] == ' ') {
                    fnsh = false;
                    break;
                }
            }
        }
        return fnsh;
    }

    @Override
    public void run() {
        while (!isFinished(board.table())) {
            if (board.lastMark() != me) {
                Move m = strategy.computeMove(me, board);
                if ((board.table()[m.row][m.column] == ' ') && (!isFinished(board.table()))) {
                    board.setMark(m.row, m.column, me);
                }
            }
        }
        Thread.yield();
    }
}
