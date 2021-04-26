package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static de.tabit.test.alexandria.engine.dummy.FieldServices.generateBonus;
import static de.tabit.test.alexandria.engine.dummy.FieldServices.generateTraps;

class EngineData {

    List<Player> playerList;
    List<Field> fields;

    EngineData(Integer numberOfPlayers, Integer roadLength, Integer trapNum, Integer bonusNum) {
           createPlayerList(numberOfPlayers);
        this.fields = generateFields(roadLength, trapNum, bonusNum);
    }

    private void createPlayerList(Integer numberOfPlayers) {
        this.playerList = new ArrayList<>();
        for ( int i =1; i <= numberOfPlayers;i++){
            playerList.add(new Player(""+i));
        }
    }

    private List<Field> generateFields(Integer roadLength, Integer trapNum, Integer bonusNum) {
        List<Field> road = new ArrayList<>();
        road.addAll(Arrays.asList(new Field[roadLength- bonusNum - trapNum]));
        road.addAll(generateTraps(trapNum));
        road.addAll(generateBonus(bonusNum));

        Collections.shuffle(road);

        return road;
    }


    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
