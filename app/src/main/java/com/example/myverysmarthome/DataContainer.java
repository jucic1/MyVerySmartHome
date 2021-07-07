package com.example.myverysmarthome;

import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.devices.Camera;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.DeviceType;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.devices.Fan;
import com.example.myverysmarthome.model.devices.Light;
import com.example.myverysmarthome.model.devices.Plug;
import com.example.myverysmarthome.model.devices.Thermostat;
import com.example.myverysmarthome.model.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class DataContainer {
    private static DataContainer INSTANCE;
    public ArrayList<Device> devices;
    public ArrayList<Group> groups;
    public ArrayList<Category> categories;
    public String hubUuid;
    public String mac;

    private DataContainer() {
        groups = new ArrayList<>();
        devices = new ArrayList<>();
        categories = new ArrayList<>();
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getHubUuid() {
        return hubUuid;
    }

    public void setHubUuid(String hubUuid) {
        this.hubUuid = hubUuid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Device getDevice(String uuid) {
        for (Device device : DataContainer.getInstance().devices) {
            if (device.getUuid().equals(uuid)) {
                return device;
            }
        }
        return null;
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public static DataContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataContainer();
        }
        return INSTANCE;
    }

    public void removeDevice(String uuid) {
        for (Device device : devices) {
            if (device.getUuid().equals(uuid)) {
                devices.remove(device);
            }
        }
    }

    public Category getAllCategory() {
        for (Category category : categories) {
            if (category.getTitle().equals("Wszystko")) {
                return category;
            }
        }
        return null;
    }

    public Category getCategory(String id) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    public void createGroup(String name, ArrayList<Device> groupDevices) {
        Group newGroup = new Group(name, getUuidsFromDeviceList(groupDevices));
        groups.add(newGroup);
    }

    private ArrayList<String> getUuidsFromDeviceList(ArrayList<Device> groupDevices) {
        ArrayList<String> result = new ArrayList<>();
        groupDevices.forEach(d -> result.add(d.getUuid()));
        return result;
    }

    public Device createDevice(String name, DeviceType deviceType) {
        Device newDevice;
        switch (deviceType) {
            case LIGHT:
                newDevice = new Light(name);
                break;
            case THERMOSTAT:
                newDevice = new Thermostat(name);
                break;
            case PLUG:
                newDevice = new Plug(name);
                break;
            case CAMERA:
                newDevice = new Camera(name);
                break;
            case FAN:
                newDevice = new Fan(name);
                break;
            default:
                throw new IllegalStateException();
        }
        devices.add(newDevice);
        return newDevice;
    }
}