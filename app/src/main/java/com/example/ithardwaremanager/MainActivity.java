package com.example.ithardwaremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ithardwaremanager.Rooms.AddRoomActivity;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.RoomAdapter;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;
import com.example.ithardwaremanager.storage.StorageManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ArrayList<Room> rooms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("lifeCycle: ", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StorageManager.setContext(this);
        StorageManager.read();
        this.rooms = StorageManager.getRooms();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifeCycle: ", "onResume");
        this.rooms = StorageManager.getRooms();
        BaseAdapter roomAdapter = new RoomAdapter(rooms, view -> {
            Intent intent = new Intent(MainActivity.this, ShowRoomActivity.class);
            ConstraintLayout parent = (ConstraintLayout) view.getParent();
            TextView id = parent.findViewById(R.id.listItemId);
            TextView name = parent.findViewById(R.id.name);
            intent.putExtra("name", name.getText().toString());
            Log.i("id = ", id.getText().toString());
            Room room = StorageManager.findRoomById(id.getText().toString());
            intent.putExtra("room", room);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.roomListView);
        listView.setAdapter(roomAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifeCycle: ", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifeCycle: ", "onPause");
        try {
            StorageManager.save(rooms);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifeCycle: ", "onResume");
    }
}
