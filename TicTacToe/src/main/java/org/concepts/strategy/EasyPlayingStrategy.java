package org.concepts.strategy;

import org.concepts.model.Board;
import org.concepts.model.Cell;
import org.concepts.model.Move;
import org.concepts.model.Player;
import org.concepts.model.enums.CellState;

public class EasyPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(int row=0;row<board.getSize();row++) {
            for(int col=0;col<board.getSize();col++) {
                if(board.getCells().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
                    return new Move(null,new Cell(row,col));
                }
            }
        }
        return null;
    }
}
