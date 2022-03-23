package com.zunzunia.SnakeNLadderGame.entities;


import com.zunzunia.SnakeNLadderGame.GameInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameSimulator {
    private static final GameInitializer gameInitializer = new GameInitializer();

    public void simulateGamesAndDisplayStatistics(int numberOfSimulations, int numberOfPlayers,
                                                  List<Snake> snakes, List<Ladder> ladders) throws IllegalAccessException {
        List<Game> games = new ArrayList<>();

        for (int i = 0; i < numberOfSimulations; i++) {
            games.add(gameInitializer.constructGame(i, numberOfPlayers, snakes, ladders));
        }

        Map<Game, List<Turn>> gameAndTurnsLogMap = new HashMap<>();
        for (Game game : games) {
            List<Turn> turnsLog = new ArrayList<>();
            turnsLog.addAll(game.runTillComplete());
            gameAndTurnsLogMap.put(game, turnsLog);
        }

        // results = GameStatisticCalculator.do_stuff(turns_log)
        // StatisticsPrinter.print_text(results)
    }
}
