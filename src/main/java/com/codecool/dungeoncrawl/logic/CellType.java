package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
    SHIELD("shield"),
    KEY("key"),

    CLOSED_DOOR("closedDoor"),
    OPENED_DOOR("openedDoor"),

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

