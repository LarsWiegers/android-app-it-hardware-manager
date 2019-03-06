package com.example.ithardwaremanager.Rooms;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.views.DrawView;
import com.example.ithardwaremanager.views.listItem;

import java.util.ArrayList;

public class RoomAdapter extends BaseAdapter {

    private final View.OnClickListener listener;
    ArrayList<Parcelable> rooms;

    public RoomAdapter(ArrayList<Parcelable> rooms, View.OnClickListener listener) {
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


    /**
     * Display the list item view
     * @param position the position of the list item
     * @param convertView the view
     * @param parent the parent view
     * @return a list item
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawView.shouldDraw(true);
        View listItem = convertView;
        if (convertView == null) {
             listItem = new listItem(parent.getContext());
        }
        Room room = (Room) this.getItem(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(room.getName());
        DrawView number = listItem.findViewById(R.id.numberOfItems);
        number.setText("" + room.getItems().size());

        Button button = listItem.findViewById(R.id.button);
        button.setOnClickListener(listener);

        return listItem;
    }
}
