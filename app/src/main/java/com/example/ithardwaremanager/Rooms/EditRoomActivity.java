package com.example.ithardwaremanager.Rooms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ithardwaremanager.BaseActivity;
import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.R;

public class EditRoomActivity extends BaseActivity {

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

            TextView nameField = findViewById(R.id.name);
            TextView descriptionField = findViewById(R.id.description);

            nameField.setText(room.getName());
            descriptionField.setText(room.getDescription());
        }


    }

    public void onClickAddButton(View view) {

        Intent intent = new Intent(EditRoomActivity.this, MainActivity.class);

        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);

        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();
        Room room = new Room( name, description);
        intent.putExtra("room", (Parcelable) room);
        Log.i("room tes",((Parcelable) room).toString() );
        startActivity(intent);
    }
}
