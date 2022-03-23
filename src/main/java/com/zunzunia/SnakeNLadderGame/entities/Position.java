package com.zunzunia.SnakeNLadderGame.entities;

public class Position {
    public final int number;
    private Snake snake = null;
    private Ladder ladder = null;

    public Position(int number, Snake snake, Ladder ladder) {
        validateNumber(number);
        this.number = number;
        this.snake = snake;
        this.ladder = ladder;
    }

    private void validateNumber(int number) {
        if (number < 0 || number > 100) {
            throw new IllegalArgumentException("Position number has to be between and including 0 - 100");
        }
    }

    public boolean is100() {
        return this.number == 100;
    }

    @Override
    public String toString() {
        return String.format("Position{%s}", number);
    }
}
