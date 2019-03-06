package com.example.ithardwaremanager.Items;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.views.DrawView;
import com.example.ithardwaremanager.views.listItem;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private final View.OnClickListener listener;
    ArrayList<Item> items;

    public ItemAdapter(ArrayList<Item> rooms, View.OnClickListener listener) {
        if(rooms != null) {
            this.items = rooms;
        }else {
            this.items = new ArrayList<>();
        }
        this.listener = listener;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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
        DrawView.shouldDraw(false);
        Item item = (Item) this.getItem(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(item.getName());

        Button button = listItem.findViewById(R.id.button);
        button.setOnClickListener(listener);

        return listItem;
    }
}
