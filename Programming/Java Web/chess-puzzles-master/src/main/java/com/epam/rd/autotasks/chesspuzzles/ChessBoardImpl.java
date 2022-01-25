package com.epam.rd.autotasks.chesspuzzles;

import java.util.Collection;

public class ChessBoardImpl implements ChessBoard {

    Collection<ChessPiece> pieces;

    public ChessBoardImpl(Collection<ChessPiece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public String state() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number = 8; number >= 1; number--) {
            for (int letter = 'A'; letter <= 'H'; letter++) {
                int finalLetter = letter;
                int finalNumber = number;
                stringBuilder.append(
                        pieces.stream()
                                .filter(piece -> piece.getCell().letter == finalLetter && piece.getCell().number == finalNumber)
                                .findFirst()
                                .orElse(new ChessPieceImpl())
                                .toChar()
                );
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString().trim();
    }
}