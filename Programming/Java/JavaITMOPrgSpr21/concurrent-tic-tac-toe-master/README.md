# Concurrent TicTacToe 

## Description

Concurrent TicTacToe is an exercise where two concurrent threads represent players and try to edit same 3x3 table setting `X` and `O`.

Note, that game table itself (interface [`com.epam.rd.autocode.concurrenttictactoe.TicTacToe`](src/main/java/com/epam/rd/autocode/concurrenttictactoe/TicTacToe.java)) is not very aware of the game rules.
The only thing it must restrict is setting a value in a cell, that has value already.
Game table must not restrict setting several same values in a row.
Game table must not restrict setting a value after one of players has won.
Refer to methods Javadoc for details.

Implement [com.epam.rd.autocode.concurrenttictactoe.TicTacToe](src/main/java/com/epam/rd/autocode/concurrenttictactoe/TicTacToe.java) `buildGame` method to supply your implementation.

[com.epam.rd.autocode.concurrenttictactoe.Player](src/main/java/com/epam/rd/autocode/concurrenttictactoe/Player.java) is an interface representing player routine.
It extends `Runnable` interface to make it possible to submit it to a thread for concurrent execution.
Implement [com.epam.rd.autocode.concurrenttictactoe.Player](src/main/java/com/epam/rd/autocode/concurrenttictactoe/Player.java) `createPlayer` method to supply your implementation of a Player.
Each player gets a game table to play, a mark (note, `X` player always goes first), and a strategy to compute a next move.
Players are aware of the game rules and will stop execution, when some of them has won.
Players respect each other and do their turns one after another.

Game routine is as follows:
- An empty board is created.
- Two players gets marks(`X` and `O`), created board to play and strategies to compute another move.
- Both players start execution in separate threads.
- Players stop execution due to end of the game.
- Expected table state is compared to the actual one.

Note, each game may not last more than 2 seconds.  