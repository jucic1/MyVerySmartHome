package com.example.myverysmarthome;

import android.app.Application;
import android.os.Environment;

import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.Group;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.Fan;
import com.example.myverysmarthome.model.devices.Light;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FileOutputStream fOut = null;
        try {
            fOut = openFileOutput("samplefile.txt",
                    MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            Gson gson = new Gson();

            ArrayList<Category> categories = new ArrayList<>();
            categories.add(new Category("title", new ArrayList<>(), 123));
//            Category category = gson.fromJson(Category.testJson, Category.class);

            String json = gson.toJson(categories);
            osw.write(json);
            osw.flush();
            osw.close();

            FileInputStream fIn = openFileInput("samplefile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
//            Category[] category = gson.fromJson(Category.testJson, Category.class);

//            char[] inputBuffer = new char[TESTSTRING.length()];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

