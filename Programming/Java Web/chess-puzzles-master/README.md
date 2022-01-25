# Spring Chess Puzzles

Chess is a famous table game. This exercise is about representing chess board states as Spring configurations.

## Details
Consider each chess piece as a particular object, that implements [ChessPiece](src/main/java/com/epam/rd/autotasks/chesspuzzles/ChessPiece.java) interface.
Then a chess board state may be represented via Spring Application Context, that contains beans of chess pieces.

Your task is to construct Spring Java Configurations to create such Application Contexts.
A configuration must provide beans of chess pieces.
The testing code use them to create an instance of [ChessBoard](src/main/java/com/epam/rd/autotasks/chesspuzzles/ChessBoard.java), which must present chess board state as a String.
Implementing this interface and its static method `of` is your responsibility as well.

You need to provide the following configurations 
(put them into [com.epam.rd.autotasks.chesspuzzles.config](src/main/java/com/epam/rd/autotasks/chesspuzzles/config) package):
- Default
- DefaultBlack
- DefaultWhite
- Puzzle01
- Puzzle02
- Puzzle03

You may refer to board states, presented in related text files in [src/test/resources/boards](src/test/resources/boards)

### State String Symbols:

|Symbols|Meaning|
|---|---| 
| . | empty cell|
| K | black king|
| Q | black queen|
| R | black rook, castle|
| B | black bishop|
| N | black knight|
| P | black pawn|
| k | white king|
| q | white queen|
| r | white rook, castle|
| b | white bishop|
| n | white knight|
| p | white pawn|

Example:
```
RNBQKBNR
PPPPPPPP
........
........
....p...
........
pppp.ppp
rnbqkbnr
```

### Cell address
To address a cell, we use a regular chess scheme, as in an example below:
```
8│RNBQKBNR
7│PPPPPPPP
6│........
5│........
4│....p...
3│........
2│pppp.ppp
1│rnbqkbnr
 └────────
  ABCDEFGH      
```