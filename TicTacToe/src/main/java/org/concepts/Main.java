package org.concepts;

import org.concepts.controller.GameController;
import org.concepts.model.*;
import org.concepts.model.enums.BotDifficultyLevel;
import org.concepts.model.enums.GameState;

import org.concepts.strategy.ColumnWinningStrategy;
import org.concepts.strategy.DiagWinningStrategy;
import org.concepts.strategy.RowWinningStrategy;
import org.concepts.strategy.WinningStrategy;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int size = 3;
        Symbol symbol1 = new Symbol("X");
        Symbol symbol2 = new Symbol("O");
        List<Player> players = List.of(new Human(1,"player 1",symbol1,"20"),
                new Bot(2,"bot 1",symbol2, BotDifficultyLevel.EASY));
        List<WinningStrategy> winningStrategies = List.of(new RowWinningStrategy(),
                new ColumnWinningStrategy(),new DiagWinningStrategy());


        GameController gameController = new GameController();
        Game game = gameController.createGame(size,players,winningStrategies);

        while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)){

            gameController.display(game);
            gameController.makeMove(game);

        }

        GameState state = gameController.getGameState(game);

        if(state.equals(GameState.COMPLETE)) {
            System.out.println("The Winner is "+ game.getWinner().getName());
        } else if (state.equals(GameState.DRAW)) {
            System.out.println("It's a draw");
        }


    }
}