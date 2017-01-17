package com.goda5.smartjcr.lift;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ControlRoom {
    private final Lift lift;
    private final List<Floor> floors;
    private final Queue<Floor> requested;

    public ControlRoom(Lift lift, List<Floor> floors) {
        this.lift = lift;
        this.floors = floors;
        requested = new ArrayBlockingQueue<>(floors.size());
    }

    public void moveLift() {
        lift.setLocation(requested.poll());
    }

    public void request(Floor floor) {
        requested.add(floor);
    }
}
