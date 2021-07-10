package com.example.myverysmarthome.addgroup;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.devices.Device;

import java.util.ArrayList;

public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupAdapter.AddGroupViewHolder> {
    private ArrayList<Device> allDevices;
    AddGroupCallBack addGroupCallBack;

    public AddGroupAdapter(AddGroupCallBack addGroupCallBack) {
        allDevices = DataContainer.getInstance().devices;
        this.addGroupCallBack = addGroupCallBack;
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
        holder.bind(allDevices.get(position), addGroupCallBack);
    }

    @Override
    public int getItemCount() {
        return allDevices.size();
    }

    class AddGroupViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        CheckBox deviceCheckbox;
        ImageView deviceImage;

        public AddGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            deviceCheckbox = itemView.findViewById(R.id.addDeviceCheckbox);
            deviceImage = itemView.findViewById(R.id.deviceImage);
        }

        void bind(Device device, AddGroupCallBack addGroupCallBack) {
            this.nameTextView.setText(device.getName());
            int drawableId = DataContainer.getInstance().getCategoryForDevice(device).getDrawableId();
            Drawable drawable = itemView.getContext().getResources().getDrawable(drawableId,itemView.getContext().getTheme());
            deviceImage.setImageDrawable(drawable);

            deviceCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        addGroupCallBack.onAddDeviceItem(device);
                    }else{
                        addGroupCallBack.onRemoveDeviceItem(device);
                    }
                }
            });
        }
    }
}

interface AddGroupCallBack {
    void onAddDeviceItem(Device item);
    void onRemoveDeviceItem(Device item);
}