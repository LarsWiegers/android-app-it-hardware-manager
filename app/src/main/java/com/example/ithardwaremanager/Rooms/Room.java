package com.example.ithardwaremanager.Rooms;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewParent;

import com.example.ithardwaremanager.Items.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable, Parcelable {
    private String name;
    private ArrayList<Parcelable> items = new ArrayList<>();
    private String description;

    public Room(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public static Parcelable getByName(ArrayList<Parcelable> rooms, String name) {
        for (Parcelable room: rooms) {
            if(((Room) room).getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    private void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public Room(JSONObject obj) throws JSONException {
        this.setName(obj.getString("name"));
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public JSONObject toJSONObject() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", this.getName());
        obj.put("description", this.getDescription());
        return obj;
    }

    public ArrayList<Parcelable> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getName());
        dest.writeString(this.getDescription());
    }

    protected Room(Parcel in) {
        name = in.readString();
        description = in.readString();
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
}
