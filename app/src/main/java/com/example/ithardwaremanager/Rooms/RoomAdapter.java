package com.example.ithardwaremanager.Rooms;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.views.listItem;

import java.util.ArrayList;

public class RoomAdapter extends BaseAdapter {

    ArrayList<Room> rooms;

    public RoomAdapter(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (convertView == null) {
             listItem = new listItem(parent.getContext());
        }
        Room room = (Room) this.getItem(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(room.getName());
        return listItem;
    }
}
