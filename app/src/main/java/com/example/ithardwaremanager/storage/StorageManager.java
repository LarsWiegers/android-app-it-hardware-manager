package com.example.ithardwaremanager.storage;

import android.os.Parcelable;

import com.example.ithardwaremanager.Items.Item;
import com.example.ithardwaremanager.Rooms.Room;
import java.util.ArrayList;

public class StorageManager {
    /**
     * Handles the main storage of the rooms / items inside this app
     */
    private static ArrayList<Parcelable> rooms = new ArrayList<>();

    public static void fillWithTestData() {
        Room room = new Room("room with items", "This room has items");
        room.addItem(new Item("I am a item", "this is a description"));
        rooms.add(room);

        Room roomWithoutItems = new Room("room without items", "no items to be found here");
        rooms.add(roomWithoutItems);
    }
    /**
     * Add a room
     * @param room the room we want to add
     */
    public static void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Remove the given room
     * @param room
     */
    public static void removeRoom(Room room) {
        for (int i = 0; i < rooms.size(); i++) {
            Room currentRoom = (Room) rooms.get(i);
            if(currentRoom.getName().equals(room.getName())) {
                rooms.remove(i);
            }
        }
    }

    /**
     * Returns the index of the room found by name if it can or returns -1 if not found
     * @param room the room we are looking form
     * @return index of the room or -1 if not found
     */
    public static int getIndex(Room room) {
        for (int i = 0; i < rooms.size(); i++) {
            Room currentRoom = (Room) rooms.get(i);
            if(currentRoom.getName().equals(room.getName())) {
                return i;
            }
        }
        // TODO should probably throw an exception here
        return -1;
    }

    /**
     * Return the rooms
     * @return rooms
     */
    public static ArrayList<Parcelable> getRooms() {
        return rooms;
    }

    /**
     * Update a room that is already in the list
     * @param room the room we are going to save
     * @param index the index of the room we want to update
     */
    public static void updateRoom(Room room, int index) {
        rooms.set(index, room);
    }
}
