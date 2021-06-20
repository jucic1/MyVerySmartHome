package com.example.myverysmarthome.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.Category;

import java.util.ArrayList;

public class DeviceMenuAdapter extends RecyclerView.Adapter<DeviceMenuAdapter.DeviceMenuViewHolder> {

    private ArrayList<Category> categoriesMenu;
    private CategoryItemClickListener categoryItemClickListener;

    DeviceMenuAdapter(CategoryItemClickListener categoryItemClickListener) {
        this.categoryItemClickListener = categoryItemClickListener;
        categoriesMenu = DataContainer.getInstance().categories;
    }

    @NonNull
    @Override
    public DeviceMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_menu, parent, false);
        return new DeviceMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceMenuViewHolder holder, int position) {
        holder.bind(categoriesMenu.get(position), categoryItemClickListener);
    }

    @Override
    public int getItemCount() {
        return categoriesMenu.size();
    }

    class DeviceMenuViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public DeviceMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.deviceName);
        }

        void bind(Category category, CategoryItemClickListener categoryCallBack) {
            this.titleTextView.setText(category.getTitle());
            itemView.setOnClickListener(view -> categoryCallBack.categoryItemCLick(category));
//            groupedDeviceAdapter.setItems(category.getDevicesInGroup());
        }
    }
}

interface CategoryItemClickListener {
    void categoryItemCLick(Category category);
}