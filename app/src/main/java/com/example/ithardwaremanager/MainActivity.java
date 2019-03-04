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

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ArrayList<Parcelable> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("lifeCycle: ", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadRoomsFromState(savedInstanceState);
        loadRoomsFromIntent();

        setupFab();

        setupRoomList();
    }

    /**
     * Sets up the room list that is the main point of this activity
     */
    private void setupRoomList() {
        BaseAdapter roomAdapter = new RoomAdapter(rooms, view -> {
            Intent intent = new Intent(MainActivity.this, ShowRoomActivity.class);

            ConstraintLayout parent = (ConstraintLayout) view.getParent();
            TextView name = parent.findViewById(R.id.name);

            Parcelable room = Room.getByName(this.rooms, name.getText().toString());
            intent.putExtra("room", room);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.roomListView);
        listView.setAdapter(roomAdapter);
    }

    /**
     * If there is a intent with a room
     */
    private void loadRoomsFromIntent() {
        Intent intent = getIntent();
        if(intent != null && intent.getParcelableExtra("room") != null) {
            Room room = intent.getParcelableExtra("room");
            Log.i("room flksdjlsd", room.toString());
            this.rooms.add(room);
        }
    }

    /**
     * Determines if there is a rooms Arraylist in the saved state and set it to the MainActivity
     * property. This is to not reset the rooms on this activity.
     * @param savedInstanceState is the given state
     */
    private void loadRoomsFromState(Bundle savedInstanceState) {
        Log.i("loadRoomsFromState", "tes");
        if(savedInstanceState != null && savedInstanceState.containsKey("rooms")) {
            Log.i("loadRoomsFromState = ", "" + savedInstanceState.toString());
            this.rooms = savedInstanceState.getParcelableArrayList("rooms");
            loadRoomsFromIntent();
        }else {
            this.rooms = new ArrayList<>();
        }

    }

    /**
     * Saves the current state so we can pickup later where we left off.
     * @param outState the state we are saving to
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("saving rooms", "yup");
        outState.putParcelableArrayList("rooms", this.rooms);
        Log.i("saving rooms", this.rooms.toString());
        Log.i("saving rooms", outState.toString());
        super.onSaveInstanceState(outState);
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
