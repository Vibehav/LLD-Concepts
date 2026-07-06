package org.concepts.model;

import org.concepts.factory.BotPlayingStrategyFactory;
import org.concepts.model.enums.BotDifficultyLevel;
import org.concepts.model.enums.PlayerType;
import org.concepts.strategy.BotPlayingStrategy;

public class Bot extends Player {

    private BotPlayingStrategy botPlayingStrategy;
    private BotDifficultyLevel botDifficultyLevel;


    public Bot(int id, String name, Symbol symbol,BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel=botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getPlayingStrategy(botDifficultyLevel);
    }


    @Override
    public Move makeMove(Game game) {
        System.out.println("It's "+this.getName()+" bot's turn");
        Move move = botPlayingStrategy.makeMove(game.getBoard());
        move.setPlayer(this);
        return move;
    }
}
