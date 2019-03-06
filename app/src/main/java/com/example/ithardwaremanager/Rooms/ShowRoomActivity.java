package com.example.ithardwaremanager.Rooms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ithardwaremanager.Items.AddItemActivity;
import com.example.ithardwaremanager.Items.Item;
import com.example.ithardwaremanager.Items.ItemAdapter;
import com.example.ithardwaremanager.Items.ShowItemActivity;
import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.storage.StorageManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowRoomActivity extends AppCompatActivity {

    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_room_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        room = (Room) StorageManager.getRooms().get(getIntent().getIntExtra("roomIndex", 0));
        TextView text = findViewById(R.id.name);
        text.setText(room.getName());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ShowRoomActivity.this, AddItemActivity.class);
            intent.putExtra("room", (Parcelable) room);
            startActivity(intent);
        });

        setupRoomList();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowRoomActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Sets up the room list that is the main point of this activity
     */
    private void setupRoomList() {
        ArrayList<Item> items = room.getItems();
        BaseAdapter roomAdapter = new ItemAdapter(items, view -> {
            Intent intent = new Intent(ShowRoomActivity.this, ShowItemActivity.class);
            ConstraintLayout parent = (ConstraintLayout) view.getParent();
            TextView name = parent.findViewById(R.id.name);

            Parcelable item = Item.getByName(items, name.getText().toString());
            intent.putExtra("item", item);
            intent.putExtra("room", (Parcelable) room);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.itemList);
        listView.setAdapter(roomAdapter);
    }

    public void onDeleteClick(View view) {
        StorageManager.removeRoom(room);
        Intent intent = new Intent(ShowRoomActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onEditClick(View view) {
        Intent intent = new Intent(ShowRoomActivity.this, EditRoomActivity.class);
        intent.putExtra("room", (Parcelable) room);
        startActivity(intent);
    }
}
