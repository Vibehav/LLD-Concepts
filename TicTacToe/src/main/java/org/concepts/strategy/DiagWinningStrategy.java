package org.concepts.strategy;

import org.concepts.model.Board;
import org.concepts.model.Move;
import org.concepts.model.Player;

import java.util.HashMap;

public class DiagWinningStrategy implements WinningStrategy {
    private HashMap<String, Integer> leftDiagMap;
    private HashMap<String, Integer> rightDiagMap;

        public DiagWinningStrategy(){
            leftDiagMap = new HashMap<>();
            rightDiagMap = new HashMap<>();
        }
    @Override
    public boolean checkWinner(Board board, Move move) {
        Player player = move.getPlayer();
        int currRow = move.getCell().getRow();
        int currCol = move.getCell().getCol();
        String currSymbol = player.getSymbol().getName();
        int boardSize = board.getSize();

        if (currRow == currCol) {

            leftDiagMap.put(currSymbol, leftDiagMap.getOrDefault(currSymbol, 0) + 1);
            if (leftDiagMap.get(currSymbol) == boardSize) {
                return true;
            }
        }

        if (currRow + currCol == boardSize - 1) {
            rightDiagMap.put(currSymbol, rightDiagMap.getOrDefault(currSymbol, 0) + 1);

            if (rightDiagMap.get(currSymbol) == boardSize) {
                return true;
            }
        }

        return false;
    }
}
/*0,0           0,4
*    1,1    1,3
*       2,2
*    3,1    3,3
* 4,0           4,4
* */
