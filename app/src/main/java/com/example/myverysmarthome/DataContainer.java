package com.example.myverysmarthome;

import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.DeviceType;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.devices.Light;
import com.example.myverysmarthome.model.devices.Thermostat;
import com.example.myverysmarthome.model.User;

import java.util.ArrayList;

public final class DataContainer {
    private static DataContainer INSTANCE;
    private static Boolean isFirstLaunch = true;
    public ArrayList<User> users;
    public ArrayList<Device> devices;
    public ArrayList<Group> groups;
    public ArrayList<Category> categories;
    public String hubUuid;
    public String mac;

    private DataContainer() {
        users = new ArrayList<>();
        groups = new ArrayList<>();
        devices = new ArrayList<>();
        categories = new ArrayList<>();

        devices.add(new Light("Gorne"));
        devices.add(new Light("Gorne 2"));
        devices.add(new Light("Gorne 3"));

        ArrayList<String> allDevices = new ArrayList<>();
        for (Device device : devices) {
            allDevices.add(device.getUuid());
        }
        groups.add(new Group("Sypialnia", allDevices));
        groups.add(new Group("Sypialnia 2", allDevices));
        categories.add(new Category("Swiatło", allDevices, R.drawable.lightbulb));
        categories.add(new Category("Termostat", new ArrayList<>(), R.drawable.temperature));
        categories.add(new Category("Kamera", new ArrayList<>(), R.drawable.camera));
        categories.add(new Category("Włącznik", new ArrayList<>(), R.drawable.plug));
        categories.add(new Category("Wiatrak", new ArrayList<>(), R.drawable.fan));
        categories.add(new Category("Wszystko", allDevices, R.drawable.all));
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

    public static Boolean getIsFirstLaunch() {
        return isFirstLaunch;
    }

    public static void setIsFirstLaunch(Boolean isFirstLaunch) {
        DataContainer.isFirstLaunch = isFirstLaunch;
    }

    public Device getDevice(String uuid) {
        for (Device device : DataContainer.getInstance().devices) {
            if (device.getUuid().equals(uuid)) {
                return device;
            }
        }
        return null;
    }

    public void removeDevice(Device device) {
        devices.remove(device);
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

    public ArrayList<String> getCategoriesNames() {
        ArrayList<String> result = new ArrayList<>();
        categories.forEach(c -> result.add(c.getTitle()));
        return result;
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
        switch(deviceType) {
            case LIGHT:
                newDevice = new Light(name);
                break;
            case THERMOSTAT:
                newDevice = new Thermostat(name);
                break;
            default:
                throw new IllegalStateException();
        }

        devices.add(newDevice);
        return newDevice;
    }

}