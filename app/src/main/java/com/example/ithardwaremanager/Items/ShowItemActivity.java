package com.example.ithardwaremanager.Items;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ithardwaremanager.BaseActivity;
import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.Rooms.EditRoomActivity;
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;
import com.example.ithardwaremanager.storage.StorageManager;

public class ShowItemActivity extends BaseActivity {
    Item item;
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getParcelableExtra("room") != null) {
            Intent intent = getIntent();
            room = intent.getParcelableExtra("room");
        }

        if(getIntent().getParcelableExtra("item") != null) {
            Intent intent = getIntent();
            item = intent.getParcelableExtra("item");
        }

        TextView nameField = findViewById(R.id.name);
        nameField.setEnabled(false);
        TextView descriptionField = findViewById(R.id.description);
        descriptionField.setEnabled(false);
        nameField.setText(item.getName());
        descriptionField.setText(item.getDescription());
    }

    public void onDeleteClick(View view) {
        int index = StorageManager.getIndex(room);
        room.removeItem(item);
        StorageManager.updateRoom(room, index);
        Intent intent = new Intent(ShowItemActivity.this, ShowRoomActivity.class);
        intent.putExtra("room", (Parcelable) room);
        startActivity(intent);
    }

    public void onEditClick(View view) {
        Intent intent = new Intent(ShowItemActivity.this, EditRoomActivity.class);
        intent.putExtra("room", (Parcelable) room);
        startActivity(intent);
    }
}
