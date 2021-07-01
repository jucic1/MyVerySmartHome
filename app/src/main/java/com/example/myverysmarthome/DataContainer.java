package com.example.myverysmarthome;

import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Device;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class DataContainer {
    private static DataContainer INSTANCE;
    public ArrayList<User> users;
    public ArrayList<Device> devices;
    public ArrayList<Group> groups;
    public ArrayList<Category> categories;

    private DataContainer() {
        users = new ArrayList<>();
        groups = new ArrayList<>();
        devices = new ArrayList<>();
        categories = new ArrayList<>();

        devices.add(new Device("Gorne"));
        devices.add(new Device("Gorne 2"));
        devices.add(new Device("Gorne 3"));

        ArrayList<String> bedroomDevices = new ArrayList<>();
        for(Device device: devices) {
            bedroomDevices.add(device.getUuid());
        }
        groups.add(new Group("Sypialnia", bedroomDevices));
        groups.add(new Group("Sypialnia 2", bedroomDevices));
        categories.add(new Category("Swiatla", bedroomDevices));
        categories.add(new Category("Termostaty", new ArrayList<>()));
    }

    public Device getDevice(String uuid) {
        for(Device device : DataContainer.getInstance().devices) {
            if(device.getUuid().equals(uuid)) {
                return device;
            }
        }
        return null;
    }

    public void removeDevice(Device device){
        devices.remove(device);
    }

    public void removeGroup(Group group){
        groups.remove(group);
    }

    public static DataContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataContainer();
        }
        return INSTANCE;
    }

    public void removeDevice(String uuid) {
        for(Device device: devices) {
            if(device.getUuid().equals(uuid)) {
                devices.remove(device);
            }
        }
    }

    public ArrayList<String> getCategoriesNames(){
        ArrayList<String> result = new ArrayList<>();
        categories.forEach(c -> result.add(c.getTitle()));
        return result;
    }

    public Category getCategory(String id) {
        for(Category category: categories) {
            if(category.getId().equals(id)) {
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

    public Device createDevice(String name) {
        Device newDevice = new Device(name);
        devices.add(newDevice);
        return newDevice;
    }
}