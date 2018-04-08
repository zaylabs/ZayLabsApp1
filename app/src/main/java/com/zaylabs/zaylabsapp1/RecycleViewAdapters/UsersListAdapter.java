package com.zaylabs.zaylabsapp1.RecycleViewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.R;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    public List<customerRequest>cRequests;

    public UsersListAdapter(List<customerRequest>cRequests){
        this.cRequests= cRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mName.setText(cRequests.get(position).getName());
        holder.mPickup.setText(cRequests.get(position).getPickup().toString());
        holder.mDrop.setText(cRequests.get(position).getDrop().toString());
        holder.mPhone.setText(cRequests.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return cRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public TextView mName,mPickup,mDrop,mPhone;


        public ViewHolder(View itemView) {
            super(itemView);
            mView= itemView;
            mName=(TextView)mView.findViewById(R.id.name);
            mPickup=(TextView)mView.findViewById(R.id.pickup);
            mDrop=(TextView)mView.findViewById(R.id.drop);
            mPhone=(TextView)mView.findViewById(R.id.phone);


        }
    }


}
