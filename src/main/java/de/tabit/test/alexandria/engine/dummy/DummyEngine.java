package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.engine.api.IAlexandriaGameEngine;
import de.tabit.test.alexandria.entity.*;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public class DummyEngine implements IAlexandriaGameEngine {


    private static final AtomicInteger TURNS = new AtomicInteger(0);

    private PlayerControl services;
    public static final Integer ROAD_END = 30;
    public static final Integer TRAP_NUM = 5;
    public static final Integer BONUS_NUM = 5;

    @Override
    public String startGame(Integer numberOfPlayers) {
        services = new FieldServices();
        services.init(numberOfPlayers, ROAD_END, TRAP_NUM, BONUS_NUM);
        return "Game started : " + numberOfPlayers + " players";
    }

    @Override
    public boolean gameIsRunning() {
        return ! services.hasWinner(ROAD_END);
    }

    @Override
    public String nextPlayer() {
        Integer playerIndex = services.getNextPlayerIndex();
        return format("Player %d", playerIndex + 1);
    }

    @Override
    public String nextPlayerTurn(Integer input) {
        TURNS.incrementAndGet();

        if (services.checkIsSkipped()) return "Player forced to skipped this turn **TRAP** ";
        Player p = services.getCurrentPlayer();
        System.out.println(p.getName() + " is playing ... Start : Field#" + (p.getPosition() + 1));
        services.moveCurrentPlayer(input);
        services.doSpecialFieldAction();
        return p.getName() + "  turn ended : Field #" + (p.getPosition() + 1)  ;
    }


}
