package com.zunzunia.SnakeNLadderGame.entities;

public class GameBoard {
    private static Position[] positions;
    private static boolean isInitialized = false;

    private GameBoard() {}

    public static GameBoard initialize(Position[] inputPositions) throws IllegalAccessException {
        if (isInitialized) {
            throw new IllegalAccessException("Initialize should not be called more than once");
        }
        positions = inputPositions.clone();
        isInitialized = true;
        return new GameBoard();
    }
    public static Position positionAt(int positionNumber) throws IllegalAccessException {
        checkIfInitialized();
        return positions[positionNumber];
    }

    public static GameBoard getGameBoard() throws IllegalAccessException {
        checkIfInitialized();
        return new GameBoard();
    }

    public static void checkIfInitialized() throws IllegalAccessException {
        if (!isInitialized) {
            throw new IllegalAccessException("GameBoard needs to be initialized first");
        }
    }

    public static int getTotalCountOfPositions() throws IllegalAccessException {
        checkIfInitialized();
        return positions.length;
    }

}
