package org.concepts.strategy;

import org.concepts.model.*;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{
    private HashMap<String,Integer> rowMaps[];

    public RowWinningStrategy(){
        rowMaps = new HashMap[20];
        for (int i = 0; i < 20; i++) {
            rowMaps[i] = new HashMap<>();
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        // step 1: we need a board where move is made and which move is made.
        // current player
        Player player = move.getPlayer();
        // current row
        int currRow = move.getCell().getRow();

        HashMap<String,Integer> currRowMap = rowMaps[currRow];

        String currSymbol = player.getSymbol().getName();

        if(!currRowMap.containsKey(currSymbol)) {
            currRowMap.put(currSymbol,0);
        }
        currRowMap.put(currSymbol,currRowMap.get(currSymbol)+1);

        return currRowMap.get(currSymbol)==board.getSize();


    }


}
/*
* Apporach 1: to iterate all the rows at every move irrerepective of the cell.
* boolean b1 = false;
* for(i to size)
* |  int counter = 0;
* |  for(j to size)
* |  |    if(currentPlayers symbol is X and cells symbol is X) counter++;
* |  if(counter==size) b1 = true;
* |  break;
*
* We got the winner return true
*
* Approach 2:
*
* */