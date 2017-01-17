package com.goda5.smartjcr.lift;

public class Lift {
    private volatile Floor location;
    private volatile Object passenger;

    public Lift(Floor location) {
        this.location = location;
    }

    public void enter(Object passenger) {
        this.passenger = passenger;
    }

    public Object exit() {
        Object temp = passenger;
        passenger = null;
        return temp;
    }

    public void setLocation(Floor location) {
        this.location = location;
    }

    public Floor getLocation() {
        return location;
    }

    public Object getPassenger() {
        return passenger;
    }
}
