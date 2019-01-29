package com.murat.cybersoft.survive.Models;

import com.murat.cybersoft.survive.Writer;
import com.murat.cybersoft.survive.Attack;

import java.util.List;

public class Hero extends IsLive implements Attack {
    private boolean alive=true;

    private Writer writer;

    private StringBuilder output = new StringBuilder();


    public void start(List<Position> positions) {

        output.append("Hero started journey with "+ getHealthPower() +" HP! \n");

        for (Position position : positions) {
            if(isAlive()) attack(position);
        }

        if(getHealthPower() > 0 )
            output.append("Hero survived!");

        writer.writeMessage(output.toString());

    }

    public void attack(Position r) {
        Enemy e = r.getEnemy();

        while(getHealthPower() > 0 && e.getHealthPower() >0) {
            setHealthPower(getHealthPower()-e.getAttackPoints());
            e.setHealthPower(e.getHealthPower()-getAttackPoints());
        }

        if(getHealthPower() > 0) {
            output.append("Hero defeated "+  e.getType() + " with " + getHealthPower()+ " HP remaining \n");
        }
        else {
            output.append(e.getType() + " defeated Hero"+ " with " + e.getHealthPower()+ " HP remaining \n");
            output.append("Hero is Dead!! Last seen at position "+ r.getPosition() +"!! \n");
            setAlive(false);
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
