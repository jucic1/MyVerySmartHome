package com.example.myverysmarthome.alldevices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
//import com.example.myverysmarthome.home.ItemCallback;
import com.example.myverysmarthome.model.Category;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.Device;

import java.util.ArrayList;
import java.util.Arrays;

public class ListDevicesAdapter extends RecyclerView.Adapter<ListDevicesAdapter.AllDevicesViewHolder> {

    private ArrayList<Device> allDevices;

    public ListDevicesAdapter() {
        allDevices = new ArrayList<>();
//        allDevices = new ArrayList<>(Arrays.asList(new ChangeableDeviceItem("Swiatło 1"), new ChangeableDeviceItem("Swiatło 2"),
//                new ChangeableDeviceItem("Wiatrak"), new ChangeableDeviceItem("Termostat")));
    }

    @NonNull
    @Override
    public AllDevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_on_list, parent, false);
        return new ListDevicesAdapter.AllDevicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllDevicesViewHolder holder, int position) {
        holder.nameTextView.setText(allDevices.get(position).getName());
        holder.statusTextView.setText(allDevices.get(position).getStatus().toString());
    }

    public void setItems(ArrayList<Device> deviceItems) {
        allDevices.clear();
        allDevices.addAll(deviceItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allDevices.size();
    }

    class AllDevicesViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView statusTextView;

        public AllDevicesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
        }

    }

}