package com.epam.rd.autocode.concurrenttictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.rd.autocode.concurrenttictactoe.ThrowingConsumer.silentConsumer;
import static com.epam.rd.autocode.concurrenttictactoe.Utils.tableString;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicTacToeTest {

    @Test
    void testEmptyTable() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();
        assertEquals("   \n   \n   ", tableString(ticTacToe.table()));
    }

    @Test
    void testSemiFilledTable() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();
        ticTacToe.setMark(0,0, 'X');
        ticTacToe.setMark(0,1, 'O');
        ticTacToe.setMark(0,2, 'X');

        assertEquals("" +
                "XOX\n" +
                "   \n" +
                "   ", tableString(ticTacToe.table()));
    }

    @Test
    void testFilledTable() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        ticTacToe.setMark(0,0, 'X');
        ticTacToe.setMark(0,1, 'O');
        ticTacToe.setMark(0,2, 'X');

        ticTacToe.setMark(1,0, 'O');
        ticTacToe.setMark(1,1, 'X');
        ticTacToe.setMark(1,2, 'O');

        ticTacToe.setMark(2,1, 'X');
        ticTacToe.setMark(2,0, 'O');
        ticTacToe.setMark(2,2, 'X');

        assertEquals("" +
                "XOX\n" +
                "OXO\n" +
                "OXX", tableString(ticTacToe.table()));
    }

    @Test
    void testTableMaybeFilledAfterWin() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        ticTacToe.setMark(0,0, 'X');
        ticTacToe.setMark(1,0, 'O');
        ticTacToe.setMark(0,1, 'X');
        ticTacToe.setMark(1,1, 'O');
        ticTacToe.setMark(0,2, 'X');
        ticTacToe.setMark(1,2, 'O');
        ticTacToe.setMark(2,0, 'X');
        ticTacToe.setMark(2,1, 'O');

        assertEquals("" +
                "XXX\n" +
                "OOO\n" +
                "XO ", tableString(ticTacToe.table()));
    }

    @Test
    void testTableMaybeFilledInAnyOrder() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        ticTacToe.setMark(0,0, 'X');
        ticTacToe.setMark(1,0, 'X');
        ticTacToe.setMark(0,1, 'X');
        ticTacToe.setMark(1,1, 'X');
        ticTacToe.setMark(0,2, 'X');
        ticTacToe.setMark(1,2, 'X');
        ticTacToe.setMark(2,0, 'X');
        ticTacToe.setMark(2,1, 'O');
        ticTacToe.setMark(2,2, 'O');

        assertEquals("" +
                "XXX\n" +
                "XXX\n" +
                "XOO", tableString(ticTacToe.table()));
    }

    @Test
    void testLastMark() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        ticTacToe.setMark(0,0, 'X');
        assertEquals('X', ticTacToe.lastMark());
        ticTacToe.setMark(1,0, 'X');
        assertEquals('X', ticTacToe.lastMark());
        ticTacToe.setMark(0,1, 'O');
        assertEquals('O', ticTacToe.lastMark());
        ticTacToe.setMark(1,1, 'O');
        assertEquals('O', ticTacToe.lastMark());
        ticTacToe.setMark(0,2, 'X');
        assertEquals('X', ticTacToe.lastMark());
        ticTacToe.setMark(1,2, 'X');
        assertEquals('X', ticTacToe.lastMark());
        ticTacToe.setMark(2,0, 'X');
        assertEquals('X', ticTacToe.lastMark());
        ticTacToe.setMark(2,1, 'O');
        assertEquals('O', ticTacToe.lastMark());
        ticTacToe.setMark(2,2, 'O');
        assertEquals('O', ticTacToe.lastMark());

        assertEquals("" +
                "XOX\n" +
                "XOX\n" +
                "XOO", tableString(ticTacToe.table()));
    }

    @Test
    void testCannotSetMarkTwice() {
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        ticTacToe.setMark(0,0, 'X');
        assertThrows(IllegalArgumentException.class, () -> ticTacToe.setMark(0,0, 'X'));
        assertThrows(IllegalArgumentException.class, () -> ticTacToe.setMark(0,0, 'O'));

        ticTacToe.setMark(1,0, 'O');
        ticTacToe.setMark(2,0, 'X');
        assertThrows(IllegalArgumentException.class, () -> ticTacToe.setMark(1,0, 'X'));
        assertThrows(IllegalArgumentException.class, () -> ticTacToe.setMark(2,0, 'O'));

        assertEquals("" +
                "X  \n" +
                "O  \n" +
                "X  ", tableString(ticTacToe.table()));

    }

    @Test
    void testTableIsCopy(){
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        ticTacToe.setMark(0,0, 'X');
        ticTacToe.setMark(1,0, 'O');
        ticTacToe.setMark(2,0, 'X');

        assertEquals("" +
                "X  \n" +
                "O  \n" +
                "X  ", tableString(ticTacToe.table()));

        ticTacToe.table()[1][1] = 'X';
        assertEquals("" +
                "X  \n" +
                "O  \n" +
                "X  ", tableString(ticTacToe.table()));

    }


}