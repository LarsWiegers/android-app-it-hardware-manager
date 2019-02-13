package com.example.ithardwaremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ithardwaremanager.Rooms.AddRoomActivity;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.RoomAdapter;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("test"));
        ListView listView = findViewById(R.id.roomListView);
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
        listView.setAdapter(roomAdapter);

    }
}
