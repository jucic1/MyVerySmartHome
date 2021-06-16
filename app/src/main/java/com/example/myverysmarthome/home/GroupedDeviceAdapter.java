package com.example.myverysmarthome.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.DeviceMenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupedDeviceAdapter extends RecyclerView.Adapter<GroupedDeviceAdapter.GroupedDeviceViewHolder> {

    private ArrayList<DeviceMenuItem> devicesInGroup;

    GroupedDeviceAdapter() {
        devicesInGroup = new ArrayList<>(Arrays.asList(new DeviceMenuItem("Lampka 1"), new DeviceMenuItem("Lampka 2"),
                new DeviceMenuItem("Gorne"), new DeviceMenuItem("Termostat"), new DeviceMenuItem("Wiatrak")));
    }

    @NonNull
    @Override
    public GroupedDeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_group, parent, false);
        return new GroupedDeviceAdapter.GroupedDeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupedDeviceViewHolder holder, int position) {
        holder.titleTextView.setText(devicesInGroup.get(position).title);
    }

    @Override
    public int getItemCount() {
        return devicesInGroup.size();
    }

    class GroupedDeviceViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public GroupedDeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.deviceName);
        }
    }
}