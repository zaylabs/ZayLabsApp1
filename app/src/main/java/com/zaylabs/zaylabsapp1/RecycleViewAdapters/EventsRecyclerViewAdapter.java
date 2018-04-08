package com.zaylabs.zaylabsapp1.RecycleViewAdapters;


import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.R;

import java.util.List;

public class EventsRecyclerViewAdapter extends
        RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {

    private List<customerRequest> eventsList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public EventsRecyclerViewAdapter(List<customerRequest> list, Context ctx, FirebaseFirestore firestore) {
        eventsList = list;
        context = ctx;
        firestoreDB = firestore;
    }
    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    @Override
    public EventsRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list_single_item, parent, false);

        EventsRecyclerViewAdapter.ViewHolder viewHolder =
                new EventsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPos = position;
        final customerRequest event = eventsList.get(position);
        holder.name.setText(event.getName());
        holder.pickup.setText(event.getPickup().toString());
        holder.drop.setText(event.getDrop().toString());
        holder.phone.setText(event.getPhone());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,pickup,drop,phone;


        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tv_name);
            pickup = (TextView) view.findViewById(R.id.tv_pickup_location);
            drop = (TextView) view.findViewById(R.id.tv_drop_location);
            phone=(TextView)view.findViewById(R.id.tv_phone);

        }
    }
    }
