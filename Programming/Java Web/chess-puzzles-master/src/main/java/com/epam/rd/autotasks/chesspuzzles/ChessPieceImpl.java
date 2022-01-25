package com.epam.rd.autotasks.chesspuzzles;

public class ChessPieceImpl implements ChessPiece {

    private Cell cell;
    private char piece;

    public ChessPieceImpl(Cell cell, char piece) {
        this.cell = cell;
        this.piece = piece;
    }

    public ChessPieceImpl(char letter, int number, char piece) {
        this.cell = Cell.cell(letter, number);
        this.piece = piece;
    }

    public ChessPieceImpl() {
        this.cell = null;
        this.piece = '.';
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public char toChar() {
        return piece;
    }
}