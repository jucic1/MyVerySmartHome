package com.example.myverysmarthome;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.example.myverysmarthome.home.ConfigureHubDialogFragment;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.devices.Camera;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.DeviceType;
import com.example.myverysmarthome.model.devices.Fan;
import com.example.myverysmarthome.model.devices.Light;
import com.example.myverysmarthome.model.devices.Plug;
import com.example.myverysmarthome.model.devices.Thermostat;
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
import java.lang.reflect.AnnotatedElement;
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
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PRIMITIVE", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("LAUNCH", false)) {
            writeToFile("groups.txt", "");

            for (DeviceType deviceType : DeviceType.values()) {
                writeToFile(deviceType.toString().toLowerCase() + ".txt", "");
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("LAUNCH", true);
            edit.apply();
        }

        String fromJsonGroups = readFromFile("groups.txt");
        Type listTypeGroup = new TypeToken<ArrayList<Group>>() {
        }.getType();
        ArrayList<Group> groups = gson.fromJson(fromJsonGroups, listTypeGroup);
        if (groups == null) {
            groups = new ArrayList<>();
        }

        ArrayList<Device> allDevices = new ArrayList<>();
        for (DeviceType deviceType : DeviceType.values()) {
            String fromJsonDevices = readFromFile(deviceType.toString().toLowerCase() + ".txt");
            Type listTypeDevice;
            switch (deviceType) {
                case LIGHT:
                    listTypeDevice = new TypeToken<ArrayList<Light>>() {
                    }.getType();
                    break;
                case THERMOSTAT:
                    listTypeDevice = new TypeToken<ArrayList<Thermostat>>() {
                    }.getType();
                    break;
                case PLUG:
                    listTypeDevice = new TypeToken<ArrayList<Plug>>() {
                    }.getType();
                    break;
                case CAMERA:
                    listTypeDevice = new TypeToken<ArrayList<Camera>>() {
                    }.getType();
                    break;
                case FAN:
                    listTypeDevice = new TypeToken<ArrayList<Fan>>() {
                    }.getType();
                    break;
                default:
                    throw new IllegalStateException();
            }
            ArrayList<Device> devices = gson.fromJson(fromJsonDevices, listTypeDevice);
            if (devices != null) {
                allDevices.addAll(devices);
            }
        }
        DataContainer.getInstance().setDevices(allDevices);
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

