package com.lumivote.lumivote.bus;

import com.squareup.otto.Bus;

/**
 * Created by alex on 8/6/15.
 */
public class BusProvider {

    private static final Bus BUS = new Bus();

    private BusProvider() {
        // private constructor to ensure singleton pattern
    }

    public static Bus getInstance() {
        return BUS;
    }

}
