package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldServices implements PlayerControl {

    private List<Player> playerList;
    private List<Field> fields;
    private Integer playerIndex = null;
    private final Random random = new Random();

    public void init(Integer numberOfPlayers, Integer roadLength, Integer trapNum, Integer bonusNum) {
        createPlayerList(numberOfPlayers);
        this.fields = generateFields(roadLength, trapNum, bonusNum);
    }

    private void createPlayerList(Integer numberOfPlayers) {
        this.playerList = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            playerList.add(new Player("" + i));
        }
    }

    private List<Field> generateFields(Integer roadLength, Integer trapNum, Integer bonusNum) {
        List<Field> road = new ArrayList<>(Collections.nCopies(roadLength + 1 - bonusNum - trapNum, new Field()));
        road.addAll(generateTraps(trapNum));
        road.addAll(generateBonus(bonusNum));

        Collections.shuffle(road);

        return road;
    }


    public static List<TrapField> generateTraps(Integer count) {
        return new ArrayList<>(Collections.nCopies(count, new TrapField()));
    }

    public static List<BonusField> generateBonus(Integer bonusNum) {
        return Stream.generate(BonusField::new).limit(bonusNum).collect(Collectors.toList());
    }

    @Override
    public void doSpecialFieldAction() {

        Player currentPlayer = getCurrentPlayer();
        Field currentField = fields.get(currentPlayer.getPosition());
        if (currentField instanceof TrapField) {
            handleTraps(currentField);
        } else if (currentField instanceof BonusField) {
            handleBonus(currentField);
        } else {
            System.out.println(currentPlayer.getName().concat(" landed on a standard Field"));
        }
    }

    private void handleBonus(Field currentField) {
        Player p = getCurrentPlayer();
        System.out.println(p.getName().concat(" landed on a BONUS Field"));
        switch (((BonusField) currentField).getType()) {
            case BONUS_TYPE1:
                movePlayer(playerIndex, 2);
                System.out.println(p.getName().concat(" advances by 2 Field"));
                break;
            case BONUS_TYPE2: {
                System.out.println(p.getName().concat(" pushes others 2 Fields back"));
                playerList.forEach(player -> {
                    if (!player.equals(p)) {
                        movePlayer(playerIndex, -2);
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

    private void handleTraps(Field currentField) {
        Player p = getCurrentPlayer();
        System.out.println(p.getName().concat(" landed on a TRAP Field"));
        switch (((TrapField) currentField).getType()) {
            case TRAP_TYPE1:
                if (p.getHasJoker()) p.setHasJoker(false);
                else {
                    movePlayer(playerIndex, -2);
                    System.out.println(p.getName().concat(" was pushed back by a TRAP"));
                }
                break;
            case TRAP_TYPE2:
                playerList.forEach(player -> {
                    if (!player.equals(p)) {
                        movePlayer(playerIndex, 2);
                    }
                });
                System.out.println(p.getName().concat("pushed everyone 2 Fields forward"));
                break;
            case TRAP_TYPE3:
                System.out.println(p.getName().concat(" has to skip next turn"));
                p.setMustSkip(true);
                break;
        }

    }

    @Override
    public void movePlayer(Integer playerIndex, Integer jumps) {
        Integer position = playerList.get(playerIndex).getPosition();
        position = Math.max(0, Math.min(DummyEngine.ROAD_END, position + jumps));
        playerList.get(playerIndex).setPosition(position);
    }

    @Override
    public Boolean hasWinner(Integer ROAD_END) {
        for (Player player : playerList) {
            if (player.getPosition().compareTo(ROAD_END) == 0) {
                System.out.println(player.getName().concat(" WON !! "));
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer getNextPlayerIndex() {
        playerIndex = random.nextInt(playerList.size());
        return playerIndex;
    }

    @Override
    public boolean checkIsSkipped() {
        Player p = getCurrentPlayer();
        if (p.getMustSkip()) {
            playerList.get(playerIndex).setMustSkip(Boolean.FALSE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Player getCurrentPlayer() {
        return playerList.get(playerIndex);
    }

    @Override
    public void moveCurrentPlayer(Integer input) {
        movePlayer(playerIndex, input);
    }
}
