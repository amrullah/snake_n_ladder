package com.zunzunia.SnakeNLadderGame.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Die {
    private static final int[] faces = {1, 2, 3, 4, 5, 6};
    private static final Random rand = new Random();

    public int roll() {
        return faces[rand.nextInt(faces.length)];
    }
}
