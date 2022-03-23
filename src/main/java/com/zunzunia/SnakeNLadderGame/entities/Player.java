package com.zunzunia.SnakeNLadderGame.entities;

public class Player {
    public final int number;
    public final String name;
    private GameBoard gameBoard;
    private Position currentPosition;

    public Player(int playerNumber) throws IllegalAccessException {
        this.number = playerNumber;
        this.name = String.format("Player %s", playerNumber);
        this.gameBoard = GameBoard.getGameBoard();
        this.currentPosition = GameBoard.positionAt(0);
    }

    public boolean hasWon() {
        return this.currentPosition.is100();
    }

    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
