package com.zunzunia.SnakeNLadderGame.entities;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Die die = new Die();
    public final GameBoard gameBoard;  // should be initialized during initialization phase
    public final Player[] players;
    public final int number;

    public Game(int gameNumber, Player[] players, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.number = gameNumber;
    }

    public List<Turn> runTillComplete() {
        List<Turn> turns = new ArrayList<>();

        int playerNumber = 0;
        while (true) {
            Player player = players[playerNumber];
            Turn turn = playTurn(player);
            turns.add(turn);
            if (player.hasWon()) {
                break;
            }
            playerNumber = (playerNumber+1) % players.length;  // keep going round-robin over the players
        }
        return turns;
    }

    private Turn playTurn(Player player) {  // it's own service object
        int totalRollSum = 0;
        while (true) {
            int rollValue = die.roll();
            totalRollSum += rollValue;
            if (rollValue == 6) {
                continue;
            } else {
                break;
            }
        }

        int newPositionNumber = player.getCurrentPosition().number + totalRollSum;
        // handle close to 100 case, where the player keeps getting new turn at 6 but can't move forward
        // handle snake case
        // handle ladder case

        return new Turn();
    }

    public String toString() {
        return String.format("Game %s", this.number);
    }

}
