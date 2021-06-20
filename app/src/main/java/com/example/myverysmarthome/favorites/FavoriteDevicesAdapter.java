package com.example.myverysmarthome.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import java.util.ArrayList;
import java.util.Arrays;

public class FavoriteDevicesAdapter extends RecyclerView.Adapter<FavoriteDevicesAdapter.FavoriteDevicesViewHolder> {

    ArrayList<ChangeableDeviceItem> favoriteDevices;

    public FavoriteDevicesAdapter() {
        this.favoriteDevices = new ArrayList<>(Arrays.asList(new ChangeableDeviceItem("Lampka 1"),
                new ChangeableDeviceItem("Lampka 2"), new ChangeableDeviceItem("Górne"),
                new ChangeableDeviceItem("Wiatrak"), new ChangeableDeviceItem("Termostat")));
    }

    @NonNull
    @Override
    public FavoriteDevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new FavoriteDevicesAdapter.FavoriteDevicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteDevicesViewHolder holder, int position) {
        holder.nameTextView.setText(favoriteDevices.get(position).name); //set value to field of holder
//        holder.nameTextView.setText(favoriteDevices.get(position).status);
    }

    @Override
    public int getItemCount() {
        return favoriteDevices.size();
    }

    class FavoriteDevicesViewHolder extends  RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView statusTextView;

        public FavoriteDevicesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.deviceName);
            statusTextView = itemView.findViewById(R.id.deviceStatus);
        }
    }
}
