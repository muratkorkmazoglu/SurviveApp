package com.murat.cybersoft.survive;

import com.murat.cybersoft.survive.Models.Position;

import java.util.Comparator;

public class PositionCompare implements Comparator<Position> {
    @Override
    public int compare(Position o1, Position o2) {
        return Integer.compare(o1.getPosition(), o2.getPosition());
    }
}
