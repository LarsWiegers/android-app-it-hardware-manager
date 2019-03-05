package com.example.ithardwaremanager.Items;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;
import com.example.ithardwaremanager.storage.StorageManager;

public class AddItemActivity extends AppCompatActivity {
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getParcelableExtra("room") != null) {
            Intent intent = getIntent();
            room = intent.getParcelableExtra("room");
        }

    }

    public void onClickAddButton(View view) {
        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);

        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();

        Item item = new Item(name, description);
        int index = StorageManager.getIndex(room);
        Log.i("room add", room.toString());
        Log.i("room add", "" + room.getItems());
        room.addItem(item);
        Log.i("room add", "" + index);
        StorageManager.updateRoom(room, index);
        Log.i("room add", "" + StorageManager.getRooms());
        Log.i("room add", "" + room.getItems());

        Intent intent = new Intent(AddItemActivity.this, ShowRoomActivity.class);
        intent.putExtra("roomIndex", StorageManager.getIndex(room));
        startActivity(intent);
    }
}
