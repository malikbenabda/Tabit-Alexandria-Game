package de.tabit.test.alexandria.entity;

import de.tabit.test.alexandria.engine.toolz.Utilities;

public class BonusField extends Field {
    private BonusType type;

    public BonusType getType() {
        type = Utilities.randomEnum(BonusType.class);
        return type;
    }

}
