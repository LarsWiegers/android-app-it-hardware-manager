package com.example.ithardwaremanager.Rooms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ithardwaremanager.Items.AddItemActivity;
import com.example.ithardwaremanager.Items.Item;
import com.example.ithardwaremanager.Items.ItemAdapter;
import com.example.ithardwaremanager.Items.ShowItemActivity;
import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.R;
import com.example.ithardwaremanager.storage.StorageManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowRoomActivity extends AppCompatActivity {

    /**
     * Hold the room in the activity so we can use it through out the different methods
     */
    Room room;
    /**
     * Handles the onCreate event that is going to happen when the activity gets called
     * @param savedInstanceState Used for android working correctly
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_room_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        room = (Room) StorageManager.getRooms().get(getIntent().getIntExtra("roomIndex", 0));
        setText();

        setupFab();

        setupRoomList();
    }

    /**
     * Set the text that is displayed on the screen
     */
    private void setText() {
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);

        name.setText(room.getName());
        description.setText(room.getDescription());
    }

    /**
     * Setup the Floating action button
     */
    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ShowRoomActivity.this, AddItemActivity.class);
            intent.putExtra("room", (Parcelable) room);
            startActivity(intent);
        });
    }

    /**
     * Handle the onBackPressed event, this is to keep the expected navigation route intact
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowRoomActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Sets up the room list that is the main point of this activity
     */
    private void setupRoomList() {
        ArrayList<Item> items = room.getItems();
        BaseAdapter roomAdapter = new ItemAdapter(items, view -> {
            Intent intent = new Intent(ShowRoomActivity.this, ShowItemActivity.class);
            ConstraintLayout parent = (ConstraintLayout) view.getParent();
            TextView name = parent.findViewById(R.id.name);

            Parcelable item = Item.getByName(items, name.getText().toString());
            intent.putExtra("item", item);
            intent.putExtra("room", (Parcelable) room);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.itemList);
        listView.setAdapter(roomAdapter);
    }

    /**
     * Handle the onclick of the delete button, delete room in storage and then go to the room list screen
     * @param view the button that is clicked
     */
    public void onDeleteClick(View view) {
        StorageManager.removeRoom(room);
        Intent intent = new Intent(ShowRoomActivity.this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * Go to the editRoomActivity with the room given
     * @param view the button that is clicked
     */
    public void onEditClick(View view) {
        Intent intent = new Intent(ShowRoomActivity.this, EditRoomActivity.class);
        intent.putExtra("room", (Parcelable) room);
        startActivity(intent);
    }
}
