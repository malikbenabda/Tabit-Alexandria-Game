package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.entity.Field;
import de.tabit.test.alexandria.entity.Player;

import java.util.List;

public interface PlayerControl {


    void doSpecialFieldAction();

    void movePlayer(Integer playerIndex, Integer jumps);

    void init(Integer numberOfPlayers, Integer roadEnd, Integer trapNum, Integer bonusNum);
    Boolean hasWinner(Integer endRoad);

    Integer getNextPlayerIndex();

    boolean checkIsSkipped();

    Player getCurrentPlayer();

    void moveCurrentPlayer(Integer input);
}
