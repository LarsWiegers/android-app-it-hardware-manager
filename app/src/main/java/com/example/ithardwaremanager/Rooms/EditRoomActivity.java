package com.example.ithardwaremanager.Rooms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.storage.StorageManager;

public class EditRoomActivity extends AppCompatActivity {

    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_room_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getParcelableExtra("room") != null) {
            Intent intent = getIntent();
            room = intent.getParcelableExtra("room");

            setText(room);
        }
    }

    /**
     * Set the text of the fields, should only be called after the room is set
     */
    private void setText(Room room) {
        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);

        nameField.setText(room.getName());
        descriptionField.setText(room.getDescription());
    }

    /**
     * Handle the add click button event, get the data and add it to storage, go back to prev view
     * @param view the add button
     */
    public void onClickAddButton(View view) {

        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);

        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();


        int index = StorageManager.getIndex(room);
        room.setName(name);
        room.setDescription(description);
        StorageManager.updateRoom(room, index);

        Intent intent = new Intent(EditRoomActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
