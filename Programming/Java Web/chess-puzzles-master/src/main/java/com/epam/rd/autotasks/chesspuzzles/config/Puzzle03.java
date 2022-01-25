package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Puzzle03 {

    @Bean
    public ChessPiece getBlackRook1() {
        return new ChessPieceImpl(Cell.cell('C', 1), 'R');
    }

    @Bean
    public ChessPiece getWhiteBishop() {
        return new ChessPieceImpl(Cell.cell('D', 1), 'b');
    }

    @Bean
    public ChessPiece getWhiteKing() {
        return new ChessPieceImpl(Cell.cell('H', 1), 'k');
    }

    @Bean
    public ChessPiece getWhitePawn1() {
        return new ChessPieceImpl(Cell.cell('G', 2), 'p');
    }

    @Bean
    public ChessPiece getWhitePawn2() {
        return new ChessPieceImpl(Cell.cell('H', 2), 'p');
    }

    @Bean
    public ChessPiece getBlackRook2() {
        return new ChessPieceImpl(Cell.cell('C', 3), 'R');
    }

    @Bean
    public ChessPiece getBlackBishop() {
        return new ChessPieceImpl(Cell.cell('B', 4), 'B');
    }

    @Bean
    public ChessPiece getWhitePawn3() {
        return new ChessPieceImpl(Cell.cell('D', 5), 'p');
    }
    @Bean
    public ChessPiece getBlackKing() {
        return new ChessPieceImpl(Cell.cell('G', 5), 'K');
    }
    @Bean
    public ChessPiece getBlackPawn1() {
        return new ChessPieceImpl(Cell.cell('A', 6), 'P');
    }
    @Bean
    public ChessPiece getBlackPawn2() {
        return new ChessPieceImpl(Cell.cell('D', 6), 'P');
    }
    @Bean
    public ChessPiece getBlackPawn3() {
        return new ChessPieceImpl(Cell.cell('G', 6), 'P');
    }
    @Bean
    public ChessPiece getBlackPawn4() {
        return new ChessPieceImpl(Cell.cell('B', 7), 'P');
    }
    @Bean
    public ChessPiece getBlackQueen() {
        return new ChessPieceImpl(Cell.cell('D', 7), 'Q');
    }
    @Bean
    public ChessPiece getBlackPawn5() {
        return new ChessPieceImpl(Cell.cell('E', 7), 'P');
    }
    @Bean
    public ChessPiece getWhiteRook1() {
        return new ChessPieceImpl(Cell.cell('F', 7), 'r');
    }
    @Bean
    public ChessPiece getWhiteRook2() {
        return new ChessPieceImpl(Cell.cell('H', 8), 'r');
    }

}