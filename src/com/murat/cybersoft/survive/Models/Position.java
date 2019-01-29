package com.murat.cybersoft.survive.Models;

public class Position {
    private int position;
    private Enemy enemy;

    public Position(Enemy enemy, int pos) {
        this.enemy = enemy;
        this.position = pos;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public Enemy getEnemy() {
        return enemy;
    }
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
