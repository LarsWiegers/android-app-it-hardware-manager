package com.example.ithardwaremanager.Items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.Rooms.Room;

import org.json.JSONException;

public class AddItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClickAddButton(View view) {
        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
        TextView nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();
//        try {
//            intent.putExtra("newRoom", new Room(name));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        startActivity(intent);
    }
}
