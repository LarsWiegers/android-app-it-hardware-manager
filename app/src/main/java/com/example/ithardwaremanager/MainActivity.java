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
    StorageManager storageManager = new StorageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("lifeCycle: ", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFilePresent = storageManager.isFilePresent(this, "rooms.json");
        if(isFilePresent) {
            String jsonString = storageManager.read(this, "rooms.json");
            try {
                JSONArray array = new JSONArray(jsonString);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    this.rooms.add(new Room(obj));
                }
            } catch (JSONException e) {
                Log.i("lifeCycle: ", e.getMessage());

            }

        } else {
            storageManager.create(this, "rooms.json", "{}");
        }


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

        if(getIntent().getSerializableExtra("newRoom") != null) {
            Intent i = getIntent();
            Room room = (Room) i.getSerializableExtra("newRoom");
            rooms.add(room);
        }

        BaseAdapter roomAdapter = new RoomAdapter(rooms, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowRoomActivity.class);
                ConstraintLayout parent = (ConstraintLayout) view.getParent();
                TextView name = parent.findViewById(R.id.name);
                intent.putExtra("name", name.getText().toString());
                startActivity(intent);
            }
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
            storageManager.save(rooms, this);

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
