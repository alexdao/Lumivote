package com.lumivote.lumivote.bus;

/**
 * Created by alex on 8/11/15.
 */
public abstract class AbstractHuffPostEvent {

    private Enum type;

    protected AbstractHuffPostEvent(Enum type) {
        this.type = type;
    }

    public Enum getType() {
        return this.type;
    }
}
