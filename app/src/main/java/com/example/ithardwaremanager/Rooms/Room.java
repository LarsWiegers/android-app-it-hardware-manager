package com.example.ithardwaremanager.Rooms;

public class Room {
    private String name;

    public Room(String name) {
        this.setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
