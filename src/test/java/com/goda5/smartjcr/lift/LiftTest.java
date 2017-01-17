package com.goda5.smartjcr.lift;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LiftTest {
    @Test
    public void test() {
        Floor floor0 = new Floor("ground");
        Floor floor1 = new Floor("1st");
        Floor floor2 = new Floor("2nd");
        Floor floor3 = new Floor("3rd");
        List<Floor> floors = new ArrayList<>();
        floors.add(floor0);
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        Lift lift = new Lift(floor0);

        ControlRoom controlRoom = new ControlRoom(lift, floors);

        Object passenger = new Object();
        lift.enter(passenger);
        controlRoom.request(floor2);
        controlRoom.moveLift();
        assertThat(lift.exit(), is(passenger));
        assertThat(lift.getLocation(), is(floor2));
        assertNull(lift.getPassenger());

        controlRoom.request(floor3);
        controlRoom.request(floor1);
        controlRoom.moveLift();
        controlRoom.moveLift();
        assertThat(lift.getLocation(), is(floor1));
    }
}
