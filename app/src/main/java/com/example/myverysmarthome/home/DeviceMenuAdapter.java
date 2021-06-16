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

public class DeviceMenuAdapter extends RecyclerView.Adapter<DeviceMenuAdapter.DeviceMenuViewHolder> {

    private ArrayList<DeviceMenuItem> devicesInMenu;

    DeviceMenuAdapter(){
        devicesInMenu = new ArrayList<>(Arrays.asList(new DeviceMenuItem("Swiatło"), new DeviceMenuItem("Wtyczki"),
                new DeviceMenuItem("Termostat"), new DeviceMenuItem("Kamery"), new DeviceMenuItem("Wszytsko")));
    }

    @NonNull
    @Override
    public DeviceMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device,parent,false);
                return new DeviceMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceMenuViewHolder holder, int position) {
        holder.titleTextView.setText(devicesInMenu.get(position).title);
    }

    @Override
    public int getItemCount() {
        return devicesInMenu.size();
    }

    class DeviceMenuViewHolder extends  RecyclerView.ViewHolder{
        TextView titleTextView;

        public DeviceMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.deviceName);
        }
    }
}
