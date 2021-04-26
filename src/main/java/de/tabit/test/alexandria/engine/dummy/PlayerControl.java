package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.entity.Player;

public interface PlayerControl {


    Integer movePlayer(Player p, Integer jumps);
    void doSpecialFieldAction(EngineData enginData, Player p);
}
