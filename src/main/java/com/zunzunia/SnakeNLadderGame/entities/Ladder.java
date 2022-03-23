package com.zunzunia.SnakeNLadderGame.entities;

import lombok.ToString;

@ToString
public class Ladder {
    public final int topPosition;
    public final int bottomPosition;

    public Ladder(int topPosition, int bottomPosition) {
        validatePositions(topPosition, bottomPosition);
        this.topPosition = topPosition;
        this.bottomPosition = bottomPosition;
    }

    private void validatePositions(int topPosition, int bottomPosition) {
        if (topPosition > 98 || topPosition <= 20) {
            throw new IllegalArgumentException("Ladder top should be between 21 - 98");
        }
        if (bottomPosition > 80 || bottomPosition < 2) {
            throw new IllegalArgumentException("Ladder bottom should be between 2 - 80");
        }
        if (topPosition - bottomPosition < 20) {
            throw new IllegalArgumentException("Ladder top should be greater than it's bottom by at least 20");
        }
    }

}
