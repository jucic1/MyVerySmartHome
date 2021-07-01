package com.example.myverysmarthome.alldevices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Device;

import java.util.ArrayList;

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

        public AllDevicesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
        }

        void bind(Device device, ListDevicesCallBack listDevicesCallBack) {
            this.nameTextView.setText(device.getName());
            this.statusTextView.setText(device.getStatus().toString());
            statusTextView.setOnClickListener(view -> {
                DataContainer.getInstance().getDevice(device.getUuid()).setStatus(!device.getStatus());
                listDevicesCallBack.onItemClick();
            });
        }
    }

}
interface ListDevicesCallBack {
    void onItemClick();
}