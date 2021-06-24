package com.example.myverysmarthome.addgroup;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.alldevices.ListDevicesAdapter;
import com.example.myverysmarthome.model.Device;

import java.util.ArrayList;
import java.util.Arrays;

public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupAdapter.AddGroupViewHolder>{

    private ArrayList<Device> allDevices;

    public AddGroupAdapter() {
//                allDevices = new ArrayList<>(Arrays.asList(new Device("Swiatło 1"), new Device("Swiatło 2"),
//                new Device("Wiatrak"), new Device("Termostat")));
    }

    @NonNull
    @Override
    public AddGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AddGroupViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return allDevices.size();
    }

    class AddGroupViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView statusTextView;

        public AddGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
        }

        void bind(Device device) {
            this.nameTextView.setText(device.getName());
            this.statusTextView.setText(device.getStatus().toString());
        }
    }
}
