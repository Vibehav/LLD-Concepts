package org.concepts.factory;

import org.concepts.model.enums.BotDifficultyLevel;
import org.concepts.strategy.BotPlayingStrategy;
import org.concepts.strategy.EasyPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyPlayingStrategy();
        }else return new EasyPlayingStrategy();
    }


}
