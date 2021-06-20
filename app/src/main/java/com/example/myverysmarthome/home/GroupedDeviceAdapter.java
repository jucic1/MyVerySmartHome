package com.example.myverysmarthome.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.Device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupedDeviceAdapter extends RecyclerView.Adapter<GroupedDeviceAdapter.GroupedDeviceViewHolder> {

    private ArrayList<Device> devicesInGroup;
    private ItemCallback itemCallback;

    GroupedDeviceAdapter(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
        devicesInGroup = new ArrayList<>();
//        devicesInGroup = new ArrayList<>(Arrays.asList(new ChangeableDeviceItem("Lampka 1"), new ChangeableDeviceItem("Lampka 2"),
//                new ChangeableDeviceItem("Gorne"), new ChangeableDeviceItem("Termostat"), new ChangeableDeviceItem("Wiatrak")));
    }

    @NonNull
    @Override
    public GroupedDeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new GroupedDeviceAdapter.GroupedDeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupedDeviceViewHolder holder, int position) {
        holder.bind(devicesInGroup.get(position), itemCallback);
    }

    @Override
    public int getItemCount() {
        return devicesInGroup.size();
    }

    public void setItems(ArrayList<Device> deviceItems) {
        devicesInGroup.clear();
        devicesInGroup.addAll(deviceItems);
        notifyDataSetChanged();
    }

    class GroupedDeviceViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView statusTextView;

        public GroupedDeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
        }

        public void bind(Device item, ItemCallback itemCallback) {
            this.nameTextView.setText(item.getName());
            this.statusTextView.setText(item.getStatus().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCallback.onItemClick(item);
                }
            });
        }
    }
}

interface ItemCallback {
    void onItemClick(Device item);
}