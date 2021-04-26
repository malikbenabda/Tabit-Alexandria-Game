package de.tabit.test.alexandria.entity;

import de.tabit.test.alexandria.engine.dummy.PlayerControl;

public class Player {

    String name = "Player_";
    Integer position = 0;
    Boolean hasJoker = Boolean.FALSE;
    Boolean curedSkip = Boolean.FALSE;

    public Player(String name) {
        this.name += name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getHasJoker() {
        return hasJoker;
    }

    public void setHasJoker(Boolean hasJoker) {
        this.hasJoker = hasJoker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCuredSkip() {
        return curedSkip;
    }

    public void setCuredSkip(Boolean curedSkip) {
        this.curedSkip = curedSkip;
    }
}
