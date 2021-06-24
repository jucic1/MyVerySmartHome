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
import com.example.myverysmarthome.model.Group;

import java.util.ArrayList;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {

    ArrayList<Group> group;
    private GroupedDeviceCallBack groupedDeviceCallBack;

    public GroupsAdapter(GroupedDeviceCallBack groupedDeviceCallBack) {
        this.groupedDeviceCallBack = groupedDeviceCallBack;
        group = DataContainer.getInstance().groups;
    }

    void setItems(ArrayList<Group> deviceGroupItem){
        group.clear();
        group.addAll(deviceGroupItem);
        notifyDataSetChanged();
    }

    void refresh(){
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group,parent,false);
        return new GroupsViewHolder(view, groupedDeviceCallBack);
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
        TextView deleteTextView;

        public GroupsViewHolder(@NonNull View itemView, GroupedDeviceCallBack groupedDeviceCallBack) {
            super(itemView);
            groupedDeviceAdapter = new GroupedDeviceAdapter(groupedDeviceCallBack);
            recyclerView = itemView.findViewById(R.id.groupRecyclerView);
            titleTextView = itemView.findViewById(R.id.title);
            recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),4));
            recyclerView.setAdapter(groupedDeviceAdapter);
            deleteTextView = itemView.findViewById(R.id.deleteTextView);
        }
        public void bind(Group group) {
            titleTextView.setText(group.getTitle());
            groupedDeviceAdapter.setItems(group.getDevicesInGroup());

            deleteTextView.setOnClickListener(view -> {
               DataContainer.getInstance().removeGroup(group);
               notifyDataSetChanged();
            });
        }
    }
}
