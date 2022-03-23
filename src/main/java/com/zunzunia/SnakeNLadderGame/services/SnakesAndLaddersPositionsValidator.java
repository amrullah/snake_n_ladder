package com.zunzunia.SnakeNLadderGame.services;

import com.zunzunia.SnakeNLadderGame.entities.Ladder;
import com.zunzunia.SnakeNLadderGame.entities.Snake;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SnakesAndLaddersPositionsValidator {

    public void validate(List<Snake> snakes, List<Ladder> ladders) {
        Set<Integer> allSnakesTopPositions = new HashSet<>();
        Set<Integer> allLaddersTopPositions = new HashSet<>();
        Set<Integer> allSnakesBottomPositions = new HashSet<>();
        Set<Integer> allLaddersBottomPositions = new HashSet<>();


        // no two snakes should have the same top
        snakes.forEach(thisSnake -> {
            if (allSnakesTopPositions.contains(thisSnake.topPosition)) {
                throw new IllegalArgumentException(
                        String.format("Two snakes have conflicting tops: %s", thisSnake.topPosition));
            }
            allSnakesTopPositions.add(thisSnake.topPosition);
            allSnakesBottomPositions.add(thisSnake.bottomPosition);
        });

        // no two ladders should have the same bottom
        ladders.forEach(thisLadder -> {
            if (allLaddersBottomPositions.contains(thisLadder.bottomPosition)) {
                throw new IllegalArgumentException(
                        String.format("Two ladders have conflicting bottoms: %s", thisLadder.bottomPosition));
            }
            allLaddersBottomPositions.add(thisLadder.bottomPosition);
            allLaddersTopPositions.add(thisLadder.topPosition);
        });


        checkNoClashBetween(allLaddersTopPositions, allLaddersBottomPositions,
                "Some Ladders have clashing tops and bottoms : %s");

        checkNoClashBetween(allSnakesTopPositions, allSnakesBottomPositions,
                "Some Snakes have clashing tops and bottoms : %s");

        checkNoClashBetween(allSnakesTopPositions, allLaddersBottomPositions,
                "Some snakes have tops clashing with ladders' bottoms : %s");

        checkNoClashBetween(allSnakesTopPositions, allLaddersTopPositions,
                "Some snakes have tops clashing with ladders' tops : %s");

        checkNoClashBetween(allSnakesBottomPositions, allLaddersBottomPositions,
                "Some snakes have bottoms clashing with ladders' bottoms : %s");
    }

    private void checkNoClashBetween(Set<Integer> positionsList1, Set<Integer> positionsList2, String errorMessage) {
        Set<Integer> intersection = getIntersectionOf(positionsList1, positionsList2);
        if (intersection.size() > 0) {
            throw new IllegalArgumentException(
                    String.format(errorMessage, intersection));
        }
    }


    private Set<Integer> getIntersectionOf(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

}
