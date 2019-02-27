package com.example.ithardwaremanager.storage;

import android.content.Context;
import android.util.Log;

import com.example.ithardwaremanager.MainActivity;
import com.example.ithardwaremanager.Rooms.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageManager {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static String filename = "rooms.json";
    private static Context context;
    public static Room findRoomById(String id) {
        return null;
    }
    public static void addRoom(Room room) {

        rooms.add(room);
    }

    public static void removeRoom(Room room) {
        rooms.remove(room);
    }
    public static ArrayList<Room> getRooms() {
        return rooms;
    }
    public static void save(ArrayList<Room> rooms) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < rooms.size(); i++) {
            JSONObject obj = rooms.get(i).toJSONObject();
            array.put(obj);
        }

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(array.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String jsonString = sb.toString();
            try {
                JSONArray array = new JSONArray(jsonString);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    rooms.add(new Room(obj));
                }
            } catch (JSONException e) {
                Log.i("JSON EXCEPTION", "ERROR");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setContext(Context mainActivityContext) {
        context = mainActivityContext;
    }

    public boolean create(Context context, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(filename,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}
