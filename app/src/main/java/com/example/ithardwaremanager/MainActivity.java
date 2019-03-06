package com.example.ithardwaremanager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ithardwaremanager.Rooms.AddRoomActivity;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.RoomAdapter;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;
import com.example.ithardwaremanager.storage.StorageManager;

public class MainActivity extends AppCompatActivity {

    private static boolean filledWithTestData = false;
    /**
     * Handles the onCreate event that is going to happen when the activity gets called
     * @param savedInstanceState Used for android working correctly
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!filledWithTestData) {
            StorageManager.fillWithTestData();
            filledWithTestData = true;
        }

        setupFab();

        setupRoomList();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("config", newConfig.toString());
        Toast.makeText(this, Configuration.ORIENTATION_LANDSCAPE, Toast.LENGTH_SHORT).show();
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets up the room list that is the main point of this activity
     */
    private void setupRoomList() {
        BaseAdapter roomAdapter = new RoomAdapter(StorageManager.getRooms(), view -> {
            Intent intent = new Intent(MainActivity.this, ShowRoomActivity.class);

            ConstraintLayout parent = (ConstraintLayout) view.getParent();
            TextView name = parent.findViewById(R.id.name);

            Parcelable room = Room.getByName(StorageManager.getRooms(), name.getText().toString());
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
