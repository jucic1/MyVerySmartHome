package com.example.myverysmarthome;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.example.myverysmarthome.home.ConfigureHubDialogFragment;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.Fan;
import com.example.myverysmarthome.model.devices.Light;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyApplication extends Application {
    Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MY_KEY", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("LAUNCH", false)) {
            ArrayList<Category> newCategories = new ArrayList<>();
            newCategories.add(new Category("Swiatło", new ArrayList<>(), R.drawable.lightbulb));
            newCategories.add(new Category("Termostat", new ArrayList<>(), R.drawable.temperature));
            newCategories.add(new Category("Kamera", new ArrayList<>(), R.drawable.camera));
            newCategories.add(new Category("Włącznik", new ArrayList<>(), R.drawable.plug));
            newCategories.add(new Category("Wiatrak", new ArrayList<>(), R.drawable.fan));
            newCategories.add(new Category("Wszystko", new ArrayList<>(), R.drawable.all));
            String json = gson.toJson(newCategories);
            writeToFile("categories.txt", json);
            writeToFile("devices.txt", "");
            writeToFile("groups.txt", "");

            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("LAUNCH", true);
            edit.apply();
        }

        String fromJsonCategories = readFromFile("categories.txt");
        Type listTypeCategory = new TypeToken<ArrayList<Category>>() {}.getType();
        ArrayList<Category> categories = gson.fromJson(fromJsonCategories, listTypeCategory);
        if (categories == null ) {
            categories = new ArrayList<>();
        }

        String fromJsonDevices = readFromFile("devices.txt");
        Type listTypeDevice = new TypeToken<ArrayList<Device>>() {}.getType();
        ArrayList<Device> devices = gson.fromJson(fromJsonDevices, listTypeDevice);
        if (devices == null ) {
            devices = new ArrayList<>();
        }

        String fromJsonGroups = readFromFile("groups.txt");
        Type listTypeGroup = new TypeToken<ArrayList<Group>>() {}.getType();
        ArrayList<Group> groups = gson.fromJson(fromJsonGroups, listTypeGroup);
        if (groups == null ) {
            groups = new ArrayList<>();
        }

        DataContainer.getInstance().setCategories(categories);
        DataContainer.getInstance().setDevices(devices);
        DataContainer.getInstance().setGroups(groups);
    }

    public void writeToFile(String filename, String text) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, MODE_PRIVATE); //only our app can access this file
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String readFromFile(String filename) {
        FileInputStream fis = null;
        StringBuilder sb = null;
        try {
            fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

