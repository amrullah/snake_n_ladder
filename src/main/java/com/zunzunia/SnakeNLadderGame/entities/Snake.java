package com.zunzunia.SnakeNLadderGame.entities;

import lombok.ToString;

@ToString
public class Snake {
    public final int topPosition;
    public final int bottomPosition;

    public Snake(int topPosition, int bottomPosition) {
        validatePositions(topPosition, bottomPosition);
        this.topPosition = topPosition;
        this.bottomPosition = bottomPosition;
    }

    private void validatePositions(int topPosition, int bottomPosition) throws IllegalArgumentException {
        if (topPosition > 98 || topPosition <= 20) {
            throw new IllegalArgumentException("Snake top should be between 21 - 98");
        }
        if (bottomPosition > 80 || bottomPosition < 2) {
            throw new IllegalArgumentException("Snake bottom should be between 2 - 80");
        }
        if (topPosition - bottomPosition < 20) {
            throw new IllegalArgumentException("Snake top should be greater than it's bottom by at least 20");
        }
    }
}
