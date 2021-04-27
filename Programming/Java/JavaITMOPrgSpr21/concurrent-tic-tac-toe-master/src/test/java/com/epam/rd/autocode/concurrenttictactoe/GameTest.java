package com.epam.rd.autocode.concurrenttictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.rd.autocode.concurrenttictactoe.ThrowingConsumer.silentConsumer;
import static com.epam.rd.autocode.concurrenttictactoe.Utils.tableString;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(2)
class GameTest {

    @Test
    void testTwoNWPlayers() {
        testCase("" +
                "XOO\n" +
                "XXX\n" +
                "O  ",
                new NorthWestStrategy(), new NorthWestStrategy());
    }

    @Test
    void testTwoESPlayers() {
        testCase("" +
                        " XO\n" +
                        " XO\n" +
                        "OXX",
                new EastSouthStrategy(), new EastSouthStrategy());
    }

    @Test
    void testTwoNWvsES() {
        testCase("" +
                "XXX\n" +
                "X O\n" +
                " OO",
                new NorthWestStrategy(), new EastSouthStrategy());
    }

    @Test
    void testTwoCircular() {
        testCase("" +
                "OXO\n" +
                " XX\n" +
                " XO",
                new CircularStrategy(), new CircularStrategy());
    }

    @Test
    void testNWvsCircular() {
        testCase("" +
                "XXO\n" +
                "XOO\n" +
                "X  ",
                new NorthWestStrategy(), new CircularStrategy());

    }

    @Test
    void testESvsCircular() {
        testCase("" +
                "OOX\n" +
                " OX\n" +
                " XX",
                new EastSouthStrategy(), new CircularStrategy());
    }

    @Test
    void testTwoRowByRow() {
        testCase("" +
                "XOX\n" +
                "OXO\n" +
                "X  ",
                new RowByRowStrategy(), new RowByRowStrategy());
    }

    @Test
    void testCircularVsRowByRow() {
        testCase("" +
                "OXO\n" +
                "OXX\n" +
                "O X",
                new CircularStrategy(), new RowByRowStrategy());
    }

    @Test
    void testESVsRowByRow() {
        testCase("" +
                "OOO\n" +
                "  X\n" +
                " XX",
                new EastSouthStrategy(), new RowByRowStrategy());
    }

    private void testCase(String expected, PlayerStrategy... strategies) {
        final TicTacToe ticTacToe = TicTacToe.buildGame();

        final List<Thread> playerThreads = new ArrayList<>();
        final List<Character> marks = Arrays.asList('X', 'O');

        for (int i = 0; i < marks.size(); i++) {
            Player player = Player.createPlayer(ticTacToe, marks.get(i), strategies[i]);
            Thread thread = new Thread(player);
            playerThreads.add(thread);
        }

        playerThreads.forEach(Thread::start);
        playerThreads.forEach(silentConsumer(Thread::join));

        assertEquals(expected, tableString(ticTacToe.table()));
    }
}