package com.example.ithardwaremanager.Items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.ithardwaremanager.R;
public class ShowItemActivity extends AppCompatActivity {
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getParcelableExtra("item") != null) {
            Intent intent = getIntent();
            item = intent.getParcelableExtra("item");
        }

        TextView nameField = findViewById(R.id.name);
        TextView descriptionField = findViewById(R.id.description);

        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();

        nameField.setText(name);
        descriptionField.setText(description);
    }
}
