package com.murat.cybersoft.survive.Models;

public class Enemy extends IsLive {
    private String type;

    public Enemy(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
