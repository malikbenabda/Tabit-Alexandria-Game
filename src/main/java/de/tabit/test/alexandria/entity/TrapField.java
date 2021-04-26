package de.tabit.test.alexandria.entity;

import de.tabit.test.alexandria.engine.toolz.Utilities;

public class TrapField extends Field{
    private TrapType type;

    public TrapType getType() {
        type = Utilities.randomEnum(TrapType.class);
        return type;
    }
}
