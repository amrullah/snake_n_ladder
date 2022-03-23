package com.zunzunia.SnakeNLadderGame;

import com.zunzunia.SnakeNLadderGame.entities.*;
import com.zunzunia.SnakeNLadderGame.services.SnakesAndLaddersPositionsValidator;

import java.util.*;

public class GameInitializer {
    private static final SnakesAndLaddersPositionsValidator snlValidator = new SnakesAndLaddersPositionsValidator();

    public Game constructGame(int gameNumber, int numberOfPlayers, List<Snake> snakes, List<Ladder> ladders) throws IllegalAccessException {
        GameBoard gameBoard = constructGameBoard(snakes, ladders);
        Player[] players = constructPlayers(numberOfPlayers);
        return new Game(gameNumber, players, gameBoard);
    }

    private Player[] constructPlayers(int numberOfPlayers) throws IllegalAccessException {
        if (numberOfPlayers < 1 || numberOfPlayers > 4) {
            throw new IllegalArgumentException("Number of players has to be between and including 1 and 4");
        }
        Player[] players = new Player[numberOfPlayers];
        for (int i=0; i < numberOfPlayers; i++) {
            players[i] = new Player(i);
        }
        return players;
    }

    private GameBoard constructGameBoard(List<Snake> snakes, List<Ladder> ladders) throws IllegalAccessException {
//        validateSnakesAndLadders(snakes, ladders);
        snlValidator.validate(snakes, ladders);
        int counter = 0;
        List<Position> positionList = new ArrayList<>();

        while (counter <= 100) {
            Snake snake = findSnakeAt(snakes, counter);
            Ladder ladder = findLadderAt(ladders, counter);
            positionList.add(new Position(counter, snake, ladder));
            counter += 1;
        }
        Position[] positionArray = new Position[positionList.size()];

        positionList.toArray(positionArray);

        return GameBoard.initialize(positionArray);
    }

    private Snake findSnakeAt(List<Snake> snakes, int counter) {
        Optional<Snake> foundSnake = snakes.stream()
                .filter(thisSnake -> thisSnake.topPosition == counter)
                .findFirst();

        if (foundSnake.isEmpty()) {
            return null;
        }
        return foundSnake.get();
    }

    private Ladder findLadderAt(List<Ladder> ladders, int counter) {
        Optional<Ladder> foundLadder = ladders.stream()
                .filter(thisLadder -> thisLadder.bottomPosition == counter)
                .findFirst();

        if (foundLadder.isEmpty()) {
            return null;
        }
        return foundLadder.get();
    }

}
