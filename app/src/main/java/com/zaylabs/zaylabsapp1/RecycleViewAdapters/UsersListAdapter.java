package com.zaylabs.zaylabsapp1.RecycleViewAdapters;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.GeoPoint;
import com.zaylabs.zaylabsapp1.DTO.customerRequest;
import com.zaylabs.zaylabsapp1.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    public List<customerRequest>cRequests;
    public Context context;

    public UsersListAdapter(Context context,List<customerRequest>cRequests){
        this.cRequests= cRequests;
        this.context = context;
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

        GeoPoint mPick = cRequests.get(position).getPickup();
        List<Address> addresses = null;
        String currentAddress = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(
                    mPick.getLatitude(),
                    mPick.getLongitude(),
                    // In this sample, get just a single address.
                    1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            int max = address.getMaxAddressLineIndex();
            if (max != -1) {
                for (int i = 0; i < max; i++)

                    currentAddress += address.getAddressLine(i) + " ";
                holder.mPickup.setText(currentAddress);
            }
            else
                holder.mPickup.setText(cRequests.get(position).getPickup().toString());;
        }


        GeoPoint mDrops = cRequests.get(position).getDrop();
        List<Address> daddresses = null;
        String DropAddress = "";
        Geocoder dropgeocoder = new Geocoder(context, Locale.getDefault());
        try {
            daddresses = dropgeocoder.getFromLocation(
                    mDrops.getLatitude(),
                    mDrops.getLongitude(),
                    // In this sample, get just a single address.
                    1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (daddresses != null && daddresses.size() > 0) {
            Address dropaddress = daddresses.get(0);
            int max = dropaddress.getMaxAddressLineIndex();
            if (max != -1) {
                for (int i = 0; i < max; i++)

                    DropAddress += dropaddress.getAddressLine(i) + " ";
                holder.mDrop.setText(DropAddress);
            }
            else
                holder.mDrop.setText(cRequests.get(position).getDrop().toString());;
        }
        holder.mPhone.setText(cRequests.get(position).getPhone());

        final String user_id = cRequests.get(position).userId;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"User ID = " + user_id,Toast.LENGTH_SHORT).show();
            }
        });
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
