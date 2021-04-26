package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.engine.api.IAlexandriaGameEngine;
import de.tabit.test.alexandria.entity.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public class DummyEngine implements IAlexandriaGameEngine {


    private static final AtomicInteger TURNS = new AtomicInteger(0);
    private final Random random = new Random();
    private EngineData enginData;
    private FieldServices services;
    public static final Integer ROAD_END = 30;
    public static final Integer TRAP_NUM = 5;
    public static final Integer BONUS_NUM = 5;
    private Integer playerOrder = null;

    @Override
    public String startGame(Integer numberOfPlayers) {
        enginData = new EngineData(numberOfPlayers, ROAD_END, TRAP_NUM, BONUS_NUM);
        services = new FieldServices();
        return "Game started : " + numberOfPlayers + " players";
    }

    @Override
    public boolean gameIsRunning() {
        for (Player player : enginData.getPlayerList()) {
            if (player.getPosition().compareTo(ROAD_END) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String nextPlayer() {
        this.playerOrder = random.nextInt(enginData.getPlayerList().size());
        return format("Player %d", playerOrder + 1);
    }

    @Override
    public String nextPlayerTurn(Integer input) {
        TURNS.incrementAndGet();
        Player p = enginData.playerList.get(playerOrder);
        System.out.println(p.getName().concat(" is playing"));

        if (p.getCuredSkip()) return p.getName().concat(" skipped this turn **CURSED** ");
        services.movePlayer(p, input);
        services.doSpecialFieldAction(enginData, p);
        return p.getName().concat("  turn ended");
    }


}
