package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
    SHIELD("shield"),
    KEY("key"),
    HEAL("heal"),

    CLOSED_DOOR("closedDoor"),
    OPENED_DOOR("openedDoor"),
    PLAYER_SWORD("player-sword"),
    PLAYER_SWORD_SHEILD("player-sword-shield"),

    SKELETON("skeleton"),
    GHOST("ghost"),
    GOLEM("golem"),
    SPIDER("spider");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

