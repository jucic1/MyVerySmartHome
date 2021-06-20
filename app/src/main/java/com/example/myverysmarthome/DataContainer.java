package com.example.myverysmarthome;

import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.Device;
import com.example.myverysmarthome.model.DeviceGroupItem;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class DataContainer {
    private static DataContainer INSTANCE;
    public ArrayList<User> users;
    public ArrayList<DeviceGroupItem> changeableDeviceItems;
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



        changeableDeviceItems = new ArrayList<>();
        ArrayList<ChangeableDeviceItem> bedroomDevices2 = new ArrayList<>(Arrays.asList(new ChangeableDeviceItem("Lampka 1"), new ChangeableDeviceItem("Lampka 2"),
                new ChangeableDeviceItem("Górne"), new ChangeableDeviceItem("Wiatrak"), new ChangeableDeviceItem("Termostat")));
        changeableDeviceItems.add(new DeviceGroupItem("Sypialnia", bedroomDevices2));
        changeableDeviceItems.add(new DeviceGroupItem("Sypialnia 2", bedroomDevices2));
    }

    public Device getDevice(String uuid) {
        for(Device device : DataContainer.getInstance().devices) {
            if(device.getUuid().equals(uuid)) {
                return device;
            }
        }
        return null;
    }

    public static DataContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataContainer();
        }
        return INSTANCE;
    }


}