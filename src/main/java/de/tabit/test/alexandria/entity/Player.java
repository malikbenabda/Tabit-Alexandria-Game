package de.tabit.test.alexandria.entity;

public class Player {

    String name = "Player ";
    Integer position = -1;
    Boolean hasJoker = Boolean.FALSE;
    Boolean mustSkip = Boolean.FALSE;

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

    public Boolean getMustSkip() {
        return mustSkip;
    }

    public void setMustSkip(Boolean mustSkip) {
        this.mustSkip = mustSkip;
    }
}
