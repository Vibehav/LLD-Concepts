package org.concepts.controller;

import org.concepts.model.Board;
import org.concepts.model.Game;
import org.concepts.model.Player;
import org.concepts.model.enums.GameState;
import org.concepts.strategy.WinningStrategy;

import java.util.List;

public class GameController {


    public Game createGame(int size,
                           List<Player> players,
                           List<WinningStrategy> winningStrategies) {
        return Game.getBuilder()
                .setPlayers(players)
                .setSize(size)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void display(Game game){
        game.getBoard().display();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove(game);
    }
}
