package com.example.myverysmarthome.home;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.devices.Camera;
import com.example.myverysmarthome.model.devices.Device;
import com.example.myverysmarthome.model.devices.Light;
import com.example.myverysmarthome.model.devices.Plug;

import java.util.ArrayList;

public class GroupedDeviceAdapter extends RecyclerView.Adapter<GroupedDeviceAdapter.GroupedDeviceViewHolder> {

    private ArrayList<Device> devicesInGroup;
    private GroupedDeviceCallBack groupedDeviceCallBack;

    GroupedDeviceAdapter(GroupedDeviceCallBack groupedDeviceCallBack) {
        this.groupedDeviceCallBack = groupedDeviceCallBack;
        devicesInGroup = new ArrayList<>();
    }

    @NonNull
    @Override
    public GroupedDeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_in_group, parent, false);
        return new GroupedDeviceAdapter.GroupedDeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupedDeviceViewHolder holder, int position) {
        holder.bind(devicesInGroup.get(position), groupedDeviceCallBack);
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
        ImageView deviceImage;
        LinearLayout container;

        public GroupedDeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
            container = itemView.findViewById(R.id.deviceContainer);
            deviceImage = itemView.findViewById(R.id.deviceImage);
        }

        public void bind(Device item, GroupedDeviceCallBack itemCallback) {
            this.nameTextView.setText(item.getName());
            Drawable drawable = itemView.getContext().getResources().getDrawable(item.getDrawableId(),itemView.getContext().getTheme());
            this.deviceImage.setImageDrawable(drawable);
            if (item instanceof Light || item instanceof Camera || item instanceof Plug) {
                boolean boolStatus = (boolean) item.getValue();
                if(!boolStatus) {
                    this.container.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.drawable.rounded_corners_gray));
                } else {
                    this.container.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.drawable.rounded_corners_light_gray));
                }
            } else {
                this.container.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.drawable.rounded_corners_light_gray));
            }
            this.statusTextView.setText(item.getValue().toString());
            itemView.setOnClickListener(view -> itemCallback.onItemClick(item));
        }
    }
}

interface GroupedDeviceCallBack {
    void onItemClick(Device item);
}