package com.example.ithardwaremanager.Items;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;

    public Item(String name) {
        this.setName(name);
    }

    public Item(JSONObject obj) throws JSONException {
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
        return obj;
    }
}
