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
import com.example.ithardwaremanager.Rooms.Room;
import com.example.ithardwaremanager.Rooms.ShowRoomActivity;

public class EditItemActivity extends BaseActivity {
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getSerializableExtra("room") != null) {
            Intent intent = getIntent();
            room = (Room) intent.getSerializableExtra("room");
        }

        if(getIntent().getSerializableExtra("item") != null) {
            Intent intent = getIntent();
            room = (Room) intent.getSerializableExtra("room");
        }

    }

    public void onClickAddButton(View view) {
        Intent intent = new Intent(EditItemActivity.this, ShowRoomActivity.class);
        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);
        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();

        intent.putExtra("item",(Parcelable) new Item(name, description));
        intent.putExtra("room",(Parcelable) room);
        startActivity(intent);
    }
}
