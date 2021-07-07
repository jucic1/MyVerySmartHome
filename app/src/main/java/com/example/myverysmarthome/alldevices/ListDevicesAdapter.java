package com.example.myverysmarthome.alldevices;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.devicechangestatus.ChangeDeviceStatusActivity;
import com.example.myverysmarthome.model.devices.Device;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ListDevicesAdapter extends RecyclerView.Adapter<ListDevicesAdapter.AllDevicesViewHolder> {

    private ArrayList<Device> allDevices;
    ListDevicesCallBack listDevicesCallBack;

    public ListDevicesAdapter(ListDevicesCallBack listDevicesCallBack) {
        allDevices = new ArrayList<>();
        this.listDevicesCallBack = listDevicesCallBack;
    }

    @NonNull
    @Override
    public AllDevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_on_list, parent, false);
        return new ListDevicesAdapter.AllDevicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllDevicesViewHolder holder, int position) {
        holder.bind(allDevices.get(position), listDevicesCallBack);
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
        ImageView deviceImage;

        public AllDevicesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
            deviceImage  = itemView.findViewById(R.id.deviceImage);
        }

        void bind(Device device, ListDevicesCallBack listDevicesCallBack) {
            this.nameTextView.setText(device.getName());
            this.statusTextView.setText(device.getValue().toString());
            Drawable drawable = itemView.getContext().getResources().getDrawable(device.getDrawableId(),itemView.getContext().getTheme());
            this.deviceImage.setImageDrawable(drawable);
            statusTextView.setOnClickListener(view -> {
                listDevicesCallBack.onItemClick(device);
            });
        }
    }

}
interface ListDevicesCallBack {
    void onItemClick(Device device);
}