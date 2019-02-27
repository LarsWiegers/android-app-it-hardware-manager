package com.example.ithardwaremanager.Rooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ithardwaremanager.BaseActivity;
import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.storage.StorageManager;

public class AddRoomActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_room_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickAddButton(View view) {

        Intent intent = new Intent(AddRoomActivity.this, MainActivity.class);
        TextView idField = findViewById(R.id.listItemId);
        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);

        String id = idField.getText().toString();
        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();
        Room room = new Room(id, name, description);

        StorageManager.addRoom(room);
        startActivity(intent);
    }
}
