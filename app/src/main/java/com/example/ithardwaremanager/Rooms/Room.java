package com.example.ithardwaremanager.Rooms;

import com.example.ithardwaremanager.Items.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    private String name;
    private ArrayList<Item> items = new ArrayList<Item>();
    private String id;
    private String description;

    public Room(String id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    private void setDescription(String description) {
        this.description = description;
    }
    private String getDescription() {
        return this.description;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
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
        obj.put("id", this.getId());
        return obj;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public String toString() {
        return this.getName();
    }
}
