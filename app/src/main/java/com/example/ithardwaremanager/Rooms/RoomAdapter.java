package com.example.ithardwaremanager.Rooms;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.views.listItem;

import java.util.ArrayList;

public class RoomAdapter extends BaseAdapter {

    private final View.OnClickListener listener;
    ArrayList<Room> rooms;

    public RoomAdapter(ArrayList<Room> rooms, View.OnClickListener listener) {
        if(rooms != null) {
            this.rooms = rooms;
        }else {
            this.rooms = new ArrayList<>();
        }
        this.listener = listener;
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
        TextView id = listItem.findViewById(R.id.listItemId);
        Log.e("ROOM", "" + position);
        name.setText(room.getName());
        id.setText(room.getId());

        Button button = listItem.findViewById(R.id.button);
        button.setOnClickListener(listener);

        return listItem;
    }
}
