package org.concepts.strategy;

import org.concepts.model.Board;
import org.concepts.model.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);
}
