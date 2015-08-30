package com.lumivote.lumivote.bus;

/**
 * Created by alex on 8/30/15.
 */
public class AbstractLumivoteEvent {

    private Enum type;

    protected AbstractLumivoteEvent(Enum type) {
        this.type = type;
    }

    public Enum getType() {
        return this.type;
    }

}
