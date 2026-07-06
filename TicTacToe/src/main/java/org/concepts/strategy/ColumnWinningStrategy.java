package org.concepts.strategy;

import org.concepts.model.Board;
import org.concepts.model.Move;
import org.concepts.model.Player;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy{

    private HashMap<String,Integer> colMaps[];

    public ColumnWinningStrategy(){
        this.colMaps = new HashMap[20];
        for (int i = 0; i < 20; i++) {
            colMaps[i] = new HashMap<>();
        }
    }



    @Override
    public boolean checkWinner(Board board, Move move) {
        Player currentPlayer = move.getPlayer();
        int currentCol = move.getCell().getCol();

        HashMap<String,Integer> map = colMaps[currentCol];

        String symbol = currentPlayer.getSymbol().getName();

        if(!map.containsKey(symbol)){
            map.put(symbol,0);
        }
        map.put(symbol,map.get(symbol)+1);
        return map.get(symbol)==board.getSize();
    }
}
