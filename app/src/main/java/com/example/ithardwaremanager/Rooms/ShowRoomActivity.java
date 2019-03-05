package com.example.ithardwaremanager.Rooms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
        Log.i("rooms 1", room.getItems().toString());
        Log.i("rooms 1", "" + getIntent().getIntExtra("roomIndex", 0));
        Log.i("abc", StorageManager.getRooms().toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ShowRoomActivity.this, AddItemActivity.class);
            intent.putExtra("room", (Parcelable) room);
            startActivity(intent);
        });

        setupRoomList();
    }

    /**
     * Sets up the room list that is the main point of this activity
     */
    private void setupRoomList() {
        Log.i("rooms ", room.getItems().toString());
        BaseAdapter roomAdapter = new ItemAdapter(room.getItems(), view -> {
            Intent intent = new Intent(ShowRoomActivity.this, ShowItemActivity.class);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.itemList);
        listView.setAdapter(roomAdapter);
    }

    public void onDeleteClick(View view) {
        StorageManager.removeRoom(room);
        Log.i("rooms = ", StorageManager.getRooms().toString());
        Log.i("room= ", room.toString());
        Intent intent = new Intent(ShowRoomActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onEditClick(View view) {
        Intent intent = new Intent(ShowRoomActivity.this, EditRoomActivity.class);
        intent.putExtra("room", (Parcelable) room);
        startActivity(intent);
    }
}
