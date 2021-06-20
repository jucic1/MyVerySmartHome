package com.example.myverysmarthome.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.DataContainer;
import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.ChangeableDeviceItem;
import com.example.myverysmarthome.model.Device;
import com.example.myverysmarthome.model.DeviceGroupItem;
import com.example.myverysmarthome.model.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {

    ArrayList<Group> group;
    private ItemCallback itemCallback;

    public GroupsAdapter(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
        group = DataContainer.getInstance().groups;
    }

    void setItems(ArrayList<Group> deviceGroupItem){
        group.clear();
        group.addAll(deviceGroupItem);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group,parent,false);
        return new GroupsViewHolder(view, itemCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsViewHolder holder, int position) {
       holder.bind(group.get(position));
    }

    @Override
    public int getItemCount() {
        return group.size();
    }

    class GroupsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        RecyclerView recyclerView;
        GroupedDeviceAdapter groupedDeviceAdapter;

        public GroupsViewHolder(@NonNull View itemView, ItemCallback itemCallback) {
            super(itemView);
            groupedDeviceAdapter = new GroupedDeviceAdapter(itemCallback);
            recyclerView = itemView.findViewById(R.id.groupRecyclerView);
            titleTextView = itemView.findViewById(R.id.title);
            recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),4));
            recyclerView.setAdapter(groupedDeviceAdapter);
        }
        public void bind(Group group) {
            titleTextView.setText(group.getTitle());
            groupedDeviceAdapter.setItems(group.getDevicesInGroup());
        }
    }
}
