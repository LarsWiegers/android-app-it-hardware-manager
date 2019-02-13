package com.example.ithardwaremanager.Rooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ithardwaremanager.Items.AddItemActivity;
import com.example.ithardwaremanager.R;

public class ShowRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_room_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowRoomActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        String name = (String) i.getSerializableExtra("name");
        TextView text = findViewById(R.id.name);
        text.setText(name);
    }
}
