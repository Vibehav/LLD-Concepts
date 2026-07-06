package org.concepts.strategy;

import org.concepts.model.Board;
import org.concepts.model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
