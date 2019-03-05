package com.example.ithardwaremanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ithardwaremanager.Rooms.AddRoomActivity;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.RoomAdapter;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;
import com.example.ithardwaremanager.storage.StorageManager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ArrayList<Parcelable> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("lifeCycle: ", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFab();

        setupRoomList();
    }

    /**
     * Sets up the room list that is the main point of this activity
     */
    private void setupRoomList() {
        rooms = StorageManager.getRooms();
        BaseAdapter roomAdapter = new RoomAdapter(rooms, view -> {
            Intent intent = new Intent(MainActivity.this, ShowRoomActivity.class);

            ConstraintLayout parent = (ConstraintLayout) view.getParent();
            TextView name = parent.findViewById(R.id.name);

            Parcelable room = Room.getByName(rooms, name.getText().toString());
            intent.putExtra("room", room);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.roomListView);
        listView.setAdapter(roomAdapter);
    }

    /**
     * Registers fab and adds listener to it
     */
    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivity(intent);
        });
    }
}
