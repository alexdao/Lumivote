package com.lumivote.lumivote.bus;

/**
 * Created by alex on 8/6/15.
 */
public abstract class AbstractSunlightEvent {

    private Enum type;

    protected AbstractSunlightEvent(Enum type) {
        this.type = type;
    }

    public Enum getType() {
        return this.type;
    }

}
