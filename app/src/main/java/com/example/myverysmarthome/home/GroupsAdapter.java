package com.example.myverysmarthome.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.R;
import com.example.myverysmarthome.model.GroupItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {

    GroupedDeviceAdapter groupedDeviceAdapter = new GroupedDeviceAdapter();
    HashMap<String, List<GroupItem>> groupMap = new HashMap<>();

    public GroupsAdapter() {
        ArrayList<GroupItem> bedroomDevices = new ArrayList<>(Arrays.asList(new GroupItem("Lampka 1"), new GroupItem("Lampka 2"),
                new GroupItem("Górne"), new GroupItem("Wiatrak"), new GroupItem("Termostat")));
        ArrayList<GroupItem> lamps = new ArrayList<>(Arrays.asList(new GroupItem("Lampka 1"), new GroupItem("Lampka 2")));
        groupMap.put("Sypialnia", bedroomDevices);
        groupMap.put("Lampki nocne", lamps);
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group,parent,false);
        return new GroupsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsViewHolder holder, int position) {
       // holder.titleTextView.setText(devicesInGroup.get(position).title);
    }

    @Override
    public int getItemCount() {
        return groupMap.size();
    }

    class GroupsViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public GroupsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.groupRecyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),4));
            recyclerView.setAdapter(groupedDeviceAdapter);
        }
    }
}