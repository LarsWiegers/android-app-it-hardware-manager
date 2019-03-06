package com.example.ithardwaremanager.Items;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ithardwaremanager.Rooms.Room;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable, Parcelable {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public static Parcelable getByName(ArrayList<Item> items, String name) {
        for (Parcelable item: items) {
            if(((Item) item).getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    protected Item(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "( " + this.getName() + " , " + this.getDescription() + " )";
    }
}
