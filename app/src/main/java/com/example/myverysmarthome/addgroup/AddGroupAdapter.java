package com.example.myverysmarthome.addgroup;

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

public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupAdapter.AddGroupViewHolder> {
    private ArrayList<Device> allDevices;

    public AddGroupAdapter() {
        allDevices = DataContainer.getInstance().devices;
    }

    public void setItems(ArrayList<Device> deviceItems) {
        allDevices.clear();
        allDevices.addAll(deviceItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_on_add_group_list, parent, false);
        return new AddGroupAdapter.AddGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddGroupViewHolder holder, int position) {
        holder.bind(allDevices.get(position));
    }

    @Override
    public int getItemCount() {
        return allDevices.size();
    }

    class AddGroupViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public AddGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
        }

        void bind(Device device) {
            this.nameTextView.setText(device.getName());
        }
    }
}
