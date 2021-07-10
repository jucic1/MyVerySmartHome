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

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;

public final class DataContainer {
    private static DataContainer INSTANCE;
    public ArrayList<Device> devices;
    public ArrayList<Group> groups;
    public ArrayList<Category> categories;

    private DataContainer() {
        groups = new ArrayList<>();
        devices = new ArrayList<>();
        categories = new ArrayList<>();
        categories.add(new Category("Światło", R.drawable.lightbulb, DeviceType.LIGHT));
        categories.add(new Category("Termostat", R.drawable.temperature, DeviceType.THERMOSTAT));
        categories.add(new Category("Kamera",  R.drawable.camera, DeviceType.CAMERA));
        categories.add(new Category("Włącznik", R.drawable.plug, DeviceType.PLUG));
        categories.add(new Category("Wiatrak",  R.drawable.fan, DeviceType.PLUG));
        categories.add(new Category("Wszystko",  R.drawable.all, null));
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public ArrayList<Device> getDevicesOfType(DeviceType deviceType) {
        ArrayList<Device> result = new ArrayList<>();
        for(Device device: devices) {
            if(device.getClass().getSimpleName().toLowerCase().equals(deviceType.name().toLowerCase())) {
                result.add(device);
            }
        }
        return result;
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
        int indexToRemove = -1;
        for (int i = 0; i< devices.size(); i++) {
            if (devices.get(i).getUuid().equals(uuid)) {
                indexToRemove = i;
            }
        }
        devices.remove(indexToRemove);
    }

    public Category getCategoryForDevice(Device device) {
        for(Category category: categories) {
            if(category.getUuid().equals(device.getCategoryId())) {
                return category;
            }
        }
        return null;
    }

    public ArrayList<Device> getDevicesForCategory(Category category) {
        ArrayList<Device> result = new ArrayList<>();
        for(Device device : devices) {
            if(device.getCategoryId().equals(category.getUuid())) {
                result.add(device);
            }
        }
        return result;
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

    public Device createDevice(String name, Category category) {
        Device newDevice;
        switch (category.getDeviceType()) {
            case LIGHT:
                newDevice = new Light(name, category.getUuid());
                break;
            case THERMOSTAT:
                newDevice = new Thermostat(name, category.getUuid());
                break;
            case PLUG:
                newDevice = new Plug(name, category.getUuid());
                break;
            case CAMERA:
                newDevice = new Camera(name, category.getUuid());
                break;
            case FAN:
                newDevice = new Fan(name, category.getUuid());
                break;
            default:
                throw new IllegalStateException();
        }
        devices.add(newDevice);
        return newDevice;
    }

}