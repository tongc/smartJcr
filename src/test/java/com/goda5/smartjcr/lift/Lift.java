package com.goda5.smartjcr.lift;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class Lift {
    private volatile Floor location;
    private volatile Object passenger;

    public Lift(Floor location) {
        this.location = location;
    }

    public void up() {
        location = location.getUp();
    }

    public void down() {
        location = location.getDown();
    }

    public void enter(Object passenger) {
        this.passenger = passenger;
    }

    public Object exit() {
        Object temp = passenger;
        passenger = null;
        return temp;
    }

    public Floor getLocation() {
        return location;
    }

    public Object getPassenger() {
        return passenger;
    }

    public static void main(String[] args) {
        Floor floor0 = new Floor("ground");
        Floor floor1 = new Floor("1st");
        floor0.setUp(floor1);
        floor1.setDown(floor0);

        Lift lift = new Lift(floor0);
        Object passenger = new Object();
        lift.enter(passenger);
        lift.up();
        assertThat(lift.exit(), is(passenger));
        assertThat(lift.getLocation(), is(floor1));
        assertNull(lift.getPassenger());
    }
}
