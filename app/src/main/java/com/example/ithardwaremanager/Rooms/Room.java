package com.example.ithardwaremanager.Rooms;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.ithardwaremanager.Items.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable, Parcelable {
    private String name;
    private ArrayList<Item> items = new ArrayList<>();
    private String description;

    /**
     * Main constructor used
     * @param name the name
     * @param description the description
     */
    public Room(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    /**
     *
     * @param rooms the rooms we are looking through
     * @param name the name we are searching for
     * @return the found room or null
     */
    public static Parcelable getByName(ArrayList<Parcelable> rooms, String name) {
        for (Parcelable room: rooms) {
            if(((Room) room).getName().equals(name)) {
                return room;
            }
        }
        return null; // TODO should probably throw an exception here
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        Log.i("addItem", items.toString());
        Log.i("addItem", item.toString());
        this.items.add(item);
        Log.i("addItem", items.toString());
        Log.i("addItem", item.toString());
    }

    public String toString() {
        return this.getName() + ", " + this.items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getName());
        dest.writeString(this.getDescription());
        dest.writeTypedList(this.getItems());
    }

    protected Room(Parcel in) {
        name = in.readString();
        description = in.readString();
        items = in.createTypedArrayList(Item.CREATOR);
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    /**
     * Remove an item from the current items we have
     * @param item the item to be removed
     */
    public void removeItem(Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if(this.items.get(i).getName().equals(item.getName())) {
                this.items.remove(i);
            }
        }
        this.items.remove(item);
    }
}
