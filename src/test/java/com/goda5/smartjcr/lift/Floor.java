package com.goda5.smartjcr.lift;

public class Floor {
    private final String name;
    private volatile Floor up;
    private volatile Floor down;

    public Floor(String name) {
        this.name = name;
    }

    public void setUp(Floor up) {
        this.up = up;
    }

    public void setDown(Floor down) {
        this.down = down;
    }

    public String getName() {
        return name;
    }

    public Floor getUp() {
        if(up != null) {
            return up;
        } else {
            return this;
        }
    }

    public Floor getDown() {
        if(down != null) {
            return down;
        } else {
            return this;
        }
    }
}
