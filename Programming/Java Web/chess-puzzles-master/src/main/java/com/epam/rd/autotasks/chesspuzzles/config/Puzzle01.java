package com.epam.rd.autotasks.chesspuzzles.config;

import com.epam.rd.autotasks.chesspuzzles.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Puzzle01 {

    @Bean
    public ChessPiece getBlackRook1() {
        return new ChessPieceImpl(Cell.cell('E',8), 'R');
    }

    @Bean
    public ChessPiece getBlackBishop1() {
        return new ChessPieceImpl(Cell.cell('D', 6), 'B');
    }

    @Bean
    public ChessPiece getBlackKing() {
        return new ChessPieceImpl(Cell.cell('C', 5), 'K');
    }

    @Bean
    public ChessPiece getBlackBishop2() {
        return new ChessPieceImpl(Cell.cell('D', 5), 'B');
    }

    @Bean
    public ChessPiece getBlackPawn1() {
        return new ChessPieceImpl(Cell.cell('F',6), 'P');
    }

    @Bean
    public ChessPiece getBlackPawn2() {
        return new ChessPieceImpl(Cell.cell('C',3), 'P');
    }

    @Bean
    public ChessPiece getWhiteKnight1() {
        return new ChessPieceImpl(Cell.cell('C',6), 'n');
    }

    @Bean
    public ChessPiece getWhiteBishop1() {
        return new ChessPieceImpl(Cell.cell('E', 2), 'b');
    }

    @Bean
    public ChessPiece getWhiteKing() {
        return new ChessPieceImpl(Cell.cell('B', 7), 'k');
    }

    @Bean
    public ChessPiece getWhitePawn1() {
        return new ChessPieceImpl(Cell.cell('D',2), 'p');
    }

}
