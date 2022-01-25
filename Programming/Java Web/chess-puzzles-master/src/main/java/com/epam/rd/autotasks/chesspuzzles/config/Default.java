package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Default {

    @Bean
    public ChessPiece getBlackRook1() {
        return new ChessPieceImpl(Cell.cell('A', 8), 'R');
    }

    @Bean
    public ChessPiece getBlackKnight1() {
        return new ChessPieceImpl(Cell.cell('B', 8), 'N');
    }

    @Bean
    public ChessPiece getBlackBishop1() {
        return new ChessPieceImpl(Cell.cell('C', 8), 'B');
    }

    @Bean
    public ChessPiece getBlackQueen() {
        return new ChessPieceImpl(Cell.cell('D', 8), 'Q');
    }

    @Bean
    public ChessPiece getBlackKing() {
        return new ChessPieceImpl(Cell.cell('E', 8), 'K');
    }

    @Bean
    public ChessPiece getBlackBishop2() {
        return new ChessPieceImpl(Cell.cell('F', 8), 'B');
    }

    @Bean
    public ChessPiece getBlackKnight2() {
        return new ChessPieceImpl(Cell.cell('G', 8), 'N');
    }

    @Bean
    public ChessPiece getBlackRook2() {
        return new ChessPieceImpl(Cell.cell('H', 8), 'R');
    }

    @Bean
    public ChessPiece getBlackPawn1() {
        return new ChessPieceImpl(Cell.cell('A', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn2() {
        return new ChessPieceImpl(Cell.cell('B', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn3() {
        return new ChessPieceImpl(Cell.cell('C', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn4() {
        return new ChessPieceImpl(Cell.cell('D', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn5() {
        return new ChessPieceImpl(Cell.cell('E', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn6() {
        return new ChessPieceImpl(Cell.cell('F', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn7() {
        return new ChessPieceImpl(Cell.cell('G', 7), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn8() {
        return new ChessPieceImpl(Cell.cell('H', 7), 'P');
    }

    @Bean
    public ChessPiece getWhiteRook1() {
        return new ChessPieceImpl(Cell.cell('A', 1), 'r');
    }

    @Bean
    public ChessPiece getWhiteKnight1() {
        return new ChessPieceImpl(Cell.cell('B', 1), 'n');
    }

    @Bean
    public ChessPiece getWhiteBishop1() {
        return new ChessPieceImpl(Cell.cell('C', 1), 'b');
    }

    @Bean
    public ChessPiece getWhiteQueen() {
        return new ChessPieceImpl(Cell.cell('D', 1), 'q');
    }

    @Bean
    public ChessPiece getWhiteKing() {
        return new ChessPieceImpl(Cell.cell('E', 1), 'k');
    }

    @Bean
    public ChessPiece getWhiteBishop2() {
        return new ChessPieceImpl(Cell.cell('F', 1), 'b');
    }

    @Bean
    public ChessPiece getWhiteKnight2() {
        return new ChessPieceImpl(Cell.cell('G', 1), 'n');
    }

    @Bean
    public ChessPiece getWhiteRook2() {
        return new ChessPieceImpl(Cell.cell('H', 1), 'r');
    }

    @Bean
    public ChessPiece getWhitePawn1() {
        return new ChessPieceImpl(Cell.cell('A', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn2() {
        return new ChessPieceImpl(Cell.cell('B', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn3() {
        return new ChessPieceImpl(Cell.cell('C', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn4() {
        return new ChessPieceImpl(Cell.cell('D', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn5() {
        return new ChessPieceImpl(Cell.cell('E', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn6() {
        return new ChessPieceImpl(Cell.cell('F', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn7() {
        return new ChessPieceImpl(Cell.cell('G', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn8() {
        return new ChessPieceImpl(Cell.cell('H', 2), 'p');
    }

}
