package com.example.myverysmarthome;

import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.DeviceGroupItem;
import com.example.myverysmarthome.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class DataContainer {
    private static DataContainer INSTANCE;
    public ArrayList<User> users;
    public ArrayList<DeviceGroupItem> changeableDeviceItems;

    //  urzadzenia beda pojedenczym modelem ktory bedzie mial pole UUID oraz imie urzadzenie
    // kolejnym modelem bedzie "menu" urzadzeniem np. swiatlo ktora bedzie miala liste stringow, stringi beda repreznetowac UUID "urzadzenia"
    // kolejnym pbiektem bedzie "grupa" ktora bedzie miala JW - DONE
    //
    //

    private DataContainer() {
        users = new ArrayList<>();
        changeableDeviceItems = new ArrayList<>();
        ArrayList<ChangeableDeviceItem> bedroomDevices = new ArrayList<>(Arrays.asList(new ChangeableDeviceItem("Lampka 1"), new ChangeableDeviceItem("Lampka 2"),
                new ChangeableDeviceItem("Górne"), new ChangeableDeviceItem("Wiatrak"), new ChangeableDeviceItem("Termostat")));
        changeableDeviceItems.add(new DeviceGroupItem("Sypialnia", bedroomDevices));
        changeableDeviceItems.add(new DeviceGroupItem("Sypialnia 2", bedroomDevices));
    }

    public static DataContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataContainer();
        }

        return INSTANCE;
    }


}