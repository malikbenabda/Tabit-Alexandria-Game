package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldServices implements PlayerControl {

    public static List<TrapField> generateTraps(Integer count) {
        return new ArrayList<>(Collections.nCopies(count, new TrapField()));
    }

    public static List<BonusField> generateBonus(Integer bonusNum) {
        return Stream.generate(BonusField::new).limit(bonusNum).collect(Collectors.toList());
    }

    @Override
    public void doSpecialFieldAction(EngineData enginData, Player p) {
        Field currentField = enginData.fields.get(p.getPosition());
        if (currentField instanceof TrapField) {
            handleTraps(currentField, p, enginData);
        } else if (currentField instanceof BonusField) {
            handleBonus(currentField, p, enginData);
        } else {
            System.out.println(p.getName().concat(" landed on a standard Field"));
        }
    }

    private void handleBonus(Field currentField, Player p, EngineData enginData) {
        System.out.println(p.getName().concat(" landed on a BONUS Field"));
        switch (((BonusField) currentField).getType()) {
            case BONUS_TYPE1:
                movePlayer(p, 2);
                System.out.println(p.getName().concat(" advances by 2 Field"));
                break;
            case BONUS_TYPE2: {
                System.out.println(p.getName().concat(" pushes others 2 Fields back"));
                enginData.playerList.forEach(player -> {
                    if (!player.equals(p)) {
                        movePlayer(player, -2);
                    }
                });
                break;
            }
            case BONUS_TYPE3:
                System.out.println(p.getName().concat(" landed on a **JOKER**"));
                p.setHasJoker(Boolean.TRUE);
                break;
        }

    }

    private void handleTraps(Field currentField, Player p, EngineData enginData) {
        System.out.println(p.getName().concat(" landed on a TRAP Field"));
        switch (((TrapField) currentField).getType()) {
            case TRAP_TYPE1:
                if (p.getHasJoker()) p.setHasJoker(false);
                else {
                    movePlayer(p, -2);
                    System.out.println(p.getName().concat(" was pushed back by a TRAP"));
                }
                break;
            case TRAP_TYPE2:
                enginData.playerList.forEach(player -> {
                    if (!player.equals(p)) {
                        movePlayer(player, 2);
                    }
                });
                System.out.println(p.getName().concat("pushed everyone 2 Fields forward"));
                break;
            case TRAP_TYPE3:
                System.out.println(p.getName().concat(" has to skip next turn"));
                p.setCuredSkip(true);
                break;
        }

    }

    @Override
    public Integer movePlayer(Player p, Integer jumps) {
        Integer position = p.getPosition();
        position = Math.max(0, Math.min(DummyEngine.ROAD_END, position));
        p.setPosition(position);
        return position;
    }
}
