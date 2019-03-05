package com.example.ithardwaremanager.storage;

import android.os.Parcelable;
import android.util.Log;

import com.example.ithardwaremanager.Rooms.Room;
import java.util.ArrayList;

public class StorageManager {
    private static ArrayList<Parcelable> rooms = new ArrayList<>();
    public static void addRoom(Room room) {
        rooms.add(room);
    }

    public static void removeRoom(Room room) {
        for (int i = 0; i < rooms.size(); i++) {
            Room currentRoom = (Room) rooms.get(i);
            if(currentRoom.getName().equals(room.getName())) {
                rooms.remove(i);
            }
        }
    }

    public static int getIndex(Room room) {
        for (int i = 0; i < rooms.size(); i++) {
            Room currentRoom = (Room) rooms.get(i);
            if(currentRoom.getName().equals(room.getName())) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Parcelable> getRooms() {
        return rooms;
    }

    public static void updateRoom(Room room, int index) {
        rooms.set(index, room);
    }
}
